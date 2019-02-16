package io.github.maksimn.yamblz2017intro.data.repository;

import android.content.Context;
import java.util.List;
import io.github.maksimn.yamblz2017intro.R;
import io.github.maksimn.yamblz2017intro.data.pojo.Language;
import io.github.maksimn.yamblz2017intro.utils.JsonUtils;
import io.github.maksimn.yamblz2017intro.utils.ResourceUtils;

public class LanguageListRepository {

    private static List<Language> smLanguageList;

    public LanguageListRepository(Context context) {
        if (smLanguageList == null) {
            final String languagesJson =
                    ResourceUtils.readRawAsString(context.getResources(), R.raw.languages);

            smLanguageList = JsonUtils.toLanguageList(languagesJson);
        }
    }

    public List<Language> getLanguageList() {
        return smLanguageList;
    }
}
