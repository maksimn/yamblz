package io.github.maksimn.yamblz2017intro;

import android.app.Application;
import java.util.List;
import io.github.maksimn.yamblz2017intro.data.pojo.Language;
import io.github.maksimn.yamblz2017intro.utils.JsonUtils;
import io.github.maksimn.yamblz2017intro.utils.ResourceUtils;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        final String languagesJson = ResourceUtils.readRawAsString(getResources(), R.raw.languages);
        final List<Language> languageList = JsonUtils.toLanguageList(languagesJson);
    }
}
