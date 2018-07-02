package me.niccorder.news.data;

/**
 * A callback to be used when we have successfully retrieved data from any repository in our
 * application.
 */
public interface SuccessCallback<T> {

    /**
     * Called when the data request returned successfully.
     *
     * @param type of the data returned.
     */
    void onSuccess(T type);
}
