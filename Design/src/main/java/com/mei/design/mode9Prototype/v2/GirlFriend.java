package com.mei.design.mode9Prototype.v2;

import java.io.Serializable;

/**
 * @author mxb
 * @date 2022/1/8
 * @desc
 * @desired
 */
public class GirlFriend implements Serializable {

    String name;

    String work;

    public GirlFriend(String name, String work) {
        this.name = name;
        this.work = work;
    }

    @Override
    public String toString() {
        return "GirlFriend{" +
                "hashCode='" + hashCode() + '\'' +
                ", name='" + name + '\'' +
                ", work='" + work + '\'' +
                '}';
    }
}
