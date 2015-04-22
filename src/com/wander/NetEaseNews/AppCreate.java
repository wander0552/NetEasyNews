package com.wander.NetEaseNews;

import android.app.Application;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;

/**
 * Created by wander on IDEA.
 * Date:15-4-22
 * Email:18955260352@163.com
 */
public class AppCreate extends Application {
    /*
        private static AppCreate appCreate;

        public static AppCreate getInstance() {
            return appCreate;
        }
    */

    @Override
    public void onCreate() {
        super.onCreate();
        getDB();
        getBitmapUtils();
    }

    //初始化DbUtils
    private DbUtils db;
    public DbUtils getDB() {
        if (db == null) {
            db = DbUtils.create(this);
        }
        return db;
    }

    //初始化BitmapUtils
    private BitmapUtils bitmapUtils;

    public BitmapUtils getBitmapUtils() {
        if (bitmapUtils == null) {
            bitmapUtils = new BitmapUtils(this);
        }
        return bitmapUtils;
    }
}
