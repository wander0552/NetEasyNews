package com.wander.NetEaseNews.Activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wander.NetEaseNews.R;

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


}