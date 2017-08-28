package com.ovwvwvo.wallpaper.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright ©2017 by rawer
 */

public class UrlsResponse {
    private List<UrlModel> urlModels = new ArrayList<>();

    public UrlsResponse() {
    }

    public UrlsResponse(List<UrlModel> urlModels) {
        this.urlModels = urlModels;
    }

    public List<UrlModel> getUrlModels() {
        return urlModels;
    }

    public void setUrlModels(List<UrlModel> urlModels) {
        this.urlModels = urlModels;
    }

    public void addUrlModels(UrlModel urlModel) {
        this.urlModels.add(urlModel);
    }
}
