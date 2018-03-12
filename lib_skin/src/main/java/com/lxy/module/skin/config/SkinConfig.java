package com.lxy.module.skin.config;

import android.content.Context;

import com.lxy.module.skin.util.LShareUtil;
import com.lxy.module.skin.util.LSkinUtils;

/**
 * Created by lxy on 2018/3/1.
 * 
 */
public class SkinConfig {
    
    
    
    public static final String XML_SKIN_NAME_SPACE  = "http://schemas.android.com/android/skin";
    
    public static final String XML_SKIN_ENABLE      = "enable";
    
    
    
    public static final String ATTR_NAME_BACKGROUND     = "background";
    
    public static final String ATTR_NAME_TEXTCOLOR      = "textColor";
    
    public static final String ATTR_NAME_SRC            = "src";
    
    public static final String ATTR_NAME_LIST_SELECTOR  = "listSelector";
    
    public static final String ATTR_NAME_DIVIDER        = "divider";
    
    
    
    public static final String ATTR_VALUE_TYPE_COLOR        = "color";
    
    public static final String ATTR_VALUE_TYPE_BACKGROUND   = "drawable";
    
    
    
    public static final String VERIFY_SKIN_PACKAGE_NAME = "com.lxy.module.skin.factory";
    
    
    
    public static final String SP_NAME                  = "com_lxy_module_skin_sp";
    
    public static final String SP_SKIN_PATH_DEFAULT     = "sp_skin_path_default";
    
    public static final String SP_SKIN_PATH_CHANGE      = "sp_skin_path_change";
    
    
    
    
    /**
     * 皮肤包加载失败-皮肤包不存在
     */
    public static final int LOAD_ERROR_FILE_NOT_EXIT        = 110;

    /**
     * 皮肤包加载失败-签名验证失败
     */
    public static final int LOAD_ERROR_ILLEGAL_SIGNATURE    = 120;

    /**
     * 皮肤包加载失败-包名验证失败
     */
    public static final int LOAD_ERROR_ILLEGAL_PACKAGE      = 130;

    /**
     * 皮肤包加载失败-皮肤包文件错误
     */
    public static final int LOAD_ERROR_FILE_INTERNAL_ERROR  = 140;
    
    
    

    public static void saveChangeSkinPath(Context context, String skinPkgPath) {
        LShareUtil.putString(context, SP_SKIN_PATH_CHANGE, skinPkgPath);
    }
    
    public static String readChangeSkinPath(Context context){
        return LShareUtil.getString(context, SP_SKIN_PATH_CHANGE, "");
    }

    public static void saveDefaultSkinPath(Context context) {
        LShareUtil.putString(context, SP_SKIN_PATH_DEFAULT, LSkinUtils.getSourceApkPath(context));
    }

    public static String readDefaultSkinPath(Context context){
        return LShareUtil.getString(context, SP_SKIN_PATH_DEFAULT, "");
    }
    
}
