package com.lxy.demo_applicaionskin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxy.demo_applicaionskin.R;
import com.lxy.demo_applicaionskin.bean.TestABean;
import com.lxy.demo_applicaionskin.listener.IDataBinding;
import com.lxy.module.skin.util.LSkinUtils;

import java.util.ArrayList;

/**
 * Created by lxy on 2018/3/12.
 * 
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    
    private Context mContext ;
    
    private ArrayList<TestABean> mData;

    public MainAdapter(Context context) {
        this.mContext   = context;
        this.mData      = new ArrayList<>();
        
        setData();
    }

    
    public void addData(ArrayList<TestABean> data){
        if (LSkinUtils.array_isEmpty(data)) return;
        
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_rv_main, parent, false);
        return new Holder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((IDataBinding)holder).bindData(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class Holder
            extends
                RecyclerView.ViewHolder
            implements
                IDataBinding 
    {

        private TextView tvTitle;
        
        private TextView tvSubTitle;
        
        Holder(View itemView) {
            super(itemView);
            
            tvTitle     = itemView.findViewById(R.id.tv_item_main_title);
            tvSubTitle  = itemView.findViewById(R.id.tv_item_main_sub_title);
        }

        @Override
        public void bindData(int position) {
            TestABean bean = mData.get(position);
            
            tvTitle.setText(bean.title);
            tvSubTitle.setText(bean.subTitle);
            
        }
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

        addData(data);
    }
}
