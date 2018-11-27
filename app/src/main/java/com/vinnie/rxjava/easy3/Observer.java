package com.vinnie.rxjava.easy3;

import android.support.annotation.NonNull;

public interface Observer<T> {

    void onSubscribe(@NonNull Disposable d);

    void onNext(@NonNull T t);

    void onComplete();

    void onError(Throwable error);
}