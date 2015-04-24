package com.wander.NetEaseNews.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wander.MyView.DetailNewsImageshow;
import com.wander.NetEaseNews.R;

/**
 * Created by wander on IDEA.
 * Date:15-4-24
 * Email:18955260352@163.com
 */
public class NewsItemActivity extends Activity {
    @ViewInject(R.id.news_detail_image)
    private DetailNewsImageshow news_detail_news;
    @ViewInject(R.id.news_detail_title)
    private TextView news_detail_title;
    @ViewInject(R.id.news_createTime)
    private TextView news_createTime;
    @ViewInject(R.id.news_detail_content)
    private TextView news_detail_content;

    private int news_id;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_item_layout);

        news_id = getIntent().getExtras().getInt("news_id");
        Log.d("id",news_id+"");

    }

    public void setNews(){

    }
}