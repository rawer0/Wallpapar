package com.ovwvwvo.wallpaper.model;

import android.os.Parcel;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Copyright ©2017 by rawer
 */
@AVClassName("UrlModel")
public class UrlModel extends AVObject {
    private long id;
    private String url;
    private String desc;

    public UrlModel(){
        super();
    }

    public long getId() {
        return getLong("id");
    }

    public void setId(long id) {
        put("id", id);
    }

    public String getUrl() {
        return getString("url");
    }

    public void setUrl(String url) {
        put("url", url);
    }

    public String getDesc() {
        return getString("desc");
    }

    public void setDesc(String desc) {
        put("desc", desc);
    }

    @Override
    public String toString() {
        return "UrlModel{" +
            "id=" + getId() +
            ", url='" + getUrl() + '\'' +
            ", desc='" + getDesc() + '\'' +
            '}';
    }
}
