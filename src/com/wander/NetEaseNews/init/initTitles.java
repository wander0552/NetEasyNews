package com.wander.NetEaseNews.init;

import android.content.Context;
import android.util.Log;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wander.Json.JsonUtils;
import com.wander.MyUtils.UrlUtils;
import com.wander.NetEaseNews.AppCreate;
import com.wander.model.Titles;

import java.util.List;

/**
 * Created by wander on IDEA.
 * Date:15-4-22
 * Email:18955260352@163.com
 */
public class initTitles {


    public static void initNews(final Context context) {
        HttpUtils httpUtils = new HttpUtils();
        final DbUtils db = DbUtils.create(context);
        String url = UrlUtils.NAV + "text";
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                List<Titles> titles = JsonUtils.getTitles(result, "text");
                try {
                    db.saveAll(titles);
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    public static void initPic(final Context context) {
        HttpUtils httpUtils = new HttpUtils();
        final DbUtils db = DbUtils.create(context);
        String url = UrlUtils.NAV + "pic";
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                List<Titles> titles = JsonUtils.getTitles(result, "pic");
                try {
                    db.saveAll(titles);
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    public static void initVideo(final Context context) {
        HttpUtils httpUtils = new HttpUtils();
        final DbUtils db = DbUtils.create(context);
        String url = UrlUtils.NAV + "video";
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                List<Titles> titles = JsonUtils.getTitles(result, "video");
                try {
                    db.saveAll(titles);
                    Log.d("video",context.toString());
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }


}


