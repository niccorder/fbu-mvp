package me.niccorder.news.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Provides a margin to all items in our RecyclerView.
 *
 * To use this, pass it a context, dimension resource for the vertical & horizontal margins of each
 * item, and finally the orientation of the recycler view.
 */
public class MarginItemDecoration extends RecyclerView.ItemDecoration {

    private final boolean vertical;
    private final int verticalMargin;
    private final int horizontalMargin;

    public MarginItemDecoration(
            @NonNull final Context context,
            @DimenRes final int verticalMargin,
            @DimenRes final int horizontalMargin
    ) {
        this(context.getResources(), verticalMargin, horizontalMargin, true);
    }

    public MarginItemDecoration(
            @NonNull final Context context,
            @DimenRes final int verticalMargin,
            @DimenRes final int horizontalMargin,
            final boolean vertical
    ) {
        this(context.getResources(), verticalMargin, horizontalMargin, vertical);
    }

    public MarginItemDecoration(
            @NonNull final Resources res,
            @DimenRes final int verticalMargin,
            @DimenRes final int horizontalMargin,
            final boolean vertical
    ) {
        this.horizontalMargin = res.getDimensionPixelSize(horizontalMargin);
        this.verticalMargin = res.getDimensionPixelSize(verticalMargin);
        this.vertical = vertical;
    }

    private boolean isEdgePosition(int position, RecyclerView parent) {
        return position == 0 || position == parent.getLayoutManager().getChildCount() - 1;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        final int position = parent.getLayoutManager().getPosition(view);
        if (isEdgePosition(position, parent)) {
            if (position == 0) {
                outRect.top = verticalMargin;
                outRect.left = horizontalMargin;
                outRect.right = vertical ? horizontalMargin : horizontalMargin / 2;
                outRect.bottom = vertical ? verticalMargin / 2 : verticalMargin;
            } else {
                outRect.top = vertical ? verticalMargin / 2 : verticalMargin;
                outRect.left = vertical ? horizontalMargin : horizontalMargin / 2;
                outRect.right = horizontalMargin;
                outRect.bottom = verticalMargin;
            }
        } else {
            outRect.top = vertical ? verticalMargin / 2 : verticalMargin;
            outRect.bottom = vertical ? verticalMargin / 2 : verticalMargin;
            outRect.left = vertical ? horizontalMargin : horizontalMargin / 2;
            outRect.right = vertical ? horizontalMargin : horizontalMargin / 2;
        }
    }
}
