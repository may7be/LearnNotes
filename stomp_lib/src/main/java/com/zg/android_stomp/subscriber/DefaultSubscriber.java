package com.zg.android_stomp.subscriber;

import android.util.Log;
import rx.Subscriber;

public class DefaultSubscriber<T> extends Subscriber<T> {

    @Override
    public final void onCompleted() {

    }

    @Override
    public final void onError(Throwable e) {
        onFail(e);
    }

    @Override
    public final void onNext(T result) {
        onSuccess(result);
    }

    public void onSuccess(T t) {

    }

    public void onFail(Throwable e) {
        Log.e(DefaultSubscriber.class.getSimpleName(), "error message: " + e.getMessage(), e);
    }

}
