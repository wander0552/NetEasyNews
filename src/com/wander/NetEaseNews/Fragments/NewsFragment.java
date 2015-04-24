package com.wander.NetEaseNews.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.extraSDK.pulltorefresh.PullToRefreshBase;
import com.extraSDK.pulltorefresh.PullToRefreshListView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.viewpagerindicator.LinePageIndicator;
import com.wander.Adapter.HeadViewPagerAdapter;
import com.wander.Adapter.NewsAdapter;
import com.wander.Json.JsonUtils;
import com.wander.MyUtils.UrlUtils;
import com.wander.NetEaseNews.Activities.NewsItemActivity;
import com.wander.NetEaseNews.AppCreate;
import com.wander.NetEaseNews.R;
import com.wander.model.DownNews;
import com.wander.model.Titles;
import com.wander.model.TopNews;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wander on IDEA.
 * Date:15-4-22
 * Email:18955260352@163.com
 */
public class NewsFragment extends Fragment {


    private int index, pageNum = 1;
    private List<DownNews> newsList;
    private NewsAdapter adapter;
    private PullToRefreshListView pullToRefreshListView;
    private List<TopNews> topNews;
    private DbUtils dbUtils;
    private HeadViewPagerAdapter headViewPagerAdapter;
    private ViewPager headView;
    private LinearLayout dot_layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = getArguments().getInt("index");

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = null;
        ret = inflater.inflate(R.layout.news_fragment, container, false);
        pullToRefreshListView = (PullToRefreshListView) ret.findViewById(R.id.listview_news);
        ListView listView = pullToRefreshListView.getRefreshableView();

        AppCreate appCreate = (AppCreate) (getActivity().getApplication());
        dbUtils = appCreate.db;

        newsList = new ArrayList<DownNews>();
        adapter = new NewsAdapter(getActivity(), newsList);
        listView.addHeaderView(getHeadView());
        listView.setAdapter(adapter);
        getLocalNews();
        setAutoChange();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent();
                bundle.putInt("news_id", (int) id);
                intent.putExtras(bundle);
                intent.setClass(getActivity(), NewsItemActivity.class);
                startActivity(intent);
            }
        });

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                getDownNews();
            }
        });

        pullToRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                pageNum++;
                getDownNews();
            }
        });
        return ret;
    }

    public View getHeadView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.headview, null, false);
        headView = (ViewPager) view.findViewById(R.id.news_headView);
        dot_layout = (LinearLayout) view.findViewById(R.id.linearlayout_head_dot);
        final TextView headTitle = (TextView) view.findViewById(R.id.head_title);

        topNews = new ArrayList<TopNews>();

        headViewPagerAdapter = new HeadViewPagerAdapter(topNews, getActivity());
        headView.setAdapter(headViewPagerAdapter);

        headView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                headView.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        headView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int size;

            @Override
            public void onPageScrolled(int i, float v, int i1) {
                size = topNews.size();
                if (size != 0) {
                    if (size > 1) {
                        for (int j = 0; j < size; j++) {
                            ImageView childAt = (ImageView) dot_layout.getChildAt(j);
                            childAt.setEnabled(true);
                        }
                        ImageView childAt = (ImageView) dot_layout.getChildAt(i);
                        childAt.setEnabled(false);
                    }
                    headTitle.setText(topNews.get(i).getTitle());
                }
            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        return view;

    }

    private void initDots() {

        int size = topNews.size();
        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(R.drawable.dots);
            imageView.setEnabled(true);
            imageView.setPadding(2, 0, 2, 0);
            imageView.setTag(i);
            dot_layout.addView(imageView);
        }
        dot_layout.getChildAt(0).setEnabled(false);
    }

    public void setAutoChange() {
        //设置图片自动切换

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        headView.setCurrentItem((Integer) msg.obj);
                        break;
                }
            }

        };

        new Thread(new Runnable() {

            @Override
            public void run() {
                new Timer().schedule(new TimerTask() {
                    int position;

                    public void run() {
                        Message msg = handler.obtainMessage();
                        msg.what = 1;
                        if (topNews.size() != 0) {
                            position = ++position % topNews.size();
                        } else {
                            position = 0;
                        }
                        msg.obj = position;
                        handler.sendMessage(msg);
                    }
                }, 3000, 3000);
            }
        }).start();
    }

    public void getLocalNews() {
        try {
            List<TopNews> topNewsList = dbUtils.findAll(Selector.from(TopNews.class).where("navid", "=", index).orderBy("date").limit(6));
            if (topNewsList != null) {
                reLoadHead(topNewsList);
            }
            List<DownNews> localnews = dbUtils.findAll(Selector.from(DownNews.class).where("navid", "=", index).orderBy("create_time").limit(10));
            if (localnews != null) {
                reloadList(localnews);
            }
            getDownNews();
        } catch (DbException e) {
            e.printStackTrace();
        }

    }


    public void getDownNews() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, UrlUtils.getNews(index, pageNum), new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                List<DownNews> list = new ArrayList<DownNews>();
                if (list != null) {
                    list = JsonUtils.getNews(responseInfo.result, index);
                    reloadList(list);
                    try {
                        dbUtils.saveAll(list);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
                if (pageNum == 1) {
                    List<TopNews> topNewsList = JsonUtils.getTopNews(responseInfo.result, index);
                    if (topNewsList != null) {
                        reLoadHead(topNewsList);
                        try {
                            dbUtils.saveAll(topNews);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    void reloadList(List<DownNews> list) {
        if (pageNum == 1) {
            if (newsList != null) {
                newsList.clear();
            }
        }
        newsList.addAll(list);
        adapter.notifyDataSetChanged();
        pullToRefreshListView.onRefreshComplete();
    }

    void reLoadHead(List<TopNews> list) {
        if (topNews != null) {
            topNews.clear();
        }
        topNews.addAll(list);
        initDots();
        headViewPagerAdapter.notifyDataSetChanged();
    }
}