package me.niccorder.news.data;

import android.support.annotation.NonNull;

import java.util.List;

import me.niccorder.news.model.Article;
import me.niccorder.news.model.Category;

/**
 * API for providing data, in this case data about the news.
 *
 * We use an interface here because an interface only has method signatures. This means that there
 * is absolutely no code that is attached to an interface, and this helps keep our code clean.
 *
 * Clean code means that each component has a strong SoC (separation of concerns). A strong SoC
 * allows us to write unit tests for each component, and help us become a strong test driven
 * developer.
 *
 * TL;DR – Whenever you are hacking it out, write a good interface because it saves your ass later
 * down the road.
 *
 * @author Nicholas Corder – niccorder25@gmail.com
 */
public interface NewsRepository {

    /**
     * @return a list containing all available categories to browse.
     */
    @NonNull List<Category> getAllCategories();

    /**
     * @return all currently available articles;
     */
    @NonNull List<Article> getArticles();

    /**
     * @param category to search by.
     * @return a list of articles that are in that categories.
     */
    @NonNull List<Article> getArticlesIn(Category category);
}
