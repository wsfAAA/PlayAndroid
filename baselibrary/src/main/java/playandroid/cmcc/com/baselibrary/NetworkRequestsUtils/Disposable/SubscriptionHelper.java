package playandroid.cmcc.com.baselibrary.NetworkRequestsUtils.Disposable;

import io.reactivex.disposables.Disposable;

/**
 * Created by wsf on 2018/9/4.
 */

public interface SubscriptionHelper<T> {
    void add(Disposable subscription);

    void cancel(Disposable t);

    void cancelall();
}
