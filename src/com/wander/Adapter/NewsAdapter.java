package com.wander.Adapter;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.cache.FileNameGenerator;
import com.wander.MyUtils.SDCardHelper;
import com.wander.NetEaseNews.AppCreate;
import com.wander.NetEaseNews.R;
import com.wander.model.DownNews;

import java.io.File;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by wander on IDEA.
 * Date:15-4-23
 * Email:18955260352@163.com
 */
public class NewsAdapter extends BaseAdapter {

    private Context context;
    private List<DownNews> newsList;
    private LayoutInflater inflater;
    private ViewHolder holder = null;
    private BitmapUtils bitmapUtils;

    public NewsAdapter(Context context, List<DownNews> newsList) {
        this.context = context;
        this.newsList = newsList;
        inflater = LayoutInflater.from(context);

        AppCreate applicationContext = (AppCreate) context.getApplicationContext();
        bitmapUtils= applicationContext.getBitmapUtils();
//        bitmapUtils = new BitmapUtils(context);
    }

    @Override
    public int getCount() {
        if (newsList != null) {
            return newsList.size();
        } else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return newsList.get(position).getNewid();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        if (convertView != null) {
            ret = convertView;
        } else {
            ret = inflater.inflate(R.layout.news_item, parent, false);
        }

        holder = (ViewHolder) ret.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.news_comment = (TextView) ret.findViewById(R.id.textview_news_comment);
            holder.news_content = (TextView) ret.findViewById(R.id.textView_news_content);
            holder.news_pic = (ImageView) ret.findViewById(R.id.imageView_news_pic);
            holder.news_title = (TextView) ret.findViewById(R.id.textView_news_title);
            ret.setTag(holder);
        }
        DownNews downNews = newsList.get(position);
        if (downNews != null) {
            holder.news_comment.setText(downNews.getComment_total() + "跟帖");
            holder.news_title.setText(downNews.getTitle());
            holder.news_content.setText(downNews.getContent());
            bitmapUtils.display(holder.news_pic, downNews.getCover_pic());
        }
        return ret;
    }

    class ViewHolder {
        private ImageView news_pic;
        private TextView news_title, news_content, news_comment;
    }
}
