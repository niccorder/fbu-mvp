package me.niccorder.fakedata.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import me.niccorder.fakedata.model.Article;
import me.niccorder.fakedata.model.Category;
import me.niccorder.fakedata.model.Company;

/**
 * The implementation of the {@link NewsRepository} which provides dummy data that allows us to mock
 * out the news data. This allows for quick iteration without a backend.
 */
public class DummyNewsRepository implements NewsRepository {

    /**
     * Create the dummy image url to use for all articles.
     */
    private static final String PLACEHOLDER_URL = "http://via.placeholder.com/350x350";

    /**
     * Create dummy content to be used in all articles.
     */
    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, eu assum scaevola vituperata usu, est ex verear necessitatibus. Natum vitae vel ex, autem tritani efficiendi eu sed, cu magna tempor pertinacia qui. Ex sed veritus recusabo, purto qualisque cu pro. Mel ceteros democritum suscipiantur te.\n" +
            "\n" +
            "Est id natum ornatus omnesque. Esse unum voluptatum id mea. Est wisi noster ut. Ius erant zril accusamus ad, mei debet homero conclusionemque ut, mea quem invidunt prodesset ex. Atqui platonem eum an, ius invidunt oportere ad.\n" +
            "\n" +
            "Iusto saepe consulatu id nam, et vel tempor labores quaerendum. Mutat intellegat vel ei, no mea illum accusamus reprehendunt, vix ex malorum electram. Usu unum vituperata an, mei falli mollis et, vel tale dolorem expetenda et. Nam at novum ponderum invidunt, euripidis mnesarchum deterruisset an vis, an molestiae voluptatibus duo.\n" +
            "\n" +
            "Feugait platonem ei pri. Vis ei elit facilis, paulo aliquam blandit te has. Eripuit deseruisse dissentiunt id nec, ex nominati sapientem tincidunt mea. Iudico vidisse sea ex.\n" +
            "\n" +
            "Enim sale deserunt has no. Usu an labore noster. Rebum reque quo et, fabulas petentium per ea. Nam ea impetus iracundia, te est elit etiam viderer, mei ad ullum nobis alterum.";

    // Create all dummy categories
    private static final Category BUSINESS = new Category(0, "Business");
    private static final Category TECHNOLOGY = new Category(1, "Technology");
    private static final Category POLITICS = new Category(2, "Politics");
    private static final Category SPORTS = new Category(3, "Sports");
    private static final Category WORLD = new Category(3, "World");

    // Create all dummy companies to be used.
    private static final Company CNN = new Company(0, "CNN", .86f);
    private static final Company FOX = new Company(1, "FOX", .02f);
    private static final Company BBC = new Company(2, "BBC", .5f);

    /**
     * Used as a helper method to create an article.
     */
    private static Article createArticle(
            final long id,
            @NonNull final String title,
            @NonNull final String description,
            @NonNull final Company company,
            @Nullable final Float liberalBias,
            @NonNull final Category... categories
    ) {
        return new Article(
                id,
                title,
                description,
                PLACEHOLDER_URL,
                liberalBias,
                LOREM_IPSUM,
                new LinkedHashSet<>(Arrays.asList(categories)),
                company
        );
    }

    /**
     * Used as a helper method to create an article.
     */
    private static Article createArticle(
            final long id,
            @NonNull final String title,
            @NonNull final String description,
            @NonNull final Company company,
            @NonNull final Category... categories
    ) {
        return createArticle(id, title, description, company, null, categories);
    }

    /**
     * Creates all dummy articles.
     */
    private static final Article[] articles = new Article[]{
            createArticle(
                    0,
                    "Trump tweets again!",
                    "Another trump tweet, will he ever stop?",
                    CNN,
                    .92f,
                    POLITICS, TECHNOLOGY
            ),
            createArticle(
                    1,
                    "Brasil wins world cup!",
                    "Brasil wins after a action packed match against mexico.",
                    BBC,
                    SPORTS, WORLD
            ),
            createArticle(
                    2,
                    "GOOG stock won't stop going up!",
                    "This week GOOG closed 700 points higher.",
                    FOX,
                    BUSINESS, TECHNOLOGY, WORLD
            )
    };

    @NonNull
    @Override
    public List<Category> getAllCategories() {
        return Arrays.asList(
                BUSINESS,
                TECHNOLOGY,
                POLITICS,
                SPORTS
        );
    }

    @NonNull
    @Override
    public List<Article> getArticles() {
        return Arrays.asList(articles);
    }

    @NonNull
    @Override
    public List<Article> getArticlesIn(Category category) {
        List<Article> articles = new LinkedList<>();
        for (Article article : DummyNewsRepository.articles) {
            if (article.categories.contains(category)) {
                articles.add(article);
            }
        }

        return articles;
    }
}
