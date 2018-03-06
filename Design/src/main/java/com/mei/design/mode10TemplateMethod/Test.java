package com.mei.design.mode10TemplateMethod;

/**
 * Created by ubt on 2018/1/11.
 *
 * @description:模板方法测试用例
 */

public class Test {

    public static void main(String[] args) {
        HoldElephant holdElephant = new HoldElephantA();
        holdElephant.hold();
    }
}
