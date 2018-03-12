package com.lxy.module.skin.listener;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.view.View;

import com.lxy.module.skin.dynamic.DynamicAttrWrapper;

import java.util.List;

/**
 * Created by lxy on 2018/3/1.
 * 
 */
public interface ILInflaterFactory extends LayoutInflaterFactory {
    void onResume();

    void onDestroy();

    void dynamicAddSkinView(Context context, View view, List<DynamicAttrWrapper> attrs);
}
