package com.example.mushr.colorfool.F1;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.example.mushr.colorfool.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Fragment1 extends Fragment {

    //  图片轮播
    private List<ImageView> images;
    private List<View> dots;
    private ViewPager mViewPaper;
    private ViewPagerAdapter viewPagerAdapter;
    private ScheduledExecutorService scheduledExecutorService;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.mipmap.lunbo_1,
            R.mipmap.lunbo_2,
            R.mipmap.lunbo_3
    };

    //  下拉卡片
    private ArrayList<CardBean> ItemList = new ArrayList<CardBean>();
    private CardAdapter adapter;
    public RecyclerView recyclerView;

    // 共用
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);
        setView();
        initCardItem();
        initRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

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



    /*** Begin---图片轮播 ***/
    private void setView() {
        mViewPaper = (ViewPager) view.findViewById(R.id.vp_lunbo);

        //显示图片
        images = new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }

        //显示小白点
        dots = new ArrayList<>();
        dots.add(view.findViewById(R.id.dot_0));
        dots.add(view.findViewById(R.id.dot_1));
        dots.add(view.findViewById(R.id.dot_2));

        viewPagerAdapter = new ViewPagerAdapter(images);
        mViewPaper.setAdapter(viewPagerAdapter);

        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dots.get(position).setBackgroundResource(R.drawable.ic_point1);
                dots.get(position).setBackgroundResource(R.drawable.ic_point2);

                oldPosition = position;
                currentItem = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    //利用线性池执行动画轮播
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);
    }

    //图片轮播任务
    private class ViewPageTask implements Runnable {
        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);

        }
    }

    //接受子线程传递过来的数据

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        }

        ;

    };

    @Override
    public void onStop() {
        super.onStop();
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }

    }
    /*** End---图片轮播 ***/

}