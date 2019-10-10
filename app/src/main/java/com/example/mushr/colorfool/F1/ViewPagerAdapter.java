package com.example.mushr.colorfool.F1;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/*** 适配器-图片轮播 ***/
public class ViewPagerAdapter extends PagerAdapter {

    private List<ImageView> imageViewList;

    public ViewPagerAdapter(List<ImageView> images){
        this.imageViewList = images;
    }
    @Override
    public int getCount() {
        return imageViewList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView(imageViewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        view.addView(imageViewList.get(position));
        return imageViewList.get(position);
    }
}