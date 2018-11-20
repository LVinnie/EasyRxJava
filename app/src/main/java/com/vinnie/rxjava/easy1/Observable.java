package com.vinnie.rxjava.easy1;

import android.support.annotation.NonNull;

public class Observable<T> {

    final ObservableOnSubscribe<T> source;

    private Observable(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        return new Observable<>(source);
    }

    public final <R> Observable<R> map(final Function<T, R> function) {

        return create(new ObservableOnSubscribe<R>() {

            @Override
            public void subscribe(@NonNull final Observer<R> observer) throws Exception {

                Observable.this.subscribe(new Observer<T>() {
                    @Override
                    public void onNext(@NonNull T t) {
                        try {
                            observer.onNext(function.apply(t));
                        } catch (Exception e) {
                            observer.onError(e);
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onComplete() {
                        observer.onComplete();
                    }

                    @Override
                    public void onError(Throwable error) {

                    }
                });
            }
        });
    }

    public void subscribe(Observer<T> observer) {
        try {
            source.subscribe(observer);
        } catch (Exception e) {
            observer.onError(e);
            e.printStackTrace();
        }
    }
}
