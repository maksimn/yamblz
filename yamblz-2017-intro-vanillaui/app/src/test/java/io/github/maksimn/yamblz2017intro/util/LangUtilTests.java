package io.github.maksimn.yamblz2017intro.util;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LangUtilTests {

    @Test
    public void determineToLang_test1() {
        String fromLang = "Русский";
        String defaultLanguage = "Английский";
        String secondDefaultLanguage = "Русский";
        String[] supportedLanguages = new String[] {"Азербайджанский", "Английский", "Баскский",
                "Башкирский", "Вьетнамский"};

        String result = LangUtil.determineToLang(fromLang, defaultLanguage, secondDefaultLanguage,
                supportedLanguages);

        assertEquals("Английский", result);
    }
}
