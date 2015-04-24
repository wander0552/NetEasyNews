package com.wander.NetEaseNews.Activities;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.*;
import android.view.animation.TranslateAnimation;
import android.widget.*;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.viewpagerindicator.TabPageIndicator;
import com.wander.Adapter.TabPageIndicatorAdapter;
import com.wander.MyUtils.WidgetController;
import com.wander.NetEaseNews.R;
import com.wander.model.Titles;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wander on IDEA.
 * Date:15-4-21
 * Email:18955260352@163.com
 */
public class MainActivity extends baseActivity {

    @ViewInject(R.id.TabPage)
    private TabPageIndicator tabPageIndicator;
    @ViewInject(R.id.main_viewpager)
    private ViewPager viewPager;
    @ViewInject(R.id.underLine)
    private ImageView underLine;

/*    @ViewInject(R.id.titleBar_sliding)
    private ImageView titleBar_sliding;
    @ViewInject(R.id.titleBar_title)
    private TextView titleBar_title;
    @ViewInject(R.id.titleBar_user)
    private ImageView titleBar_user;
    @ViewInject(R.id.titleBar_setting)
    private ImageView titleBar_setting;*/


    private SlidingMenu slidingMenu;
    private DbUtils db;
    private TabPageIndicatorAdapter adapter;
    private Point outSize;
    private int width;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ViewUtils.inject(this);
        db = DbUtils.create(this);

        initIndicator();
        createLeftSlidingMenu();

    }


    private List<Titles> titles;

    private void initIndicator() {
        titles = new ArrayList<Titles>();
        outSize = new Point();
        getWindow().getWindowManager().getDefaultDisplay().getSize(outSize);
        //初始化适配器
        adapter = new TabPageIndicatorAdapter(getSupportFragmentManager(), titles);
        viewPager.setAdapter(adapter);


        tabPageIndicator.setViewPager(viewPager);

        tabPageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            /**
             * i  smarll item id
             * v  移动出去的比例
             */
            public void onPageScrolled(int i, float v, int i1) {

//                Log.d("scroll", width +"   ");
//                float move = width * v;
//                underLine.setLayoutParams(new ViewGroup.LayoutParams(width, 2));
//                underLine.setTranslationX(move);
//                WidgetController.setLayoutX(underLine, move);
            }

            @Override
            public void onPageSelected(int i) {
//                Log.d("select",i+"");

            }

            @Override
            public void onPageScrollStateChanged(int i) {
//                underLine.setTranslationX(width*i);

            }
        });
        setTitles("text");
        setTitle("网易");
        setTitleColor(Color.WHITE);
    }

    void setTitles(String nav) {
        List<Titles> list = new ArrayList<Titles>();
        try {
            List<Titles> titlesList = db.findAll(Selector.from(Titles.class).where("type", "=", nav).and("show", "=", "1"));
            if (titlesList != null) {
                list = titlesList;
                Log.d("list", list.toString());
                width = tabPageIndicator.getWidth();
                width = width / list.size();
                titles.clear();
                titles.addAll(list);
                adapter.notifyDataSetChanged();
                tabPageIndicator.notifyDataSetChanged();
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void createLeftSlidingMenu() {
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        slidingMenu.setMenu(R.layout.slidingmenu_left);
        slidingMenu.setSecondaryMenu(R.layout.slidingmenu_left);
        slidingMenu.setShadowWidth(3);
        slidingMenu
                .setBehindWidth(getResources().getDisplayMetrics().widthPixels * 3 / 5);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setFadeEnabled(true);

        slidingMenu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
            @Override
            public void onOpened() {

            }
        });
    }

    public void onMenuClick(View v) {
        switch (v.getId()) {
            case R.id.sliding_news:
                setTitles("text");
                setTitle("网易");
                slidingMenu.toggle();
                break;
            case R.id.sliding_picture:
                setTitles("pic");
                setTitle("图片");
                slidingMenu.toggle();
                break;

            case R.id.sliding_video:
                setTitles("video");
                setTitle("视频");
                slidingMenu.toggle();
                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                slidingMenu.toggle();
                return true;
            case R.id.menu_user:

                return true;
            case R.id.menu_setting:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private long start = 0, end = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (slidingMenu.isMenuShowing()) {
                slidingMenu.toggle();
                return true;
            } else if (start == 0) {
                Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
                start = System.currentTimeMillis();
                return true;
            } else {
                end = System.currentTimeMillis();
                if (end - start < 2000) {
                    finish();
                } else {
                    start = 0;
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}