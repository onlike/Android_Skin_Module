package com.lxy.module.skin.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.lxy.module.skin.SkinManager;
import com.lxy.module.skin.config.SkinConfig;

/**
 * Created by lxy on 2018/3/6.
 * 
 */
public class LResourceProviderUtil {

    public static int getColor(Context context, Resources skinResource, int resId) {

        int originColor = context.getResources().getColor(resId);

        if (skinResource == null || SkinManager.getInstance().isDefaultSkin()) {
            return originColor;
        }

        int finalColor  = 0;

        String resName = context.getResources().getResourceEntryName(resId);

        int trueResId = skinResource.getIdentifier(resName, 
                SkinConfig.ATTR_VALUE_TYPE_COLOR, 
                SkinConfig.VERIFY_SKIN_PACKAGE_NAME);

        try {
            
            finalColor = skinResource.getColor(trueResId);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            
            finalColor = originColor;
        }

        return finalColor;
    }

    @SuppressLint("NewApi")
    public static Drawable getDrawable(Context context, Resources skinResource, int resId) {


        Drawable originDrawable = context.getResources().getDrawable(resId);

        if (skinResource == null || SkinManager.getInstance().isDefaultSkin()) {
            return originDrawable;
        }

        Drawable finalDrawable  = null;

        String resName = context.getResources().getResourceEntryName(resId);

        int trueResId = skinResource.getIdentifier(resName, 
                SkinConfig.ATTR_VALUE_TYPE_BACKGROUND, 
                SkinConfig.VERIFY_SKIN_PACKAGE_NAME);

        try {

            if (android.os.Build.VERSION.SDK_INT < 22) {
                
                finalDrawable = skinResource.getDrawable(trueResId);
            } else {
                
                finalDrawable = skinResource.getDrawable(trueResId, null);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            
            finalDrawable = originDrawable;
        }

        return finalDrawable;
    }


    public static ColorStateList getColorStateList(Context context, Resources skinResource, int resId) {

        ColorStateList originColorList = context.getResources().getColorStateList(resId);

        if (skinResource == null || SkinManager.getInstance().isDefaultSkin()) {
            return originColorList;
        }

        ColorStateList finalColorList  = null;

        String resName = context.getResources().getResourceEntryName(resId);

        int trueResId = skinResource.getIdentifier(resName, 
                SkinConfig.ATTR_VALUE_TYPE_COLOR, 
                SkinConfig.VERIFY_SKIN_PACKAGE_NAME);

        try {

            if (trueResId == 0) { 

                finalColorList = originColorList;
            } else {

                finalColorList = skinResource.getColorStateList(trueResId);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        
        if (finalColorList == null){
            int[][] states = new int[1][1];
            finalColorList = new ColorStateList(states, new int[]{context.getResources().getColor(resId)});
        }

        return finalColorList;
    }
}
