package com.wander.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.wander.NetEaseNews.R;
import com.wander.model.Sorts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wander on IDEA.
 * Date:15-4-22
 * Email:18955260352@163.com
 */
public class SlidingAdapter extends BaseAdapter {
    private List<Sorts> list;
    private Context context;
    private LayoutInflater inflater;

    public SlidingAdapter(Context context) {
        this.context = context;
        inflater=LayoutInflater.from(context);
        initSorts();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret=null;
        ret=inflater.inflate(R.layout.sliding_item,parent,false);
        ImageView sliding_image= (ImageView) ret.findViewById(R.id.sliding_item_image);
        TextView  sliding_name= (TextView) ret.findViewById(R.id.sliding_name);
        Sorts sorts = list.get(position);
        sliding_image.setImageResource(sorts.getId());
        sliding_name.setText(sorts.getName());
        return null;
    }

    void initSorts(){
        list=new ArrayList<Sorts>();
        String[] names=new String[]{"新闻","订阅","图片","视频","跟贴"};
        int[] id=new int[]{R.drawable.night_icon_community,R.drawable.night_icon_service,R.drawable.icon_community,R.drawable.icon_service,R.drawable.night_login_username_icon};

        for (int i = 0; i < names.length; i++) {
            Sorts sort=new Sorts();
            sort.setName(names[i]);
            sort.setId(id[i]);
            list.add(sort);
        }
    }
}
