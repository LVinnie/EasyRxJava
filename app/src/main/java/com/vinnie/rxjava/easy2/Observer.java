package com.vinnie.rxjava.easy2;

import android.support.annotation.NonNull;

public interface Observer<T> {

    void onNext(@NonNull T t);

    void onComplete();

    void onError(Throwable error);
}