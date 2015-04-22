package com.wander.NetEaseNews.init;

import android.content.Context;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.wander.NetEaseNews.AppCreate;
import com.wander.model.Titles;

/**
 * Created by wander on IDEA.
 * Date:15-4-22
 * Email:18955260352@163.com
 */
public class initTitles {

    public static void initNews(Context context){
        String[] titles={" 头条 "," 娱乐 "," 热点 "," 体育 "," 蚌埠 "," 财经 "," 科技 "," 段子 "," 图片 "," 汽车 "," 时尚 "," 轻松一刻 "," 军事 "," 历史 "," 房产 "," 游戏 "};


        DbUtils db=DbUtils.create(context);

        for (int i = 0; i < titles.length; i++) {
            Titles title=new Titles();
            title.setName(titles[i]);
            try {
                db.save(title);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }


}
