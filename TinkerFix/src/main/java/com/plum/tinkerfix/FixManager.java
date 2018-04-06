package com.plum.tinkerfix;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashSet;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * Created by mei on 2018/4/6.
 * Description:
 */

public class FixManager {

    public static final String DEX_DIR = "odex";
    private static HashSet<File> loadedDex = new HashSet<>();

    static {
        loadedDex.clear();
    }

    /**
     * 把自己的dex文件封装成dexElements数组，并与现存的dexElements数组合并组成新的数组，
     * 并为PathClassLoader的PathList的dexElements字段重新赋值。
     *
     * @param context
     */
    public static void loadDex(Context context) {

        if (context == null) return;

        File filesDir = context.getDir(DEX_DIR, Context.MODE_PRIVATE);
        Log.i("INFO", "filesDir=" + filesDir.getAbsolutePath() + ";name=" + filesDir.getName());

        File[] listFiles = filesDir.listFiles();
        for (File file : listFiles) {
            if (file.getName().startsWith("classes") || file.getName().endsWith(".dex")) {
                Log.i("INFO", "dexName:" + file.getName());
                loadedDex.add(file);
            }
        }

        String optimizeDir = filesDir.getAbsolutePath() + File.separator + "opt_dex";
        Log.i("INFO", "optimizeDir=" + optimizeDir);
        File fopt = new File(optimizeDir);
        if (!fopt.exists()) {
            fopt.mkdirs();
        }

        try {
            for (File dex : loadedDex) {
                //-----------------------系统的ClassLoader------------------------------------
                PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
                Class baseDexClazzLoader = Class.forName("dalvik.system.BaseDexClassLoader");
                Field pathListField = baseDexClazzLoader.getDeclaredField("pathList");
                pathListField.setAccessible(true);
                Object systemPathListObject = pathListField.get(pathClassLoader);

                Class systemPathClazz = systemPathListObject.getClass();
                Field systemElementsField = systemPathClazz.getDeclaredField("dexElements");
                systemElementsField.setAccessible(true);
                Object systemDexElements = systemElementsField.get(systemPathListObject);

                // ------------------自己的ClassLoader--------------------------
                DexClassLoader classLoader = new DexClassLoader(dex.getAbsolutePath(),
                        fopt.getAbsolutePath(), null, context.getClassLoader());

                Class myDexClazzLoader = Class.forName("dalvik.system.BaseDexClassLoader");
                Field myPathListField = myDexClazzLoader.getDeclaredField("pathList");
                myPathListField.setAccessible(true);
                Object myPathListObject = myPathListField.get(classLoader);

                Class myDexElementsClazz = myPathListObject.getClass();
                Field myDexElementsField = myDexElementsClazz.getDeclaredField("dexElements");
                myDexElementsField.setAccessible(true);
                Object myDexElementsObject = myDexElementsField.get(myPathListObject);

                //------------------------融合dexElements数组-----------------------------
                //获取dexElements数组里面存储的元素的类型
                Class sigleElementClazz = systemDexElements.getClass().getComponentType();
                int systemDexElementsLength = Array.getLength(systemDexElements);
                int myDexElementsLength = Array.getLength(myDexElementsObject);
                //新数组的总长度
                int newDexElementsLength = systemDexElementsLength + myDexElementsLength;
                //创建一个新的dexElements数组 类型为Element类型
                Object newElementsArray = Array.newInstance(sigleElementClazz, newDexElementsLength);

                //为新创建的dexElements数组赋值
                for (int i = 0; i < newDexElementsLength; i++) {
                    if (i < myDexElementsLength) {//需要把我们自己的dex文件放在数组的前面，这样优先加载的类就是我们修复好的类了
                        Array.set(newElementsArray, i, Array.get(myDexElementsObject, i));
                    } else {
                        Array.set(newElementsArray, i, Array.get(systemDexElements, i - myDexElementsLength));
                    }
                }

                //-------------------融合完毕   将新数组  放到系统的PathLoad内部----------------------
                systemElementsField.set(systemPathListObject, newElementsArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
