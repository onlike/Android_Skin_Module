package com.lxy.demo_applicaionskin.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lxy.demo_applicaionskin.R;
import com.lxy.demo_applicaionskin.adapter.MainAdapter;
import com.lxy.demo_applicaionskin.bean.TestABean;
import com.lxy.module.skin.SkinManager;
import com.lxy.module.skin.annotations.SkinLoadError;
import com.lxy.module.skin.base.BaseSkinActivity;
import com.lxy.module.skin.listener.SkinLoadCallback;

import java.util.ArrayList;

/**
 * Created by lxy on 2018/3/8.
 *
 */
public class MainActivity extends BaseSkinActivity {
    
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 17;
    
    private String skinPath = Environment.getExternalStorageDirectory() + "/SkinFactory.skin";
    
    private MainAdapter mAdapter;
    
    private RecyclerView rvCore ;


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


    public void jumpFragment(View view) {

    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();
        
        initVariable();
        
        initView();
        
        bindView();
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

    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE },
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void loge(String msg){
        Log.e("onlike","-----------"+msg);
    }

    private void setData(){
        
        ArrayList<TestABean> data = new ArrayList<>();

        TestABean bean = null;
        
        for (int i = 0; i < 20; i++) {
            
            bean            = new TestABean();
            bean.title      = "标题：数据"+ (i+1);
            bean.subTitle   = "子标题：参数"+ (i+1);
            
            data.add(bean);
        }
        
        mAdapter.addData(data);
    }

    private void initVariable() {
        mAdapter = new MainAdapter(this);
    }

    private void initView() {
        rvCore = (RecyclerView) findViewById(R.id.rv);
    }

    private void bindView() {
        rvCore.setLayoutManager(new LinearLayoutManager(this));
        rvCore.setAdapter(mAdapter);
        
        setData();
    }

}
