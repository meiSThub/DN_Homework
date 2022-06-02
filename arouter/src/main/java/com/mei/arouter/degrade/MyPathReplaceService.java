package com.mei.arouter.degrade;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.PathReplaceService;

import android.content.Context;
import android.net.Uri;

/**
 * @author mxb
 * @date 2022/6/2
 * @desc
 * @desired
 */
@Route(path = "/degrade/myPathReplace")
public class MyPathReplaceService implements PathReplaceService {

    @Override
    public String forString(String path) {
        return null;
    }

    @Override
    public Uri forUri(Uri uri) {
        return null;
    }

    @Override
    public void init(Context context) {

    }
}
