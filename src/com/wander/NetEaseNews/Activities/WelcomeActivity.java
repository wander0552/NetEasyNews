package com.wander.NetEaseNews.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import com.wander.NetEaseNews.R;
import com.wander.NetEaseNews.init.initTitles;

public class WelcomeActivity extends Activity {

    private View welcomeimage;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        welcomeimage = findViewById(R.id.welcome_image);

        initFirst();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 2000);
    }

    private void initFirst() {
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirst = preference.getBoolean("isFirst", true);
        if (isFirst) {
            initTitleDb();
        }
        SharedPreferences.Editor edit = preference.edit();
        edit.putBoolean("isFirst", false);
        edit.commit();
    }

    private void initTitleDb() {
        initTitles.initNews(getApplicationContext());
        initTitles.initPic(getApplicationContext());
        initTitles.initVideo(getApplicationContext());
    }
}
