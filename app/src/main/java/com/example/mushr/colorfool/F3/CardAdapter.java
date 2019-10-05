package com.example.mushr.colorfool.F3;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mushr.colorfool.Activity.SettingsActivity;
import com.example.mushr.colorfool.R;

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

        public myViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.card_pic);
//            imageId = (TextView) itemView.findViewById();
            userName = (TextView) itemView.findViewById(R.id.card_text);
//            userId = (TextView) itemView.findViewById();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SettingsActivity.class);
                    context.startActivity(intent);
                }
            });
        }

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //创建自定义布局
        View view = View.inflate(context, R.layout.card_item, null);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int index) {
        CardBean bean = itemList.get(index);
//        holder.imageId.setText(bean.imageId);
        holder.image.setImageDrawable(bean.image);
//        holder.userId.setText(bean.userId);
        holder.userName.setText(bean.userName);
    }
}
