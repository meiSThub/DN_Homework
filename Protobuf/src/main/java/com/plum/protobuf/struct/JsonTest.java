package com.plum.protobuf.struct;

import android.location.Address;
import android.util.Log;



import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/2 0002.
 */

public class JsonTest  {

    private static final String TAG = "JsonTest";

    public static void fastJson() {

        AddressBook addressBook = getObject();

        long l = System.currentTimeMillis();
        String data = JSON.toJSONString(addressBook);
        byte[] bytes = data.getBytes();
        Log.e(TAG, "FastJson 序列化耗时：" + (System.currentTimeMillis() - l));
        Log.e(TAG, "FastJson 序列化数据大小：" + bytes.length);


        l = System.currentTimeMillis();
        AddressBook addressBook1 = JSON.parseObject(new String(bytes), AddressBook.class);
        Log.e(TAG, "FastJson 反序列化耗时：" + (System.currentTimeMillis() - l));
//        Log.e(TAG,addressBook1.getPersons().get(0).getName());
//        Log.e(TAG,addressBook1.getPersons().get(0).getPhones().get(0).getNumber());
    }
    
    public static void gson(){
        AddressBook addressBook = getObject();
        long l = System.currentTimeMillis();
        Gson gson = new Gson();
        String data = gson.toJson(addressBook);
        byte[] bytes = data.getBytes();
        Log.e(TAG, "Gson 序列化耗时：" + (System.currentTimeMillis() - l));
        Log.e(TAG, "Gson 序列化数据大小：" + bytes.length);

        l = System.currentTimeMillis();

        AddressBook addressBook1 = gson.fromJson(new String(bytes), AddressBook.class);
        Log.e(TAG, "Gson 反序列化耗时：" + (System.currentTimeMillis() - l));
        
//        Log.e(TAG,addressBook1.getPersons().get(0).getName());
//        Log.e(TAG,addressBook1.getPersons().get(0).getPhones().get(0).getNumber());
    }
    
    
    
    private static AddressBook getObject(){
        AddressBook addressBook = new AddressBook();

        PhoneNumber p_110 = new PhoneNumber();
        p_110.setNumber("110");
        Person zs = new Person();
        zs.setId(1);
        zs.setName("张三");
        zs.addPhones(p_110);
        addressBook.addPersons(zs);


        PhoneNumber p_120 = new PhoneNumber();
        p_120.setNumber("120");
        Person ls = new Person();
        ls.setId(2);
        ls.setName("李四");
        ls.addPhones(p_120);
        addressBook.addPersons(ls);
        return addressBook;
    }
}
