package com.wander.MyUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by wander on IDEA.
 * Date:15-4-21
 * Email:18955260352@163.com
 */
public class UrlUtils {
    //欢迎页图片
    //TODO 暂未得到接口
   public  final static String Welcome="http://s.cimg.163.com/pi/img1.126.net/channel6/2015/019253/10801476_0420.jpg.720x2147483647.75.auto.webp";

    //导航分类
   public final static String NAV="http://1000phone.net:8088/qfapp/index.php/juba/news/cate_list?type=";

    //新闻
   public final static  String NEWS="http://1000phone.net:8088/qfapp/index.php/juba/news/get_news_list?cate_id=";

   public static String getNews(int nav,int pageNum){
      return  NEWS+nav+"&p="+pageNum;
   }

}
