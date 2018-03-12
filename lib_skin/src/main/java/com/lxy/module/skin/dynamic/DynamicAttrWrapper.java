package com.lxy.module.skin.dynamic;

import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * Created by lxy on 2018/3/6.
 * 
 * when dynamic add view to skin , use this wrapper the target attr name and id 
 * see{@link com.lxy.module.skin.listener.ILInflaterFactory#dynamicAddSkinView(Context, View, List)}
 */
public class DynamicAttrWrapper {

    /**
     * target attr name , {@link com.lxy.module.skin.annotations.SkinAttrName}
     */
    public String targetAttrName;

    /**
     * target attr value id in R file , eg:R.drawable.main_bg(in fact such as:17301755....)
     */
    public int targetAttrValueID;

    public DynamicAttrWrapper(String attrName, int attrValueID) {
        this.targetAttrName = attrName;
        this.targetAttrValueID = attrValueID;
    }

}
