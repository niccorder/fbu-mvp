package me.niccorder.fakedata;

import android.app.Application;

import me.niccorder.fakedata.data.DummyNewsRepository;
import me.niccorder.fakedata.data.NewsRepository;

/**
 * The entry point of our application. Ensures that we only have one mocked news repository for our
 * entire application.
 *
 * Please look into the Singleton pattern if you are not familiar.
 */
public class FakeNewsApplication extends Application {

    /**
     * The dummy data news repository.
     */
    private NewsRepository newsRepository;

    /**
     * @return the news repository to use in the application.
     */
    public NewsRepository getNewsRepository() {
        return newsRepository;
    }

    /**
     * Similar to activies, the {@link Application} class is created when the Android OS calls the
     * method.
     *
     * The {@link Application} class does not have any destruction methods.
     */
    @Override
    public void onCreate() {
        super.onCreate();

        // Mock out our news repository.
        // the reason why we go through all this effort is that when we decide to implement
        // the news repository to provide real data from an API we will, in theory, only have
        // to swap out this one line of code instead of changing code all over the application.
        newsRepository = new DummyNewsRepository();
    }
}
