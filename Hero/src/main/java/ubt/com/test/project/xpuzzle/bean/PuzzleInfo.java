package ubt.com.test.project.xpuzzle.bean;

import android.graphics.Bitmap;

/**
 * Created by ubt on 2018/1/4.
 *
 * @description:
 */

public class PuzzleInfo {

    public int id;
    public int bitmapId;
    public Bitmap mBitmap;

    public PuzzleInfo(int id, int bitmapId, Bitmap bitmap) {
        this.id = id;
        this.bitmapId = bitmapId;
        mBitmap = bitmap;
    }
}
