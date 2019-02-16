package io.github.maksimn.yamblz2017intro;

import android.app.Application;
import io.github.maksimn.yamblz2017intro.data.repository.LanguageListRepository;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize list of all supported languages
        new LanguageListRepository(this);
    }
}
