package com.plum.protobuf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tutorial.AddressBookProtos;
import com.google.protobuf.InvalidProtocolBufferException;
import com.plum.protobuf.struct.JsonTest;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new SocketTest().start();
    }

    /**
     * Protobuf 的基本使用
     */
    private void protofufBaseUse(){
        AddressBookProtos.Person.PhoneNumber.Builder builder=AddressBookProtos.Person
                .PhoneNumber.newBuilder().setNumber("110");
        AddressBookProtos.Person.Builder person = AddressBookProtos.Person.newBuilder().setName
                ("张三")
                .setId(1).addPhones(builder);

        AddressBookProtos.Person.PhoneNumber.Builder builder2 = AddressBookProtos.Person
                .PhoneNumber.newBuilder().setNumber("120");
        AddressBookProtos.Person.Builder person2 = AddressBookProtos.Person.newBuilder().setName
                ("李四")
                .setId(2).addPhones(builder2);

        AddressBookProtos.AddressBook addressBook = AddressBookProtos.AddressBook.newBuilder()
                .addPeople(person).addPeople(person2).build();


        //保存为一个文件 需要向服务器发送
        // 序列化 将对象转成 byte数组
        long l = System.currentTimeMillis();
        byte[] bytes = addressBook.toByteArray();
        Log.e(TAG, "protobuf 序列化耗时:" + (System.currentTimeMillis() - l));
        Log.e(TAG, "protobuf 序列化数据大小:" + bytes.length);

        // 从文件 读到内存 或者 解析从服务器返回的数据
        // 反序列化
        try {
            l = System.currentTimeMillis();
            AddressBookProtos.AddressBook addressBook1 = AddressBookProtos.AddressBook.parseFrom
                    (bytes);
            Log.e(TAG, "protobuf 反序列化耗时:" + (System.currentTimeMillis() - l));
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }


        JsonTest.fastJson();
        JsonTest.gson();
    }
}
