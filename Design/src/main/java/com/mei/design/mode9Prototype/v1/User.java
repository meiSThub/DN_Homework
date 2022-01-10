package com.mei.design.mode9Prototype.v1;

/**
 * @author mxb
 * @date 2022/1/8
 * @desc
 * @desired
 */
public class User implements Cloneable {

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
            User cloneUser = (User) super.clone();
            cloneUser.girlFriend = girlFriend.clone();
            return cloneUser;
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
