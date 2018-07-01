package me.niccorder.fakedata.news;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.niccorder.fakedata.FakeNewsApplication;
import me.niccorder.fakedata.R;
import me.niccorder.fakedata.data.NewsRepository;
import me.niccorder.fakedata.internal.MarginItemDecoration;
import me.niccorder.fakedata.model.Article;
import me.niccorder.fakedata.news.adapter.ArticleAdapter;

public class FakeNewsActivity extends AppCompatActivity {

    /**
     * The in-memory datastore of articles. We hold on to a reference here so we can determine if
     * there is any changes between the repository/adapter and notify it appropriately.
     */
    private final List<Article> articles = new ArrayList<>();

    /**
     * The news repository provides news articles.
     */
    private NewsRepository newsRepository;

    /**
     * A swipe refresh layout allows PTR (pull to refresh) on scrollable views.
     */
    private SwipeRefreshLayout refreshLayout;

    private RecyclerView newsRecycler;
    private LinearLayoutManager layoutManager;
    private ArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_news);

        // Grab a reference to our news repository
        newsRepository = ((FakeNewsApplication) getApplication()).getNewsRepository();

        // Populate with articles.
        articles.addAll(newsRepository.getArticles());

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
        layoutManager = new LinearLayoutManager(this);
        newsRecycler.setLayoutManager(layoutManager);

        // Set an item animator to make adding/removing/moving items in our adapter pretty.
        newsRecycler.setItemAnimator(new DefaultItemAnimator());

        final MarginItemDecoration marginDecoration = new MarginItemDecoration(
                this,
                R.dimen.item_article_margin_v,
                R.dimen.item_article_margin_h,
                true
        );
        newsRecycler.addItemDecoration(marginDecoration);

        // Create the article adapter.
        articleAdapter = new ArticleAdapter(articles);
        newsRecycler.setAdapter(articleAdapter);
    }
}
