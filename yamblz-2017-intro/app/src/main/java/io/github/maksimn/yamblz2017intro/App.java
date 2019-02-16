package io.github.maksimn.yamblz2017intro;

import android.app.Application;
import io.github.maksimn.yamblz2017intro.utils.ResourceUtils;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        String languagesJson = ResourceUtils.readRawAsString(getResources(), R.raw.languages);
    }
}
