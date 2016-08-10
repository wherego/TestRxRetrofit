package com.htf.rxretrofit.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.camnter.easyrecyclerview.adapter.EasyRecyclerViewAdapter;
import com.camnter.easyrecyclerview.holder.EasyRecyclerViewHolder;
import com.htf.rxretrofit.R;
import com.htf.rxretrofit.bean.Beauty;
import com.htf.rxretrofit.utils.GlideUtils;

/**
 * 2016/7/20 11:06
 * Author: htf
 */
public class WelfareAdapter extends EasyRecyclerViewAdapter {

    private Context context;
    private OnClickListener listener;

    public WelfareAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int[] getItemLayouts() {
        return new int[] {R.layout.item_daily};
    }

    @Override
    public void onBindRecycleViewHolder(EasyRecyclerViewHolder viewHolder, int position) {
        Beauty beauty = this.getItem(position);
        if (beauty == null) return;
        ImageView daily_iv = viewHolder.findViewById(R.id.daily_iv);
        TextView dailyTitleTV = viewHolder.findViewById(R.id.daily_title_tv);
        
        // 图片
        if (TextUtils.isEmpty(beauty.url)) {
            GlideUtils.displayNative(daily_iv, R.mipmap.img_default_gray);
        } else {
            GlideUtils.display(daily_iv, beauty.url);
            daily_iv.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onClickPicture(beauty.url, beauty.desc, v);
                }
            });
        }

        dailyTitleTV.setText(beauty.desc);
    }

    @Override
    public int getRecycleViewItemType(int position) {
        return 0;
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }


    public interface OnClickListener {
        void onClickPicture(String url, String title, View view);
    }
}
