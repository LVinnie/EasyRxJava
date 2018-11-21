package com.vinnie.rxjava.easy2;


import android.support.annotation.NonNull;

public class ObservableCreate<T> extends Observable<T> {

    final ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    @Override
    public void subscribeActual(Observer<T> observer) {
        try {
            source.subscribe(new EmitterCreate<T>(observer));
        } catch (Exception e) {
            observer.onError(e);
            e.printStackTrace();
        }
    }

    class EmitterCreate<T> implements Emitter<T> {

        final Observer<T> observer;

        public EmitterCreate(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void onNext(@NonNull T t) {
            observer.onNext(t);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }

        @Override
        public void onError(Throwable error) {
            observer.onError(error);
        }
    }
}