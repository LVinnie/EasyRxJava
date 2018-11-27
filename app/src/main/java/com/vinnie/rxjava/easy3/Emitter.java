package com.vinnie.rxjava.easy3;

import android.support.annotation.NonNull;

public interface Emitter<T> {

    void onNext(@NonNull T t);

    void onComplete();

    void onError(Throwable error);
}
