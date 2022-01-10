package com.mei.design.mode9Prototype.v2;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author mxb
 * @date 2022/1/8
 * @desc
 * @desired
 */
public class User implements Serializable {

    String name;

    String subject;

    int age;

    GirlFriend girlFriend;

    public User(String name, String subject, int age, GirlFriend girlFriend) {
        this.name = name;
        this.subject = subject;
        this.age = age;
        this.girlFriend = girlFriend;
    }

    @Override
    protected User clone() {
        try {
            // 把对象二进制写入内存
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);
            // 把对象二进制从内存中读取出来
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                    byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            User user = (User) objectInputStream.readObject();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "User{" +
                "hashCode='" + hashCode() + '\'' +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", age=" + age +
                ", girlFriend=" + girlFriend +
                '}';
    }
}
