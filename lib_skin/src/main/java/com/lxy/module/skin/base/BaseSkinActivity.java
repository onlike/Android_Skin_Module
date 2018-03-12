package com.lxy.module.skin.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.lxy.module.skin.dynamic.DynamicAttrWrapper;
import com.lxy.module.skin.factory.LInflaterFactory;
import com.lxy.module.skin.util.LSkinUtils;

import java.util.List;

/**
 * Created by lxy on 2018/3/1.
 * 
 */
public class BaseSkinActivity extends AppCompatActivity{
    
    private LInflaterFactory mFactory;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initSkinFactory();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        
        if (!LSkinUtils.obj_isNull(mFactory)){
            mFactory.onResume();
        }

        super.onResume();
    }

    @Override
    protected void onDestroy() {
        
        if (!LSkinUtils.obj_isNull(mFactory)){
            mFactory.onDestroy();
        }

        super.onDestroy();
    }

    protected void dynamicAddSkinView(View view, List<DynamicAttrWrapper> attrs){
        
        if (!LSkinUtils.obj_isNull(mFactory)){
            mFactory.dynamicAddSkinView(this, view, attrs);
        }
    }
    
    private void initSkinFactory(){
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory(layoutInflater, mFactory = new LInflaterFactory(this));
    }
}
