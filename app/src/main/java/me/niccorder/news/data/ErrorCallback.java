package me.niccorder.news.data;

/**
 * A callback to be used when we have successfully retrieved data from any repository in our
 * application.
 */
public interface ErrorCallback {

    /**
     * Called when there is an error when gathering data from a repository request.
     *
     * @param error that occurred when gathering data.
     */
    void onError(Throwable error);
}
