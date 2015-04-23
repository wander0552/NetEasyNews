package com.wander.model;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

import java.util.IdentityHashMap;

/**
 * Created by wander on IDEA.
 * Date:15-4-22
 * Email:18955260352@163.com
 */

@Table(name = "titles" ,execAfterTableCreated = "CREATE UNIQUE INDEX titles_index ON titles(id)")
public class Titles extends EntityBase{
    @Column(column = "name")
    private String name;
    @Column(column = "type")
    private String type;
    //是否显示当前分类；1显示（默认全显示）  0不显示
    @Column(column = "show")
    private int show=1;
    @Column(column = "NavId")
    private int NavId;

    public int getNavId() {
        return NavId;
    }

    public void setNavId(int navId) {
        NavId = navId;
    }

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Titles{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", show=" + show +
                ", NavId=" + NavId +
                '}';
    }
}
