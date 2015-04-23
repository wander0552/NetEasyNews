package com.wander.model;

import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by wander on IDEA.
 * Date:15-4-23
 * Email:18955260352@163.com
 */
@Table(name = "downnews",execAfterTableCreated = "CREATE UNIQUE INDEX downnews_index ON titles(id)")
public class DownNews extends EntityBase {
    private int newid;
    private int navid;
    private String title;
    private String content;
    private String create_time;
    private String cover_pic;
    private String descript;
    private String comment_total;

    public int getNewid() {
        return newid;
    }

    public void setNewid(int newid) {
        this.newid = newid;
    }

    public int getNavid() {
        return navid;
    }

    public void setNavid(int navid) {
        this.navid = navid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCover_pic() {
        return cover_pic;
    }

    public void setCover_pic(String cover_pic) {
        this.cover_pic = cover_pic;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getComment_total() {
        return comment_total;
    }

    public void setComment_total(String comment_total) {
        this.comment_total = comment_total;
    }
}
