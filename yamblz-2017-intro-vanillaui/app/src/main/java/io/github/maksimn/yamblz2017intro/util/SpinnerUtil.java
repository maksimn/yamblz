package io.github.maksimn.yamblz2017intro.util;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import io.github.maksimn.yamblz2017intro.R;

public class SpinnerUtil {

    public static void setDataAndBehavior(Spinner spinner, String[] languages,
                                          String selectedLanguage,
                                          Action<String> onItemSelectedCallback) {
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(spinner.getContext(),
                R.layout.item_language, languages);
        final int pos = adapter.getPosition(selectedLanguage);

        spinner.setAdapter(adapter);
        spinner.setSelection(pos > -1 ? pos : 0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final TextView spinnerTextView = (TextView) view;

                if (spinnerTextView != null) {
                    final String languageName = spinnerTextView.getText().toString();

                    if (onItemSelectedCallback != null) {
                        onItemSelectedCallback.run(languageName);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}
