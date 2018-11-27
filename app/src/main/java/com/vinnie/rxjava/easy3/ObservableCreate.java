package com.vinnie.rxjava.easy3;


import android.support.annotation.NonNull;

public class ObservableCreate<T> extends Observable<T> {

    final ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    @Override
    public void subscribeActual(Observer<T> observer) {
        try {
            EmitterCreate emitterCreate = new EmitterCreate<T>(observer);
            observer.onSubscribe(emitterCreate);
            source.subscribe(emitterCreate);
        } catch (Exception e) {
            observer.onError(e);
            e.printStackTrace();
        }
    }

    class EmitterCreate<T> implements Emitter<T>, Disposable {

        final Observer<T> observer;
        boolean disposed = false;

        public EmitterCreate(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void onNext(@NonNull T t) {
            if (!isDisposed()) {
                observer.onNext(t);
            }
        }

        @Override
        public void onComplete() {
            if (!isDisposed()) {
                observer.onComplete();
            }
        }

        @Override
        public void onError(Throwable error) {
            observer.onError(error);
        }

        @Override
        public void dispose() {
            disposed = true;
        }

        @Override
        public boolean isDisposed() {
            return disposed;
        }
    }
}