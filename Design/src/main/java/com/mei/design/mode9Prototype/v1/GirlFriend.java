package com.mei.design.mode9Prototype.v1;

/**
 * @author mxb
 * @date 2022/1/8
 * @desc
 * @desired
 */
public class GirlFriend implements Cloneable {

    String name;

    String work;

    public GirlFriend(String name, String work) {
        this.name = name;
        this.work = work;
    }

    @Override
    protected GirlFriend clone() {
        try {
            return (GirlFriend) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
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
