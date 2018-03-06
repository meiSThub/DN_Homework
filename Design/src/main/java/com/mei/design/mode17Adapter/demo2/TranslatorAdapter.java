package com.mei.design.mode17Adapter.demo2;

/**
 * Created by ubt on 2018/1/17.
 *
 * @description: 球员适配者，即翻译，翻译把进攻指令给外籍球员
 * <p>
 * 把外籍球员适配成美籍球员,外籍球员是被适配者
 */

public class TranslatorAdapter extends Player {

    private ForeignPlayer mForeignPlayer;

    public TranslatorAdapter(ForeignPlayer foreignPlayer) {
        mForeignPlayer = foreignPlayer;
    }

    @Override
    public void attack() {
        mForeignPlayer.进攻();
    }

    @Override
    public void defense() {
        mForeignPlayer.防守();
    }
}
