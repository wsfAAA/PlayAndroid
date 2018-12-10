package playandroid.cmcc.com.baselibrary.net.callback;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by wsf on 2018/12/10.
 */

public abstract class RxCallBack<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    protected abstract void RxOnNext(T t);

    protected abstract void RxOnError(Throwable e);
}
