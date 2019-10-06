package com.example.mushr.colorfool.F3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.example.mushr.colorfool.R;

import java.util.ArrayList;

public class Fragment3 extends Fragment {
    private View view;
    private ArrayList<CardBean> ItemList = new ArrayList<CardBean>();
    private CardAdapter adapter;
    public RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment3, container, false);
        initCardItem();
        initRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initCardItem(){
        CardDao dao = new CardDao(getActivity());
        ItemList = dao.getItemList();

//        for (int i = 0; i < 10; i++) {
//            CardBean f1NewsItem = new CardBean();
//            f1NewsItem.setUserName("澎湃新闻");
//            ItemList.add(f1NewsItem);
//        }
    }

    private void initRecyclerView(){
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setPadding(8,8,8,8);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        adapter = new CardAdapter(getActivity(),ItemList);
        recyclerView.setAdapter(adapter);
        //  设置recycler的样式
        //  recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        //  设置分割线
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

    }
}