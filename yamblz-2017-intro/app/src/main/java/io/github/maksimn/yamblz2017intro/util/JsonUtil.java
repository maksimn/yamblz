package io.github.maksimn.yamblz2017intro.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.maksimn.yamblz2017intro.data.pojo.Lang;

public class JsonUtil {

    public static Lang[] toLanguageList(String json) {
        return new Gson().fromJson(json, new TypeToken<Lang[]>(){}.getType());
    }
}
