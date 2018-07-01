package me.niccorder.fakedata.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import me.niccorder.fakedata.R;

/**
 * A view holder for a news article to be displayed in our article adapter.
 */
class ArticleHolder extends RecyclerView.ViewHolder {

    final ImageView icon;
    final TextView title;
    final TextView description;

    public ArticleHolder(View itemView) {
        super(itemView);

        icon = itemView.findViewById(R.id.icon_iv);
        title = itemView.findViewById(R.id.title_tv);
        description = itemView.findViewById(R.id.description_tv);
    }

    /**
     * A utility method to keep our adapter's {@link RecyclerView.Adapter#onCreateViewHolder(ViewGroup, int)}
     * as terse as possible.
     */
    static ArticleHolder create(ViewGroup parent) {
        return new ArticleHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_article,
                        parent,
                        false
                )
        );
    }
}
