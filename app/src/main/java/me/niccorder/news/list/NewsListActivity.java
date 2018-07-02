package me.niccorder.news.list;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.niccorder.news.NewsApplication;
import me.niccorder.news.R;
import me.niccorder.news.data.NewsRepository;
import me.niccorder.news.data.RepositoryCallback;
import me.niccorder.news.internal.MarginItemDecoration;
import me.niccorder.news.model.Article;
import me.niccorder.news.list.adapter.NewsAdapter;

/**
 * For my initial MVP, this will only display a list of articles using a RecyclerView.
 */
public class NewsListActivity extends AppCompatActivity {

    /**
     * The tag used when printing to Logcat when using {@link Log}.
     */
    private static final String TAG = NewsListActivity.class.getSimpleName();

    /**
     * The in-memory datastore of articles. We hold on to a reference here so we can determine if
     * there is any changes between the repository/adapter and notify it appropriately.
     */
    private final List<Article> articles = new ArrayList<>();

    /**
     * The news repository provides news articles. Notice how we set the type to the NewsRepository
     * interface, and not the actual implementation (in this case it's the DummyDataRepository).
     */
    private NewsRepository newsRepository;

    /**
     * A swipe refresh layout allows PTR (pull to refresh) on scrollable views.
     */
    private SwipeRefreshLayout refreshLayout;

    private RecyclerView newsRecycler;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Grab a reference to our news repository
        newsRepository = ((NewsApplication) getApplication()).getNewsRepository();

        // Grab a reference to our views.
        refreshLayout = findViewById(R.id.refresh_container);
        newsRecycler = findViewById(R.id.recycler);

        initRefreshLayout();
        initRecycler();

        // Grab all articles from our news repository and notify the adapter that we have data
        // to be displayed.
        newsRepository.getArticles(new RepositoryCallback<List<Article>>() {
            @Override
            public void onSuccess(List<Article> data) {
                super.onSuccess(data);

                articles.addAll(data);
                newsAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRefreshLayout () {
        // Sets the color wheel colors shown during a PTR (pull to refresh).
        refreshLayout.setColorSchemeResources(
                R.color.color_primary,
                R.color.color_accent,
                R.color.color_primary_dark
        );

        // A callback that is called when the swipe refresh layout has been PTR'd
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NewsListActivity.this.onRefresh();
            }
        });
    }

    private void initRecycler() {
        // Init the layout manager, and add default animation for adding/removing/moving items in
        // our recyclerview so that way they look pretty.
        newsRecycler.setLayoutManager(new LinearLayoutManager(this));
        newsRecycler.setItemAnimator(new DefaultItemAnimator());

        // Add's spacing to our items inside our recycler.
        final MarginItemDecoration marginDecoration = new MarginItemDecoration(
                this,
                R.dimen.item_article_margin_v,
                R.dimen.item_article_margin_h,
                true
        );
        newsRecycler.addItemDecoration(marginDecoration);

        // Create the article adapter, and attach it to our recycler.
        newsAdapter = new NewsAdapter(articles);
        newsRecycler.setAdapter(newsAdapter);
    }

    /**
     * To be called when a PTR even has occurred.
     */
    private void onRefresh() {
        Log.d(TAG, "onRefresh()");

        newsRepository.getArticles(new RepositoryCallback<List<Article>>() {
            @Override
            public void onSuccess(List<Article> data) {
                super.onSuccess(data);

                updateArticles(articles, data);
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable error) {
                super.onError(error);

                refreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * Updates the articles given the old and new datasets.
     *
     * @param oldArticles to be updated.
     * @param newArticles to be displayed.
     */
    private void updateArticles(
            @NonNull final List<Article> oldArticles,
            @NonNull final List<Article> newArticles
    ) {
        Log.d(TAG, "updateArticles()");

        // DiffUtil is a utility provided by the android support libraries to calculate the
        // diff between two lists for us. Thankfully, it also works seamlessly with a recyclerview
        // adapter as you will see below.
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return oldArticles.size();
            }

            @Override
            public int getNewListSize() {
                return newArticles.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                final Article oldArticle = oldArticles.get(oldItemPosition);
                final Article newArticle = newArticles.get(newItemPosition);

                return oldArticle.id == newArticle.id;
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                final Article oldArticle = oldArticles.get(oldItemPosition);
                final Article newArticle = newArticles.get(newItemPosition);

                return oldArticle.title.equals(newArticle.title)
                        && oldArticle.description.equals(newArticle.description)
                        && oldArticle.content.equals(newArticle.content)
                        && oldArticle.imageUrl.equals(newArticle.imageUrl);
            }
        });

        // Update the old articles to reflect the new articles.
        oldArticles.clear();
        oldArticles.addAll(newArticles);

        // Then dispatch the updates to the adapter. This is equivalent to calling
        // notifyDatasetChanged() on the newsAdapter
        diffResult.dispatchUpdatesTo(newsAdapter);
    }
}
