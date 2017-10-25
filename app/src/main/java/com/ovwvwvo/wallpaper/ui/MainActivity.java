package com.ovwvwvo.wallpaper.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.ovwvwvo.jkit.weight.ToastMaster;
import com.ovwvwvo.wallpaper.R;
import com.ovwvwvo.wallpaper.adapter.AutoLoadMoreAdapter;
import com.ovwvwvo.wallpaper.adapter.MainAdapter;
import com.ovwvwvo.wallpaper.model.UrlModel;
import com.ovwvwvo.wallpaper.presenter.LoadDataPresenterImpl;
import com.ovwvwvo.wallpaper.view.MainView;
import com.ovwvwvo.wallpaper.widget.DividerGridItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, SwipeRefreshLayout.OnRefreshListener, AutoLoadMoreAdapter.OnLoadMoreListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private DividerGridItemDecoration gridIecoration;
    private DividerItemDecoration listDecoration;

    private final static int LIST_LAYOUT = 1;
    private final static int GRID_LAYOUT = 2;
    private int currentLayout = GRID_LAYOUT;

    private MainAdapter adapter;
    private LoadDataPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();

        presenter = new LoadDataPresenterImpl(this);
        presenter.loadData(0);
    }

    private void initView() {
        initStatusBar();
        setSupportActionBar(toolbar);

        listDecoration = new DividerItemDecoration(this, LinearLayout.VERTICAL);
        gridIecoration = new DividerGridItemDecoration(this);
        recyclerView.addItemDecoration(gridIecoration);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (currentLayout == GRID_LAYOUT) {
                    boolean bo = MainAdapter.ItemType.LOAD_MORE.ordinal() == adapter.getItemViewType(position);
                    return bo ? 2 : 1;
                } else if (currentLayout == LIST_LAYOUT) {
                    return 2;
                } else return 0;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new MainAdapter(this);
        adapter.setLoadMoreListener(this);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            int statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            View rectView = new View(this);
            LinearLayout.LayoutParams params
                = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
            rectView.setLayoutParams(params);
            rectView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
            ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
            decorView.addView(rectView);
            ViewGroup rootView = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setClipToPadding(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            //setting
        } else if (id == R.id.action_layout) {
            if (currentLayout == LIST_LAYOUT) {
                adapter.setSpancount(currentLayout = GRID_LAYOUT);
                item.setIcon(R.drawable.ic_list_menu);
            } else if (currentLayout == GRID_LAYOUT) {
                adapter.setSpancount(currentLayout = LIST_LAYOUT);
                item.setIcon(R.drawable.ic_grid_menu);
            }
            swapSpanSize();
            adapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

    private void swapSpanSize() {
        recyclerView.removeItemDecoration(listDecoration);
        recyclerView.removeItemDecoration(gridIecoration);
        if (currentLayout == GRID_LAYOUT) {
            recyclerView.addItemDecoration(gridIecoration);
        } else if (currentLayout == LIST_LAYOUT) {
            recyclerView.addItemDecoration(listDecoration);
        }
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void LoadDataSuccess(List<UrlModel> models) {
        swipeRefreshLayout.setRefreshing(false);
        if (models == null) {
            ToastMaster.showToastMsg("data is null");
        } else {
            if (models.size() == 0) {
                adapter.setMoreDataAvailable(false);
                ToastMaster.showToastMsg(R.string.no_more);
            }
            if (adapter.isLoading)
                adapter.addModels(models);
            else
                adapter.setModels(models);
        }
    }

    @Override
    public void onRefresh() {
        adapter.setMoreDataAvailable(true);
        presenter.loadData(0);
    }

    @Override
    public void onLoadMore() {
        presenter.loadData(adapter.getLastId());
    }
}
