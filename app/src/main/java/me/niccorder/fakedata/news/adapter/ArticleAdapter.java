package me.niccorder.fakedata.news.adapter;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import me.niccorder.fakedata.R;
import me.niccorder.fakedata.model.Article;

/**
 * An implementation of the {@link RecyclerView.Adapter} which displays news articles.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleHolder> {

    private final List<Article> articles;

    public ArticleAdapter(List<Article> articles) {
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
        final int primaryColor = ContextCompat.getColor(icon.getContext(), R.color.colorPrimary);
        final int accentColor = ContextCompat.getColor(icon.getContext(), R.color.colorAccent);
        final int primaryDarkColor = ContextCompat.getColor(
                icon.getContext(),
                R.color.colorPrimaryDark
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
