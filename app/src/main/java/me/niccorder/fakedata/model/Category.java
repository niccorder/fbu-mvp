package me.niccorder.fakedata.model;

/**
 * Represents a different type of category for a news article.
 *
 * e.g. Business, Technology, Politics, etc.
 */
public final class Category {

    /**
     * A unique id representing a news article.
     */
    public final long id;

    /**
     * The name of the category.
     */
    public final String name;

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
