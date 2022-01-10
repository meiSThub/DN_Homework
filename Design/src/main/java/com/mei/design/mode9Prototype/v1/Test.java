package com.mei.design.mode9Prototype.v1;

/**
 * @author mxb
 * @date 2022/1/8
 * @desc
 * @desired
 */
public class Test {

    public static void main(String[] args) {
        GirlFriend girlFriend = new GirlFriend("Marry", "医生");
        User user = new User("张三", "计算机", 20, girlFriend);
        User cloneUser = user.clone();
        user.girlFriend.name = "美女";
        System.out.println("origin user:" + user);
        System.out.println(" clone user:" + cloneUser);
    }
}
