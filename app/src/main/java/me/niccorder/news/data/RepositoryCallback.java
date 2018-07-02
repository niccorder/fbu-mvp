package me.niccorder.news.data;

/**
 * A utility class to allow repository classes to use this as a data request callback. The reason
 * why we split these into two is because in the future we may only want to listen for errors, as
 * successful responses may not mean anything to us in the application.
 *
 * @param <T> type of the data in the response.
 */
public abstract class RepositoryCallback<T> implements SuccessCallback<T>, ErrorCallback {

    @Override
    public void onSuccess(T data) {
        // Do nothing. We leave this blank because RepositoryCallback is a utility class used in the
        // case a user only wants to implement onSuccess, or onError.
    }

    @Override
    public void onError(Throwable error) {
        // Do nothing. We leave this blank because RepositoryCallback is a utility class used in the
        // case a user only wants to implement onSuccess, or onError.
    }
}
