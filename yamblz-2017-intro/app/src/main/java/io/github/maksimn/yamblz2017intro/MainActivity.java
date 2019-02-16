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
import java.util.ArrayList;
import io.github.maksimn.yamblz2017intro.data.pojo.Language;
import io.github.maksimn.yamblz2017intro.data.repository.LanguageListRepository;

public class MainActivity extends AppCompatActivity {

    private LanguageListRepository languageListRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        languageListRepository = new LanguageListRepository(this);

        final Spinner spinner = findViewById(R.id.from_language_spinner);

        ArrayList<Language> list = (ArrayList<Language>) languageListRepository.getLanguageList();

        MyCustomAdapter adapter = new MyCustomAdapter(MainActivity.this,
                R.layout.spinner_item_language);

        spinner.setAdapter(adapter);
    }

    public class MyCustomAdapter extends ArrayAdapter<Language> {

        public MyCustomAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId, languageListRepository.getLanguageList());
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
            View row = inflater.inflate(R.layout.spinner_item_language, parent, false);
            TextView label = (TextView) row.findViewById(R.id.language_name);
            String text = languageListRepository.getLanguageList().get(position).lang_name;
            label.setText(text);

            return row;
        }
    }
}
