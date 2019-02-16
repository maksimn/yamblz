package io.github.maksimn.yamblz2017intro.data.repository;

import android.content.Context;
import io.github.maksimn.yamblz2017intro.R;
import io.github.maksimn.yamblz2017intro.data.pojo.Language;
import io.github.maksimn.yamblz2017intro.utils.JsonUtils;
import io.github.maksimn.yamblz2017intro.utils.ResourceUtils;

public class LanguagesRepository {

    private static Language[] smLanguages;

    public LanguagesRepository(Context context) {
        if (smLanguages == null) {
            final String languagesJson =
                    ResourceUtils.readRawAsString(context.getResources(), R.raw.languages);

            smLanguages = JsonUtils.toLanguageList(languagesJson);
        }
    }

    public Language[] getLanguages() {
        return smLanguages;
    }
}
