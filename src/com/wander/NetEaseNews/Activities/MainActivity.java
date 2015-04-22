package com.wander.NetEaseNews.Activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.viewpagerindicator.TabPageIndicator;
import com.wander.Adapter.SlidingAdapter;
import com.wander.Adapter.TabPageIndicatorAdapter;
import com.wander.NetEaseNews.R;
import com.wander.model.Titles;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by wander on IDEA.
 * Date:15-4-21
 * Email:18955260352@163.com
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    @ViewInject(R.id.TabPage)
    private TabPageIndicator tabPageIndicator;
    @ViewInject(R.id.main_viewpager)
    private ViewPager viewPager;
    @ViewInject(R.id.titleBar_sliding)
    private ImageView titleBar_sliding;
    @ViewInject(R.id.titleBar_title)
    private TextView titleBar_title;
    @ViewInject(R.id.titleBar_user)
    private ImageView titleBar_user;
    @ViewInject(R.id.titleBar_setting)
    private ImageView titleBar_setting;


    private SlidingMenu slidingMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_layout);
        ViewUtils.inject(this);

        initView();
        initIndicator();
        createLeftSlidingMenu();
        createRightSlidingMenu();

    }

    void initView(){
        titleBar_sliding.setOnClickListener(this);
        titleBar_setting.setOnClickListener(this);
        titleBar_user.setOnClickListener(this);
    }

    private List<Titles> titles;

    private void initIndicator() {
        DbUtils db = DbUtils.create(this);
        try {
            titles = db.findAll(Titles.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        //初始化适配器
        TabPageIndicatorAdapter adapter = new TabPageIndicatorAdapter(getSupportFragmentManager(), titles);
        viewPager.setAdapter(adapter);

        tabPageIndicator.setViewPager(viewPager);
        tabPageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            /**
             * i  smarll item id
             * v  移动出去的比例
             */
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void createLeftSlidingMenu() {
        initSliding();
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setMenu(R.layout.slidingmenu_left);
        slidingMenu.setShadowWidth(3);
        slidingMenu
                .setBehindWidth(getResources().getDisplayMetrics().widthPixels * 3 / 5);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
    }

    void initSliding(){
        SlidingAdapter adapter=new SlidingAdapter(this);
        LayoutInflater inflater= LayoutInflater.from(this);
        View inflate = inflater.inflate(R.layout.slidingmenu_left, null);
        ListView listView= (ListView) inflate.findViewById(R.id.sort_listview);
        listView.setAdapter(adapter);
    }

    private void createRightSlidingMenu(){
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.RIGHT);
        slidingMenu.setMenu(R.layout.slidingmenu_left);
        slidingMenu.setShadowWidth(3);
        slidingMenu
                .setBehindWidth(getResources().getDisplayMetrics().widthPixels * 3 / 5);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
    }


    private long start=0,end=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            if (slidingMenu.isMenuShowing()) {
                slidingMenu.toggle();
                return true;
            }
            else if(start==0){
                Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
                start=System.currentTimeMillis();
                return true;
            }
            else {
                end=System.currentTimeMillis();
                if (end-start<2000) {
                    finish();
                }
                else {
                    start=0;
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titleBar_setting:

                break;
            case R.id.titleBar_user:

                break;
            case  R.id.titleBar_sliding:

                break;

        }
    }
}