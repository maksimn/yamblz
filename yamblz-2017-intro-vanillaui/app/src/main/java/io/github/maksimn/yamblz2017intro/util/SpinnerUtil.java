package io.github.maksimn.yamblz2017intro.util;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import io.github.maksimn.yamblz2017intro.R;

public class SpinnerUtil {

    public static void setDataAndBehavior(Spinner spinner, String[] langs, String selectedLang,
                                          Action<String> onItemSelectedCallback) {
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(spinner.getContext(),
                R.layout.item_language, langs);
        final int langPos = adapter.getPosition(selectedLang);

        spinner.setAdapter(adapter);
        spinner.setSelection(langPos > -1 ? langPos : 0);
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
