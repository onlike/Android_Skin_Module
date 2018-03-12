package com.lxy.module.skin.mediator;

import android.view.View;

import com.lxy.module.skin.attr.BaseAttr;
import com.lxy.module.skin.util.LSkinUtils;

import java.util.List;

/**
 * Created by lxy on 2018/3/1.
 * 
 */
public class SkinViewMediator {
    
    public View view;
    
    public List<BaseAttr> attrs;
    
    public void apply(){
        if (LSkinUtils.obj_isNull(view)) return;
        if (LSkinUtils.array_isEmpty(attrs)) return;

        for (BaseAttr attr : attrs) {
            attr.invoke(view);
        }
    }
    
    public void clean(){
        
        if (!LSkinUtils.obj_isNull(view)){
            view = null;
        }
        
        if (!LSkinUtils.array_isEmpty(attrs)) {
            attrs.clear();
            attrs = null;
        }
    }
}
