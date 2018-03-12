package com.lxy.module.skin.attr;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.lxy.module.skin.SkinManager;
import com.lxy.module.skin.config.SkinConfig;
import com.lxy.module.skin.util.LSkinUtils;

/**
 * Created by lxy on 2018/3/1.
 * 
 */
public abstract class BaseAttr {

    /**
     * attr name , {@link com.lxy.module.skin.annotations.SkinAttrName}
     */
    public String attrName;

    /**
     * attr value id in R file , eg:R.drawable.main_bg(in fact such as:17301755....)
     */
    public int attrValueID;

    /**
     * attr value name in xml , eg:main_text_blue
     */
    public String attrValueEntryName;

    /**
     * attr value type , such as color or drawable
     */
    public String attrValueTypeName;

    public BaseAttr() {
    }

    public abstract void invoke(View view);
    
    
    public boolean isColorType(){
        return LSkinUtils.txt_equals(attrValueTypeName, SkinConfig.ATTR_VALUE_TYPE_COLOR);
    }
    
    public boolean isDrawableType(){
        return LSkinUtils.txt_equals(attrValueTypeName, SkinConfig.ATTR_VALUE_TYPE_BACKGROUND);
    }
    
    public int getColor(){
        return SkinManager.getInstance().getColor(attrValueID);
    }
    
    public Drawable getDrawable(){
        return SkinManager.getInstance().getDrawable(attrValueID);
    }
    
    public ColorStateList getColorList(){
        return SkinManager.getInstance().getColorList(attrValueID);
    }

    @Override
    public String toString() {
        return "BaseAttr{" +
                "targetAttrName='" + attrName + '\'' +
                ", targetAttrValueID='" + attrValueID + '\'' +
                ", attrValueEntryName='" + attrValueEntryName + '\'' +
                ", attrValueTypeName='" + attrValueTypeName + '\'' +
                '}';
    }
}
