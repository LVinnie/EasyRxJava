package com.vinnie.rxjava.easy1;

import android.support.annotation.NonNull;

public interface ObservableOnSubscribe<T> {

    void subscribe(@NonNull Observer<T> observer) throws Exception;
}
