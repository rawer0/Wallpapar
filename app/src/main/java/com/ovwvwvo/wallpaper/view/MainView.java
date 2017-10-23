package com.ovwvwvo.wallpaper.view;

import com.ovwvwvo.wallpaper.model.UrlModel;

import java.util.List;

/**
 * Copyright ©2017 by rawer
 */

public interface MainView {

    void showProgress();

    void hideProgress();

    void LoadDataSuccess(List<UrlModel> models);
}
