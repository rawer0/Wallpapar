package com.ovwvwvo.wallpaper.model;

/**
 * Copyright ©2017 by rawer
 */

public class UrlModel {
    private long id;
    private String url;
    private String desc;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "UrlModel{" +
            "id=" + id +
            ", url='" + url + '\'' +
            ", desc='" + desc + '\'' +
            '}';
    }
}
