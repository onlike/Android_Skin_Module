package com.lxy.demo_applicaionskin;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lxy.module.skin.SkinManager;
import com.lxy.module.skin.annotations.SkinLoadError;
import com.lxy.module.skin.base.BaseSkinActivity;
import com.lxy.module.skin.listener.SkinLoadCallback;

public class MainActivity extends BaseSkinActivity {
    
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 17;
    
    //    private String skinPath = Environment.getExternalStorageDirectory() + "/支付宝.apk";
    private String skinPath = Environment.getExternalStorageDirectory() + "/SkinFactory.skin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();
        
        initVariable();
        
        initView();
        
        bindView();
    }

    private void initVariable() {
        
    }

    private void initView() {
        
    }

    private void bindView() {
        
    }

    public void changeSkin(View view) {
        SkinManager.getInstance().load(skinPath, new SkinLoadCallback() {
            @Override
            public void loadStart() {
                loge("loadStart");
            }

            @Override
            public void loadSuccess() {
                loge("loadSuccess");
            }

            @Override
            public void loadError(@SkinLoadError int state) {
                loge("loadError :" + state);
            }
        });
    }

    public void clearSkin(View view) {
        SkinManager.getInstance().clearSkin();
    }
    
    
    private void loge(String msg){
        Log.e("onlike","-----------"+msg);
    }
    
    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE },
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }else{
            // already have permission
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {

            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED){
                    showToast("请开启权限");
                    finish();
                    return;
                }
            }
        }
    }

}
