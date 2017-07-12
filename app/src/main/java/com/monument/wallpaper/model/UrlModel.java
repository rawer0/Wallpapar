package com.monument.wallpaper.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright ©2017 by Teambition
 */
@IgnoreExtraProperties
public class UrlModel {
    private List<String> url;

    public UrlModel() {
        url = new ArrayList<>();
    }

    public UrlModel(List<String> url) {
        this.url = url;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrls(List<String> url) {
        this.url = url;
    }

    public void addUrl(String url) {
        this.url.add(url);
    }

    @Override
    public String toString() {
        return "UrlModel{" +
            "url=" + url +
            '}';
    }
}
