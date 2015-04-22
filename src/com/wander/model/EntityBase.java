package com.wander.model;

/**
 * Created by wander on IDEA.
 * Date:15-4-22
 * Email:18955260352@163.com
 */
public abstract class EntityBase {
    //int 和long型默认自增
    private long id;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
