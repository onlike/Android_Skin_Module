package com.lxy.module.skin.parse;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.AsyncTask;

import com.lxy.module.skin.config.SkinConfig;
import com.lxy.module.skin.listener.IResourceParse;
import com.lxy.module.skin.util.LSkinUtils;

import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by lxy on 2018/3/5.
 * 
 */
public class LResourceParse implements IResourceParse {

    @Override
    public void load(Context context, String skinPath, ParseInvokeCallback callback) {

        if (LSkinUtils.txt_isEmpty(skinPath)) {
            if (isListenerNotNull(callback))
                callback.error(SkinConfig.LOAD_ERROR_FILE_NOT_EXIT);
            
            return;
        }

        if (LSkinUtils.file_isFileNotExists(new File(skinPath))) {
            if (isListenerNotNull(callback))
                callback.error(SkinConfig.LOAD_ERROR_FILE_NOT_EXIT);
            
            return;
        }

        LResourceParseTask task = new LResourceParseTask(context, skinPath, callback);
        task.execute(skinPath);
    }

    private class LResourceParseTask extends AsyncTask<String, Void, Resources> {

        Context context;
        String skinPath;
        ParseInvokeCallback callback;

        public LResourceParseTask(Context context, String skinPath, ParseInvokeCallback callback) {
            this.context = context;
            this.skinPath = skinPath;
            this.callback = callback;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            callback.start();
        }

        @Override
        protected Resources doInBackground(String... params) {
            try {
                
                String skinPkgPath = params[0];
                String packageName = context.getPackageName();
                
                PackageManager mPm = context.getPackageManager();
                PackageInfo mInfo = mPm.getPackageArchiveInfo(skinPkgPath, PackageManager.GET_ACTIVITIES);

                boolean isDefSkin       = LSkinUtils.txt_equals(mInfo.packageName, packageName);
                boolean isVerifySkin    = LSkinUtils.txt_equals(mInfo.packageName, SkinConfig.VERIFY_SKIN_PACKAGE_NAME);
                
                if (!isDefSkin){     // if it's default skin we do not handle or isn't default skin we need go to next judge.
                    
                    if (!isVerifySkin){     // if it's not verify skin we post error and return.
                        
                        callback.error(SkinConfig.LOAD_ERROR_ILLEGAL_PACKAGE);
                        return null;
                    }
                }

                AssetManager assetManager = AssetManager.class.newInstance();
                Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
                int resultCode = (int) addAssetPath.invoke(assetManager, skinPkgPath);

                if (resultCode == 0) {
                    callback.error(SkinConfig.LOAD_ERROR_FILE_INTERNAL_ERROR);
                    return null;
                }

                Resources superRes = context.getResources();
                Resources skinResource = new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());


                return skinResource;

            } catch (Exception e) {
                e.printStackTrace();
                callback.error(SkinConfig.LOAD_ERROR_FILE_INTERNAL_ERROR);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Resources resources) {
            super.onPostExecute(resources);

            if (!LSkinUtils.obj_isNull(resources)) callback.finish(resources);

        }
    }


    private boolean isListenerNotNull(ParseInvokeCallback listener) {
        return !LSkinUtils.obj_isNull(listener);
    }
}
