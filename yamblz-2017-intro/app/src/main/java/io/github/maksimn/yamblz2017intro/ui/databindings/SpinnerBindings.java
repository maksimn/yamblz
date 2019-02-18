package io.github.maksimn.yamblz2017intro.ui.databindings;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import io.github.maksimn.yamblz2017intro.R;

public class SpinnerBindings {

    @BindingAdapter("android:entries")
    public static void setEntries(Spinner spinner, String[] langs) {
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinner.getAdapter();
        if (adapter == null) {
            spinner.setAdapter(new ArrayAdapter<>(spinner.getContext(), R.layout.item_language, langs));
            return;
        }

        adapter.clear();
        for(String lang : langs) {
            adapter.add(lang);
        }
    }

    @BindingAdapter("android:text")
    public static void setText(Spinner spinner, String langName) {
        if (spinner != null) {
            ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinner.getAdapter();
            int pos = adapter.getPosition(langName);
            spinner.setSelection(pos);
        }
    }

    @BindingAdapter("app:onItemSelected")
    public static void setItemSelectedListener(Spinner spinner,
                                               ItemSelectedListener listener) {
        if (listener == null) {
            spinner.setOnItemSelectedListener(null);
        } else {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object tag = spinner.getTag();
                    if (tag == null || (tag != null && !tag.equals(position))) {
                        listener.onItemSelected(parent.getItemAtPosition(position));
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });
        }
    }
}
