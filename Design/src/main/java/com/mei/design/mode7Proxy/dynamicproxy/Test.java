package com.mei.design.mode7Proxy.dynamicproxy;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * Created by ubt on 2018/1/11.
 *
 * @description:动态代理测试类
 */

public class Test {


    public static void main(String[] args) {
        //真实角色
        Star star = new Star();
        //代理角色处理器
        StarHandler handler = new StarHandler(star);

        IStar starProxy = (IStar) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{IStar.class}, handler);
        System.out.println("proxy="+starProxy.getClass().getName());
        starProxy.accept("sing");
        writeClassToDisk();
    }


    public static void writeClassToDisk() {
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{IStar.class});
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("/Users/akulaku/WorkSpace/AndroidDemo/DN_Homework/Design/src/main/java/com/mei/design/mode7Proxy/dynamicproxy/$Proxy0.class");
            fos.write(classFile);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
