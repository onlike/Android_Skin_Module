package com.lxy.module.skin.attr;

import android.graphics.drawable.ColorDrawable;
import android.view.View;

/**
 * Created by lxy on 2018/3/1.
 * 
 */
public class BackgroundAttr extends BaseAttr{
    @Override
    public void invoke(View view) {
        
        if (isDrawableType()){
            
            view.setBackground(getDrawable());
        }else if (isColorType()){
            
            // in here , the source code have reset color list selector logic , we just shield it; 
//            view.setBackgroundColor(getColor());
            view.setBackground(new ColorDrawable(getColor()));
        }
    }
}
