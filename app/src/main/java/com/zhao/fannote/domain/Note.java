package com.zhao.fannote.domain;

import java.util.Calendar;

/**
 * Created by Zhao on 2016/5/23.
 * 一个笔记类
 */
public class Note {
    //ID
    private int id;
    //标题
    private String title;
    //内容
    private String content;
    //添加日期
    private Calendar date;

    public Note() {
        //将hashcode设置为对象的id
        this.id = this.hashCode();
    }

    public String getTitle() {
        return title;
    }

    //get & set
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }


}
