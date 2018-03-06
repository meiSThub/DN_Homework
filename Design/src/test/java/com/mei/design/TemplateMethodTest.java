package com.mei.design;

import com.mei.design.mode10TemplateMethod.HoldElephant;
import com.mei.design.mode10TemplateMethod.HoldElephantA;

import org.junit.Test;



/**
 * Created by ubt on 2018/1/11.
 *
 * @description:模板方法测试用例
 */

public class TemplateMethodTest {

    @Test
    public void test() {
        HoldElephant holdElephant = new HoldElephantA();
        holdElephant.hold();
    }
}
