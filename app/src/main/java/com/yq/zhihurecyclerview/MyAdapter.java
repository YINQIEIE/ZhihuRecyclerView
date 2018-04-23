package com.yq.zhihurecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/20.
 */

public class MyAdapter extends Adapter<MyAdapter.NormalViewHolder> {

    private Context mContext;
    List<NormalBean> data = new ArrayList();

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
        for (int i = 0; i < 100; i++) {
            NormalBean normalBean = new NormalBean("中兴禁购事件还在持续发酵。今天中兴通讯再发停牌公告，称将积极、合法寻求被美国禁售的解决方案。虽然中兴只有25%-30%的零部件来自美国供应商，但手机芯片、基带芯片、射频芯片、存储芯片、手机玻璃、光学元件等最为核心的零部件都依赖于美国供应商。因此，这次禁令对中兴的影响非常大。");
            data.add(normalBean);
            if (i > 0 && i % 6 == 0)
                normalBean.setShowPic(true);
        }
    }

    @Override
    public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_normal, parent, false);
        return new NormalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NormalViewHolder holder, int position) {
        NormalBean bean = data.get(position);
        holder.tv_msg.setText(bean.getMsg());
        if (bean.isShowPic()) {
            holder.iv_ad.setVisibility(View.VISIBLE);
            holder.tv_msg.setVisibility(View.GONE);
            holder.tv_comment.setVisibility(View.GONE);
        } else {
            holder.iv_ad.setVisibility(View.GONE);
            holder.tv_msg.setVisibility(View.VISIBLE);
            holder.tv_comment.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class NormalViewHolder extends RecyclerView.ViewHolder {
        TextView tv_msg, tv_comment;
        AdImageView iv_ad;

        public NormalViewHolder(final View itemView) {
            super(itemView);
            tv_msg = itemView.findViewById(R.id.tv_msg);
            iv_ad = itemView.findViewById(R.id.iv_ad);
            tv_comment = itemView.findViewById(R.id.tv_comment);
        }
    }


}
