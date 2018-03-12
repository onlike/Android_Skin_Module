package com.lxy.demo_applicaionskin;

import android.app.Application;
import android.util.Log;

import com.lxy.module.skin.SkinManager;

/**
 * Created by linksus on 2018/3/1.
 */

public class LApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.init(this);

        Log.e("onlike","---------------------------------------"+this.getPackageName());
    }
}
