package com.example.mushr.colorfool;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment1 f1;     // Fragment1 对象
    private Fragment2 f2;     // Fragment2 对象
    private Fragment3 f3;     // Fragment3 对象
    private Fragment[] fragments;
    private int lastShowFragment = 0;   // 表示最后一个显示的 Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        /*沉浸式状态栏*/
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        toggle.syncState();

        drawer.addDrawerListener(toggle);

        Resources resources = MainActivity.this.getResources();
        Drawable drawable = resources.getDrawable(R.drawable.ic_nav_head);
        int size = 42;
        CircleDrawable circleDrawable = new CircleDrawable(drawable,MainActivity.this,size);
        toolbar.setNavigationIcon(circleDrawable);

        //  获取侧拉抽屉
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_left);
        navigationView.setItemIconTintList(null);       // 屏蔽图标自带颜色
        navigationView.setNavigationItemSelectedListener(this);

        //  获取底部导航栏
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_bottom);
        bottomNavigationView.setItemIconTintList(null);       // 屏蔽图标自带颜色
        refreshItemIcon(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(ClickToChange);//点击事件
        initFragments();
    }

    public void refreshItemIcon(BottomNavigationView bottomNavigationView) {
        MenuItem item1 = bottomNavigationView.getMenu().findItem(R.id.item1);
        item1.setIcon(R.drawable.ic_bottom_home1);
        MenuItem item2 = bottomNavigationView.getMenu().findItem(R.id.item3);
        item2.setIcon(R.drawable.ic_bottom_around1);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener ClickToChange
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.item1:
                    if (lastShowFragment != 0) {
                        item.setIcon(R.drawable.ic_bottom_home2);
                        switchFragment(lastShowFragment, 0);
                        lastShowFragment = 0;
                    }
                    return true;
                case R.id.item2:
                    if (lastShowFragment != 1) {
                        item.setIcon(R.drawable.ic_bottom_add);
                        switchFragment(lastShowFragment, 1);
                        lastShowFragment = 1;
                    }
                    return true;
                case R.id.item3:
                    if (lastShowFragment != 2) {
                        item.setIcon(R.drawable.ic_bottom_around2);
                        switchFragment(lastShowFragment, 2);
                        lastShowFragment = 2;
                    }
                    return true;
            }
            return false;
        }

    };

    public void switchFragment(int lastIndex, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.fm_container, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

    private void initFragments() {
        f1 = new Fragment1();
        f2 = new Fragment2();
        f3 = new Fragment3();
        fragments = new Fragment[]{f1, f2, f3};
        lastShowFragment = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fm_container, f1)
                .show(f1)
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_more, menu);
        return true;
    }

    /**
     * 解决Toolbar中Menu无法同时显示图标和文字的问题
     * */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.toolbar_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*** 侧拉抽屉，可为每个按钮增加点击事件 ***/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle main_bottom_navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_1) {
            // Handle the camera action
        } else if (id == R.id.nav_2) {

        } else if (id == R.id.nav_3) {

        } else if (id == R.id.nav_4) {

        } else if (id == R.id.nav_5) {

        } else if (id == R.id.nav_6) {

        } else if (id == R.id.nav_7) {
//            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
//            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}