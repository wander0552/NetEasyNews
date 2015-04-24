package com.wander.NetEaseNews.Activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wander.NetEaseNews.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wander on IDEA.
 * Date:15-4-21
 * Email:18955260352@163.com
 */
public class baseActivity extends FragmentActivity{

    private ActionBar actionBar;
//    private SlidingMenu slidingMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);

        initTitleBar();
        setOverFlowShowingAlways();

    }
    void initTitleBar(){
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.night_top_navigation_back);
    }

 /*   private long start=0,end=0;
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
    }*/

    public void setOverFlowShowingAlways(){
        ViewConfiguration configuration=ViewConfiguration.get(this);
        try {
            Field sHasPermanentMenuKey = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            sHasPermanentMenuKey.setAccessible(true);
            sHasPermanentMenuKey.setBoolean(configuration,false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_menu, menu);
        MenuItem news_user = menu.findItem(R.id.menu_user);
        MenuItem new_setting = menu.findItem(R.id.menu_setting);

        return true;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {

        if (featureId==Window.FEATURE_ACTION_BAR&&menu!=null){
            if (menu.getClass().getSimpleName().equals("MenuBuilder")){
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu,true);

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return super.onMenuOpened(featureId, menu);
    }
}