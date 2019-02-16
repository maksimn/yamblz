package io.github.maksimn.yamblz2017intro.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.maksimn.yamblz2017intro.data.pojo.Language;

public class JsonUtils {

    public static Language[] toLanguageList(String json) {
        return new Gson().fromJson(json, new TypeToken<Language[]>(){}.getType());
    }
}