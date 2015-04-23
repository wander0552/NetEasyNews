package com.wander.Json;

import android.content.Context;
import com.lidroid.xutils.DbUtils;
import com.wander.model.DownNews;
import com.wander.model.Titles;
import com.wander.model.TopNews;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wander on IDEA.
 * Date:15-4-23
 * Email:18955260352@163.com
 */
public class JsonUtils {

    //获取导航列表
    public static List<Titles> getTitles(String jsonString, String type) {
        List<Titles> list = new ArrayList<Titles>();
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray array = object.getJSONArray("data");
            int length = array.length();
            for (int i = 0; i < length; i++) {
                Titles title = new Titles();
                JSONObject content = array.getJSONObject(i);
                title.setName(content.getString("name"));
                title.setNavId(content.getInt("cate_id"));
                title.setType(type);

                list.add(title);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<DownNews> getNews(String jsonString, int cate_id) {
        List<DownNews> list = new ArrayList<DownNews>();
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONObject data=object.getJSONObject("data");
            JSONArray array = data.getJSONArray("down_news");
            int length = array.length();
            for (int i = 0; i < length; i++) {
                DownNews downNews = new DownNews();
                JSONObject down = array.getJSONObject(i);

                downNews.setNavid(cate_id);
                downNews.setNewid(down.getInt("id"));
                downNews.setComment_total(down.getString("comment_total"));
                downNews.setTitle(down.getString("title"));
                downNews.setDescript(down.getString("descript"));
                downNews.setCreate_time(down.getString("create_time"));
                downNews.setCover_pic(down.getString("cover_pic"));
                downNews.setContent(down.getString("content"));

                list.add(downNews);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<TopNews> getTopNews(String jsonString, int cate_id) {
        List<TopNews> list = new ArrayList<TopNews>();
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONObject data=object.getJSONObject("data");
            JSONArray array = data.getJSONArray("top_news");

            int length = array.length();
            for (int i = 0; i < length; i++) {
                JSONObject top = array.getJSONObject(i);
                TopNews topNews = new TopNews();

                topNews.setCover_pic(top.getString("cover_pic"));
                topNews.setTitle(top.getString("title"));
                topNews.setDate(System.currentTimeMillis());
                topNews.setNavId(cate_id);
                topNews.setTopid(top.getInt("id"));
                list.add(topNews);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<Titles> getPic(String jsonString) {
        List<Titles> list = new ArrayList<Titles>();
        try {
            JSONObject object = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
