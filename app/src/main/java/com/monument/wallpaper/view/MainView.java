package com.monument.wallpaper.view;

import com.monument.wallpaper.model.UrlModel;

/**
 * Copyright ©2017 by rawer
 */

public interface MainView {

    void showProgress();

    void showToast(String msg);

    void LoadDataSuccess(UrlModel model);
}
