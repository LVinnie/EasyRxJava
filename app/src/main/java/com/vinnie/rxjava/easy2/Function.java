package com.vinnie.rxjava.easy2;

import android.support.annotation.NonNull;

public interface Function<T, R> {

    R apply(@NonNull T t) throws Exception;
}
