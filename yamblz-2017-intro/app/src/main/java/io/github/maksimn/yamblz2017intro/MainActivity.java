package io.github.maksimn.yamblz2017intro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import io.github.maksimn.yamblz2017intro.data.repository.LanguagesRepository;
import io.github.maksimn.yamblz2017intro.ui.LanguagesAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LanguagesRepository languagesRepository = new LanguagesRepository(this);
        final Spinner spinner = findViewById(R.id.from_language_spinner);

        LanguagesAdapter adapter = new LanguagesAdapter(this, R.layout.spinner_language,
                languagesRepository.getLanguageNames(), getLayoutInflater());

        spinner.setAdapter(adapter);
    }
}
