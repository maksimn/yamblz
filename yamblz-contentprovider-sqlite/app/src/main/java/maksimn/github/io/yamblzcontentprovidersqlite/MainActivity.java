package maksimn.github.io.yamblzcontentprovidersqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import maksimn.github.io.yamblzcontentprovidersqlite.repos.IRepository;
import maksimn.github.io.yamblzcontentprovidersqlite.repos.SQLiteRepository;
import maksimn.github.io.yamblzcontentprovidersqlite.repos.pojo.Group;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final IRepository repository = new SQLiteRepository(this);

        repository.addGroup("Начинахи во вт и чт", i -> {
            repository.addGroup("Продолжающие", i2 -> {
                repository.getGroups(groups -> {
                    List<Group> res = groups;
                    int s = res.size();
                });
            });
        });

    }
}
