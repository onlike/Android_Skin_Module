package com.lxy.demo_applicaionskin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxy.demo_applicaionskin.R;
import com.lxy.demo_applicaionskin.bean.TestBBean;
import com.lxy.demo_applicaionskin.listener.IDataBinding;
import com.lxy.module.skin.util.LSkinUtils;

import java.util.ArrayList;

/**
 * Created by lxy on 2018/3/16.
 * 
 */
public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    
    private Context mContext;
    
    private ArrayList<TestBBean> mData ;


    public ListAdapter(Context context) {
        this.mContext   = context;
        this.mData      = new ArrayList<>();
        
        setData();
    }

    public void addData(ArrayList<TestBBean> data){
        if (LSkinUtils.array_isEmpty(data)) return;

        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_rv_list, parent, false);
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
        private ImageView imgThumb;
        
        private TextView tvTitle;

        Holder(View itemView) {
            super(itemView);
            
            imgThumb    = itemView.findViewById(R.id.img_item_list_thumb);
            tvTitle     = itemView.findViewById(R.id.tv_item_list_title);
            
        }

        @Override
        public void bindData(int position) {
            TestBBean bean = mData.get(position);
            
            tvTitle.setText(bean.title);
        }
    }


    private void setData(){

        ArrayList<TestBBean> data = new ArrayList<>();

        TestBBean bean = null;

        for (int i = 0; i < 25; i++) {

            bean            = new TestBBean();
            bean.title      = "标题：数据"+ (i+1);
            bean.drawable   = R.drawable.ic_launcher;

            data.add(bean);
        }

        addData(data);
    }
}
