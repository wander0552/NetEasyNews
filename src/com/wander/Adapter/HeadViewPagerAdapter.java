package com.wander.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.lidroid.xutils.BitmapUtils;
import com.wander.NetEaseNews.AppCreate;
import com.wander.model.TopNews;

import java.util.List;

/**
 * Created by wander on IDEA.
 * Date:15-4-24
 * Email:18955260352@163.com
 */
public class HeadViewPagerAdapter extends PagerAdapter {
    private List<TopNews> list;
    private BitmapUtils bitmapUtils;
    private Context context;

    public HeadViewPagerAdapter(List<TopNews> list, Context context) {
        this.list = list;
        this.context = context;
        if (context == null) {
            throw new RuntimeException("");
        } else {
            AppCreate applicationContext = (AppCreate) context.getApplicationContext();
            bitmapUtils = applicationContext.getBitmapUtils();

        }
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //todo
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        bitmapUtils.display(imageView, list.get(position).getCover_pic());
        container.addView(imageView);
        return imageView;
    }
}
