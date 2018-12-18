package io.github.maksimn.rxjava2examples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import io.reactivex.Single;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Задача 1: создать "Observable", тип события - целое число, присвоить ему значение 42
        // "подписаться" на это значение и вывести его в лог в обработчике
        Single.just(42).subscribe(val -> Log.i("", val.toString()));
    }
}
