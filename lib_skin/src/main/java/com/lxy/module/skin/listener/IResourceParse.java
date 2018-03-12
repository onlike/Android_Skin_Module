package com.lxy.module.skin.listener;

import android.content.Context;
import android.content.res.Resources;

import com.lxy.module.skin.annotations.SkinLoadError;

/**
 * Created by lxy on 2018/3/5.
 * 
 */
public interface IResourceParse {

    void load(Context context, String skinPath, ParseInvokeCallback callback);

    interface ParseInvokeCallback {

        void start();

        void error(@SkinLoadError int state);

        void finish(Resources resources);

    }


}
