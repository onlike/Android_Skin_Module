package com.lxy.module.skin.attr;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by lxy on 2018/3/1.
 * 
 */
public class ImageResourceAttr extends BaseAttr{
    
    @Override
    public void invoke(View view) {
        
        if (view instanceof ImageView){

            ImageView imageView = (ImageView) view;

            if (isDrawableType()){

                imageView.setImageDrawable(getDrawable());
            }else if (isColorType()){

                imageView.setImageDrawable(new ColorDrawable(getColor()));
            }
        }
    }
}
