package com.wander.model;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by wander on IDEA.
 * Date:15-4-23
 * Email:18955260352@163.com
 */
@Table(name = "topnews",execAfterTableCreated = "CREATE UNIQUE INDEX topnews_index ON titles(id)")
public class TopNews extends EntityBase {
    @Column(column = "topid")
    private int topid;
    @Column(column = "title")
    private String title;
    @Column(column = "cover_pic")
    private String cover_pic;
    @Column(column = "navid")
    private  int NavId;
    @Column(column = "date")
    private long date;

    public int getTopid() {
        return topid;
    }

    public void setTopid(int topid) {
        this.topid = topid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover_pic() {
        return cover_pic;
    }

    public void setCover_pic(String cover_pic) {
        this.cover_pic = cover_pic;
    }

    public int getNavId() {
        return NavId;
    }

    public void setNavId(int navId) {
        NavId = navId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TopNews{" +
                "topid=" + topid +
                ", title='" + title + '\'' +
                ", cover_pic='" + cover_pic + '\'' +
                ", NavId=" + NavId +
                ", date=" + date +
                '}';
    }
}
