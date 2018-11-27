package com.vinnie.rxjava.easy3.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.vinnie.rxjava.easy3.Disposable;
import com.vinnie.rxjava.easy3.Emitter;
import com.vinnie.rxjava.easy3.Function;
import com.vinnie.rxjava.easy3.Observable;
import com.vinnie.rxjava.easy3.ObservableOnSubscribe;
import com.vinnie.rxjava.easy3.Observer;

public class SampleActivity extends AppCompatActivity {

    final String TAG = SampleActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        test();
    }


    Disposable disposable;

    private void test() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull final Emitter<String> emitter) throws Exception {

                emitter.onNext("111");
                disposable.dispose();
                emitter.onNext("222");
                emitter.onComplete();
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(@NonNull String s) throws Exception {
                return "map " + s;
            }
        }).subscribe(new Observer<String>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.e(TAG, "onNext " + s);
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }

            @Override
            public void onError(Throwable error) {
                Log.e(TAG, "onError");
            }
        });
    }
}
