package io.github.maksimn.yamblz2017intro.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import io.github.maksimn.yamblz2017intro.R;

public class LangAdapter extends ArrayAdapter<String> {

    private String[] languages;
    private LayoutInflater inflater;

    public LangAdapter(Context context, int textViewResourceId, String[] languages,
                       LayoutInflater inflater) {
        super(context, textViewResourceId, languages);
        this.languages = languages;
        this.inflater = inflater;
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

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.item_language, parent, false);
        TextView label = row.findViewById(R.id.language_name);
        String text = languages[position];
        label.setText(formattedLanguageName(text));

        return row;
    }

    private String formattedLanguageName(String langName) {
        final int ind = langName.indexOf(' ');

        return ind < 0 ? langName : langName.substring(0, ind);
    }
}
