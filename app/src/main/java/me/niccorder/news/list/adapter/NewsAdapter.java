package me.niccorder.news.list.adapter;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import me.niccorder.news.R;
import me.niccorder.news.model.Article;

/**
 * An implementation of the {@link RecyclerView.Adapter} which displays news articles.
 */
public class NewsAdapter extends RecyclerView.Adapter<ArticleHolder> {

    private final List<Article> articles;

    public NewsAdapter(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ArticleHolder.create(parent);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {
        final Article current = articles.get(position);

        holder.title.setText(current.title);
        holder.description.setText(current.description);

        loadAricleImage(current, holder.icon);
    }

    private void loadAricleImage(Article article, ImageView icon) {
        final int primaryColor = ContextCompat.getColor(icon.getContext(), R.color.color_primary);
        final int accentColor = ContextCompat.getColor(icon.getContext(), R.color.color_accent);
        final int primaryDarkColor = ContextCompat.getColor(
                icon.getContext(),
                R.color.color_primary_dark
        );

        final CircularProgressDrawable placeholderProgress = new CircularProgressDrawable(icon.getContext());
        placeholderProgress.setColorSchemeColors(accentColor, primaryDarkColor, primaryColor);

        final RequestOptions requestOpts = RequestOptions.fitCenterTransform()
                .optionalFitCenter()
                .placeholder(placeholderProgress)
                .error(R.drawable.ic_refresh_black_24dp);

        Glide.with(icon)
                .load(article.imageUrl)
                .apply(requestOpts)
                .into(icon);
    }
}
