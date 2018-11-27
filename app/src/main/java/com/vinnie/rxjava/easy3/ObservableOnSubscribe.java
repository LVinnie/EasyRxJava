package com.vinnie.rxjava.easy3;

import android.support.annotation.NonNull;

public interface ObservableOnSubscribe<T> {

    void subscribe(@NonNull Emitter<T> emitter) throws Exception;
}
