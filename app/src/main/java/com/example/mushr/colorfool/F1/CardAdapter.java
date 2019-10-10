package com.example.mushr.colorfool.F1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mushr.colorfool.Activity.PictureInfoActivity;
import com.example.mushr.colorfool.R;
import com.example.mushr.colorfool.Utils.CustomDrawableUtil;

import java.util.ArrayList;

/*** 适配器-瀑布流下拉新闻 ***/
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

        private CardView carditem;
        private ImageView image;
        private TextView imageId;
        private TextView userId;
        private TextView userName;
        private ImageButton userHead;
        private ImageButton btnStar;
        private ImageButton btnLove;

        private boolean clicked1 = false;
        private boolean clicked2 = false;

        /* 获取 item 中的控件，包括整个的 itemView 。设置点击事件*/
        public myViewHolder(View itemView) {
            super(itemView);

            carditem = (CardView) itemView.findViewById(R.id.cardview);
            image = (ImageView) itemView.findViewById(R.id.card_pic);
//            imageId = (TextView) itemView.findViewById();
//            userName = (TextView) itemView.findViewById(R.id.card_text);
//            userId = (TextView) itemView.findViewById();
            userHead = (ImageButton) itemView.findViewById(R.id.card_head);

            btnStar = (ImageButton) itemView.findViewById(R.id.card_btn_star);
            btnLove = (ImageButton) itemView.findViewById(R.id.card_btn_love);

            //  为整个的item添加点击事件，点击跳转到图片详细信息展示页面
            carditem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PictureInfoActivity.class);
                    context.startActivity(intent);
                }
            });

            //  为卡片上的 五角星（收藏）按钮添加点击事件
            btnStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!clicked1){
                        clicked1 = true;
                        btnStar.setActivated(clicked1);
                    } else {
                        clicked1 = false;
                        btnStar.setActivated(clicked1);
                    }

                }
            });
            //  为卡片上的 心形（喜欢）按钮添加点击事件
            btnLove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!clicked2){
                        clicked2 = true;
                        btnLove.setActivated(clicked2);
                    } else {
                        clicked2 = false;
                        btnLove.setActivated(clicked2);
                    }

                }
            });


        }

    }

    /* 创建布局
     *这里有一个小坑，之前一直在用居然没踩到 ??
     * 就是写布局时我发现recyclerview里面的card设置了match_parent但无论如何也顶不满整个布局的宽度，
     * 后来上百度查到这是recylerview的一个小bug，这里需要稍微改一下这个 onCreateViewHolder 函数，
     * 所以你看到的这和之前433里面的会稍有不同
     * */
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //创建自定义布局
        View view = LayoutInflater.from(context).inflate(R.layout.card_itemnew, viewGroup, false);
        myViewHolder viewHolder = new myViewHolder(view);
        return viewHolder;
    }

    /* 为 holder 填充数据，holder 是上面获取到的item中所有view控件的集合 */
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
