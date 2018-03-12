package com.lxy.module.skin.factory;

import android.util.AttributeSet;

import com.lxy.module.skin.attr.BackgroundAttr;
import com.lxy.module.skin.attr.BaseAttr;
import com.lxy.module.skin.attr.DividerAttr;
import com.lxy.module.skin.attr.ImageResourceAttr;
import com.lxy.module.skin.attr.ListSelectorAttr;
import com.lxy.module.skin.attr.TextColorAttr;
import com.lxy.module.skin.config.SkinConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lxy on 2018/3/1.
 * 
 */
public class LAttrFactory {
    
    private static Map<String, Class<? extends BaseAttr>> sSupportAttr = new HashMap<>();
    
    static {
        sSupportAttr.put(SkinConfig.ATTR_NAME_BACKGROUND        , BackgroundAttr.class);
        sSupportAttr.put(SkinConfig.ATTR_NAME_TEXTCOLOR         , TextColorAttr.class);
        sSupportAttr.put(SkinConfig.ATTR_NAME_SRC               , ImageResourceAttr.class);
        sSupportAttr.put(SkinConfig.ATTR_NAME_LIST_SELECTOR     , ListSelectorAttr.class);
        sSupportAttr.put(SkinConfig.ATTR_NAME_DIVIDER           , DividerAttr.class);
    }

    public static void addSupportSkinAttr(String attrName, Class<? extends BaseAttr> cls){
        sSupportAttr.put(attrName, cls);
    }
    
    public static BaseAttr get(int id, String attrName, String entryName, String typeName){
        
        BaseAttr attr = null;
        Class<? extends BaseAttr> cls = sSupportAttr.get(attrName);
        
        try {
            
            attr =  cls.newInstance();
            
            attr.attrValueID            = id;
            attr.attrName               = attrName;
            attr.attrValueEntryName     = entryName;
            attr.attrValueTypeName      = typeName;
            
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return attr;
    }

    public static boolean isSupportedAttr(String attrName){
        return sSupportAttr.keySet().contains(attrName);
    }

    public static boolean isAttrEnableSkin(AttributeSet attrs){
        return attrs.getAttributeBooleanValue(SkinConfig.XML_SKIN_NAME_SPACE, SkinConfig.XML_SKIN_ENABLE, false);
    }
    
}
