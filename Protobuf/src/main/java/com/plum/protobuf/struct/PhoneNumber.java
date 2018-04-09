package com.plum.protobuf.struct;

import java.util.List;

/**
 * Created by Administrator on 2018/2/2 0002.
 */

public class PhoneNumber {
    enum PhoneType {
        MOBILE,
        HOME,
        WORK;
    }

    private String number;
    private PhoneType type;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }
}
