package com.weiun.serialize.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author William
 * @Date 2019/2/27
 * @Description 相片
 */
public class Photo implements Serializable {

    private String note;

    private String url;

    private Date time;

    @Override
    public String toString() {
        return String.format("{note:%s,url:%s,time:%s}", note, url, time);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
