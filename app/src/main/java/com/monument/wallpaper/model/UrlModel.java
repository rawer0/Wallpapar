package com.monument.wallpaper.model;

import java.util.List;

/**
 * Copyright ©2017 by Teambition
 */

public class UrlModel {
    private List<String> url;

    public UrlModel(List<String> url) {
        this.url = url;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }
}
