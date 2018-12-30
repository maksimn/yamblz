package io.github.maksimn.rxjava2examples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.github.maksimn.rxjava2examples.rx2course.ch02observables.Ch02Code;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ch02Code.observableCreation();
    }
}
