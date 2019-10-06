package com.example.mushr.colorfool.F3;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mushr.colorfool.Activity.SettingsActivity;
import com.example.mushr.colorfool.R;
import com.example.mushr.colorfool.Utils.CustomDrawableUtil;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.myViewHolder> {

    private Context context;
    private ArrayList<CardBean> itemList;

    public CardAdapter(Context context, ArrayList<CardBean> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        private TextView imageId;
        private ImageView image;
        private TextView userId;
        private TextView userName;
        private ImageButton userHead;

        public myViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.card_pic);
//            imageId = (TextView) itemView.findViewById();
//            userName = (TextView) itemView.findViewById(R.id.card_text);
//            userId = (TextView) itemView.findViewById();
            userHead = (ImageButton) itemView.findViewById(R.id.card_head);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SettingsActivity.class);
                    context.startActivity(intent);
                }
            });
        }

    }

    /* 这里有一个小坑，之前一直在用居然没踩到 ??
     * 就是写布局时我发现recyclerview里面的card设置了match_parent但无论如何也顶不满整个布局的宽度，
      * 后来上百度查到这是recylerview的一个小bug，这里需要稍微改一下这个 onCreateViewHolder 函数，
      * 所以你看到的这和之前433里面的会稍有不同
      * */
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //创建自定义布局
        View inflate = LayoutInflater.from(context).inflate(R.layout.card_itemnew, viewGroup, false);
        myViewHolder viewHolder = new myViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int index) {
        CardBean bean = itemList.get(index);
//        holder.imageId.setText(bean.imageId);
        holder.image.setImageDrawable(bean.image);
//        holder.userId.setText(bean.userId);
//        holder.userName.setText(bean.userName);
        holder.userHead.setBackground(CustomDrawableUtil.getRoundedBitmapDrawable(context,bean.userHead));
    }
}
