package com.example.mushr.colorfool.F1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mushr.colorfool.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;


public class Fragment1 extends Fragment {

    //  图片轮播用到的变量
    private Banner mBanner;
    //  直接使用本地图片，res资源的id号；如果使用网络图片这里数组需改为字符串数组
    private ArrayList<Integer> images;
    //  轮播图片对应的 title
    private ArrayList<String> titles;

    //  下拉卡片用到的变量
    private ArrayList<CardBean> ItemList = new ArrayList<CardBean>();
    private CardAdapter adapter;
    public RecyclerView recyclerView;

    // 共用
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);

        //注意，这里一开始踩了个坑，用getActivity()一直闪退，好好思考一下
        mBanner = (Banner) view.findViewById(R.id.banner);
        //初始化数据
        initData();
        //初始化view
        initView();

        initCardItem();
        initRecyclerView();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /*** Begin---图片轮播 ***/
    private void initView() {

        //设置banner样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        //可选样式如下:
        //1. Banner.CIRCLE_INDICATOR    显示圆形指示器
        //2. Banner.NUM_INDICATOR   显示数字指示器
        //3. Banner.NUM_INDICATOR_TITLE 显示数字指示器和标题
        //4. Banner.CIRCLE_INDICATOR_TITLE  显示圆形指示器和标题
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());

        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT   指示器居左
        //Banner.CENTER 指示器居中
        //Banner.RIGHT  指示器居右
        mBanner.setIndicatorGravity(BannerConfig.CENTER);

        //设置是否允许手动滑动轮播图
        mBanner.setViewPagerIsScroll(true);

        //设置是否自动轮播（不设置则默认自动）
        mBanner.isAutoPlay(true);

        //设置轮播图片间隔时间（不设置默认为2000）
        mBanner.setDelayTime(1500);

        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        mBanner.setImages(images);

        //设置标题资源（当banner样式有显示title时）
        //mBanner.setBannerTitles(imageTitle);

        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);

        //添加点击事件
        mBanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(getContext(), "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
                    }
                });
        mBanner.start();

    }

    private void initData() {
        //设置图片资源:url或本地资源
        images = new ArrayList<>();
        images.add(R.mipmap.lunbo_1);
        images.add(R.mipmap.lunbo_2);
        images.add(R.mipmap.lunbo_3);

        //设置图片标题:自动对应
        titles = new ArrayList<>();
        titles.add("Title1");
        titles.add("Title2");
        titles.add("Title3");

    }
    /**
     * 网络加载图片
     * 使用了Glide图片加载框架
     */
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context)
                    .load(path)
                    .into(imageView);
        }

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    /*** End---图片轮播 ***/

    /*** Begin---瀑布流下拉卡片(RecyclerView + CardView) ***/
    private void initCardItem() {
        CardDao dao = new CardDao(getActivity());
        ItemList = dao.getItemList();
    }

    private void initRecyclerView() {

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setPadding(8, 8, 8, 8);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        adapter = new CardAdapter(getActivity(), ItemList);
        recyclerView.setAdapter(adapter);
        //  设置recycler的样式
        //  recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        //  设置分割线
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }
    /*** End---瀑布流下拉卡片(RecyclerView + CardView) ***/

}