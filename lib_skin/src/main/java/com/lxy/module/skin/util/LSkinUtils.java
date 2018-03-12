package com.lxy.module.skin.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.io.File;
import java.util.List;

/**
 * Created by lxy on 2018/3/1.
 * 
 */
public class LSkinUtils {
    
    public static boolean obj_isNull(Object obj){
        return obj == null;
    }
    
    public static boolean txt_isEmpty(String str){
        return str == null || str.length() == 0;
    }
    
    public static boolean txt_equals(String a, String b){
        return TextUtils.equals(a, b);
    }
    
    public static boolean array_isEmpty(List<?> list){
        return list == null || list.size() == 0 ;
    }

    public static boolean file_isFileExists(File file){
        return file !=null && file.exists();
    }

    public static boolean file_isFileNotExists(File file){
        return file == null || !file.exists();
    }
    
    public static String getSourceApkPath(Context context) {
        
        String packageName = context.getPackageName();
        
        try {
            
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
            return appInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return packageName;
    }
    
}
