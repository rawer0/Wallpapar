package com.monument.wallpaper.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.monument.wallpaper.R;
import com.monument.wallpaper.adapter.MainAdapter;
import com.monument.wallpaper.model.UrlModel;
import com.monument.wallpaper.presenter.LoadDataPresenterImpl;
import com.monument.wallpaper.view.MainView;
import com.ovwvwvo.jkit.weight.ToastMaster;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private MainAdapter adapter;
    private LoadDataPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new LoadDataPresenterImpl();
        initView();

        presenter.loadData();

        presenter.writeDatabase();

        presenter.readDatabase();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new MainAdapter(this);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showToast(String msg) {
        ToastMaster.showToastMsg(msg);
    }

    @Override
    public void LoadDataSuccess(UrlModel model) {
        swipeRefreshLayout.setRefreshing(false);
        adapter.setModels(model.getUrl());
    }

    @Override
    public void onRefresh() {
        presenter.loadData();
    }
}
