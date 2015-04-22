package com.wander.model;

import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by wander on IDEA.
 * Date:15-4-22
 * Email:18955260352@163.com
 */

@Table(name = "titles" ,execAfterTableCreated = "CREATE UNIQUE INDEX titles_index ON titles(name)")
public class Titles extends EntityBase{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
