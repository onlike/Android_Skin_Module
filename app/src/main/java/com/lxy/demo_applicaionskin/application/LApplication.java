package com.lxy.demo_applicaionskin.application;

import android.app.Application;
import android.util.Log;

import com.lxy.module.skin.SkinManager;

/**
 * Created by lxy on 2018/3/1.
 * 
 */
public class LApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        
        // you must init module at application run first time
        SkinManager.init(this);

        Log.e("onlike","---------------------------------------"+this.getPackageName());
    }
}
