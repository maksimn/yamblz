package io.github.maksimn.yamblz2017intro;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import io.github.maksimn.yamblz2017intro.data.pojo.Language;
import io.github.maksimn.yamblz2017intro.data.repository.LanguagesRepository;

public class MainActivity extends AppCompatActivity {

    private LanguagesRepository languagesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        languagesRepository = new LanguagesRepository(this);

        final Spinner spinner = findViewById(R.id.from_language_spinner);

        MyCustomAdapter adapter = new MyCustomAdapter(this, R.layout.spinner_language);

        spinner.setAdapter(adapter);
    }

    public class MyCustomAdapter extends ArrayAdapter<Language> {

        public MyCustomAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId, languagesRepository.getLanguages());
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {

            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView,
                                  ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.spinner_language, parent, false);
            TextView label = row.findViewById(R.id.language_name);
            String text = languagesRepository.getLanguages()[position].lang_name;
            label.setText(text);

            return row;
        }
    }
}
