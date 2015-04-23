package com.wander.NetEaseNews.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;
import com.extraSDK.pulltorefresh.PullToRefreshBase;
import com.extraSDK.pulltorefresh.PullToRefreshListView;
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
import com.wander.Adapter.NewsAdapter;
import com.wander.Json.JsonUtils;
import com.wander.MyUtils.UrlUtils;
import com.wander.NetEaseNews.R;
import com.wander.model.DownNews;
import com.wander.model.Titles;
import com.wander.model.TopNews;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = getArguments().getInt("index");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = null;
        ret = inflater.inflate(R.layout.news_fragment, container, false);
        pullToRefreshListView = (PullToRefreshListView) ret.findViewById(R.id.listview_news);
        ListView listView = pullToRefreshListView.getRefreshableView();
        dbUtils = DbUtils.create(getActivity());

        newsList = new ArrayList<DownNews>();
        adapter = new NewsAdapter(getActivity(), newsList);
        listView.setAdapter(adapter);
        getLocalNews();

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

    public void getLocalNews(){
        try {
            topNews=dbUtils.findAll(Selector.from(TopNews.class));
            if (topNews!=null){
//                reloadList();
            }
            else {

            }
            newsList=dbUtils.findAll(Selector.from(DownNews.class));
            if (newsList!=null){
                reloadList(newsList);
                getDownNews();
            }
           reloadList(newsList);

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

                list = JsonUtils.getNews(responseInfo.result, index);
                reloadList(list);

                try {
                    dbUtils.saveAll(list);
                } catch (DbException e) {
                    e.printStackTrace();
                }

                if (pageNum == 1) {
                    topNews = JsonUtils.getTopNews(responseInfo.result, index);
                    try {
                        dbUtils.saveAll(topNews);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
                reloadList(list);
            }
            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    void reloadList(List<DownNews> list) {
        if (pageNum == 1) {
            if (newsList!=null) {
                newsList.clear();
            }
        }
        else {
            newsList.addAll(list);
        }
        adapter.notifyDataSetChanged();
        pullToRefreshListView.onRefreshComplete();
    }
}