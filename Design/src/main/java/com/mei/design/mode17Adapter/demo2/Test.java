package com.mei.design.mode17Adapter.demo2;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description:
 */

public class Test {

    public static void main(String[] args) {
        Player americaPlayer = new AmericaPlayer();
        americaPlayer.setName("科比");
        americaPlayer.attack();

        ForeignPlayer foreignPlayer = new ForeignPlayer();
        foreignPlayer.setName("姚明");

        TranslatorAdapter adapter = new TranslatorAdapter(foreignPlayer);
        adapter.attack();

    }
}
