package me.niccorder.news.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Set;

/**
 * Our data model for a news article.
 */
public final class Article {

    /**
     * A unique id which we can identify this news article by.
     */
    public final long id;

    /**
     * The title of the news article.
     */
    public final String title;

    /**
     * A brief description of the news article.
     */
    public final String description;

    /**
     * An image url for the news article.
     */
    public final String imageUrl;

    /**
     * A number between [0 - 1] which is a general measurement of how biased the article is toward
     * liberal policies.
     *
     * 0 means the company completely conservative.
     * 1 means the company is completely liberal.
     * null means not applicable to article.
     */
    @Nullable
    public final Float liberalBias;

    /**
     * The actual readable content of the article.
     */
    public final String content;

    /**
     * The categories that this article applies to.
     */
    public final Set<Category> categories;

    /**
     * The company which created this article.
     */
    public final Company company;

    public Article(
            long id,
            @NonNull String title,
            @NonNull String description,
            @NonNull String imageUrl,
            @Nullable Float liberalBias,
            @NonNull String content,
            @NonNull Set<Category> categories,
            @NonNull Company company
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.liberalBias = liberalBias;
        this.content = content;
        this.categories = categories;
        this.company = company;
    }

    public Article(
            long id,
            @NonNull String title,
            @NonNull String description,
            @NonNull String imageUrl,
            @NonNull String content,
            @NonNull Set<Category> categories,
            @NonNull Company company
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.liberalBias = null;
        this.content = content;
        this.categories = categories;
        this.company = company;
    }
}
