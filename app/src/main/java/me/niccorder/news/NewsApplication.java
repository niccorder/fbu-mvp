package me.niccorder.news;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import me.niccorder.news.data.DummyNewsRepository;
import me.niccorder.news.data.NewsRepository;

/**
 * The entry point of our application. Ensures that we only have one mocked news repository for our
 * entire application.
 *
 * Please look into the Singleton pattern if you are not familiar.
 */
public class NewsApplication extends Application {

    /**
     * The single handler used inside the entire application. This allows us to not create objects
     * in an unnecessary fashion.
     */
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    /**
     * @return the main handler instance for the application.
     */
    public Handler getMainHandler() {
        return mainHandler;
    }

    /**
     * The news repository we will be using throughout the application.
     *
     * There should only ever be one instance per-application.
     */
    private final NewsRepository newsRepository = new DummyNewsRepository(getMainHandler());

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
    }
}
