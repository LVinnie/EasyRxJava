package com.vinnie.rxjava.easy1.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.vinnie.rxjava.easy1.Function;
import com.vinnie.rxjava.easy1.Observable;
import com.vinnie.rxjava.easy1.ObservableOnSubscribe;
import com.vinnie.rxjava.easy1.Observer;

public class SampleActivity extends AppCompatActivity {

    final String TAG = SampleActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        test1();
        test2();
    }

    private void test1() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull Observer<String> observer) throws Exception {
                observer.onNext("111");
                observer.onNext("222");
                observer.onComplete();
            }
        }).subscribe(new Observer<String>() {
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

    private void test2() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull Observer<String> observer) throws Exception {
                observer.onNext("111");
                observer.onNext("222");
                observer.onComplete();
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(@NonNull String s) throws Exception {
                return "map " + s;
            }
        }).subscribe(new Observer<String>() {
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
