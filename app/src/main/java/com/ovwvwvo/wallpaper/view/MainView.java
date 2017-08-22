package com.ovwvwvo.wallpaper.view;

import com.ovwvwvo.wallpaper.model.UrlModel;

/**
 * Copyright ©2017 by rawer
 */

public interface MainView {

    void showProgress();

    void hideProgress();

    void showToast(String msg);

    void LoadDataSuccess(UrlModel model);
}
