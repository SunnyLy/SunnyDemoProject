package com.lidroid.xutils.sample.entities;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Finder;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.sqlite.FinderLazyLoader;

import java.util.Date;

/**
 * Author: wyouflf
 * Date: 13-7-25
 * Time: 下午7:06
 */
// 建议加上注解， 混淆后表名不受影响
@Table(name = "parent", execAfterTableCreated = "CREATE UNIQUE INDEX index_name ON parent(name,email)")
public class Parent extends EntityBase {

    @Column(column = "name") // 建议加上注解， 混淆后列名不受影响
    public String name;

    @Column(column = "email")
    private String email;

    @Column(column = "isAdmin")
    private boolean isAdmin;

    @Column(column = "time")
    private Date time;

    @Column(column = "date")
    private java.sql.Date date;

    @Finder(valueColumn = "id", targetColumn = "parentId")
    public FinderLazyLoader<Child> children; // 关联对象多时建议使用这种方式，延迟加载效率较高。
    //@Finder(valueColumn = "id",targetColumn = "parentId")
    //public Child children;
    //@Finder(valueColumn = "id", targetColumn = "parentId")
    //private List<Child> children;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                ", time=" + time +
                ", date=" + date +
                '}';
    }
}
