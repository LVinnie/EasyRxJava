package com.vinnie.rxjava.easy3;

import android.support.annotation.NonNull;

public abstract class Observable<T> {

    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        return new ObservableCreate<T>(source);
    }

    public final <R> Observable<R> map(final Function<T, R> function) {

        return create(new ObservableOnSubscribe<R>() {

            @Override
            public void subscribe(@NonNull final Emitter<R> emitter) throws Exception {

                Observable.this.subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull T t) {
                        try {
                            emitter.onNext(function.apply(t));
                        } catch (Exception e) {
                            emitter.onError(e);
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onComplete() {
                        emitter.onComplete();
                    }

                    @Override
                    public void onError(Throwable error) {
                        emitter.onError(error);
                    }
                });
            }
        });
    }

    public abstract void subscribeActual(Observer<T> observer);

    public void subscribe(Observer<T> observer) {
        subscribeActual(observer);
    }
}
