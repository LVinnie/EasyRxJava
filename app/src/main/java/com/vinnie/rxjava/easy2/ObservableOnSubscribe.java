package com.vinnie.rxjava.easy2;

import android.support.annotation.NonNull;

public interface ObservableOnSubscribe<T> {

    void subscribe(@NonNull Emitter<T> emitter) throws Exception;
}
