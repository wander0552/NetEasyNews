package com.wander.NetEaseNews;

import android.app.Application;
import android.os.Environment;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.cache.FileNameGenerator;
import com.wander.MyUtils.SDCardHelper;

import java.io.File;

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
    public DbUtils db;
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
            File cacheDir = new File(SDCardHelper.getSDCardPublicDir(Environment.DIRECTORY_PICTURES), File.separator + "neteasy");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }

            //设置内存缓存大小
            //指定默认的缓存路径
            String absolutePath = cacheDir.getAbsolutePath();
            bitmapUtils = new BitmapUtils(this, absolutePath,0.3f,1024*100);
            //设置加载用图片等
            bitmapUtils.configDefaultLoadingImage(R.drawable.cell_image_background);
            bitmapUtils.configDefaultLoadFailedImage(R.drawable.loadfailed);
        }
        return bitmapUtils;
    }
}
