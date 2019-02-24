package io.github.maksimn.yamblz2017intro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.github.maksimn.yamblz2017intro.ui.TranslatorFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment translatorFragment;

    private final static String TRANSLATOR_FRAGMENT = "TRANSLATOR_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fm = getSupportFragmentManager();

        translatorFragment = savedInstanceState == null ?
                new TranslatorFragment() :
                fm.getFragment(savedInstanceState, TRANSLATOR_FRAGMENT);

        fm.beginTransaction()
                .replace(R.id.translator, translatorFragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState, TRANSLATOR_FRAGMENT, translatorFragment);
    }
}
