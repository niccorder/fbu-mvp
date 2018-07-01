package me.niccorder.fakedata.news;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import me.niccorder.fakedata.FakeNewsApplication;
import me.niccorder.fakedata.R;
import me.niccorder.fakedata.data.NewsRepository;

public class FakeNewsActivity extends AppCompatActivity {

    /**
     * The news repository provides news articles.
     */
    private NewsRepository newsRepository;

    /**
     * A swipe refresh layout allows PTR (pull to refresh) on scrollable views.
     */
    private SwipeRefreshLayout refreshLayout;

    private RecyclerView newsRecycler;
    private StaggeredGridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_news);

        // Grab a reference to our news repository
        newsRepository = ((FakeNewsApplication) getApplication()).getNewsRepository();

        // Grab a reference to our views.
        refreshLayout = findViewById(R.id.refresh_container);
        newsRecycler = findViewById(R.id.recycler);

        // Sets the color wheel colors shown during a PTR (pull to refresh).
        refreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorAccent,
                R.color.colorPrimaryDark
        );

        // Init the layout manager & attach to recycler.
        layoutManager = new StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
        );
        layoutManager.setGapStrategy(
                StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        );
        newsRecycler.setLayoutManager(layoutManager);
    }
}
