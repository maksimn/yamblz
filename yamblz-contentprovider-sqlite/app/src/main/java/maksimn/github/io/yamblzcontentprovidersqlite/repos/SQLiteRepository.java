package maksimn.github.io.yamblzcontentprovidersqlite.repos;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import maksimn.github.io.yamblzcontentprovidersqlite.repos.pojo.Group;
import maksimn.github.io.yamblzcontentprovidersqlite.repos.sqlite.DbProvider;

public class SQLiteRepository implements IRepository {

    private DbProvider mDbProvider;

    public SQLiteRepository(Context context) {
        mDbProvider = new DbProvider(context);
    }

    @Override
    public void getGroups(Action<List<Group>> action) {
        mDbProvider.getGroups(cursor -> {
            final List<Group> groups = new ArrayList<>();

            while (cursor.moveToNext()) {
                final int id = cursor.getInt(0);
                final String name = cursor.getString(1);
                final Group group = new Group();

                group.id = id;
                group.name = name;

                groups.add(group);
            }

            action.run(groups);
        });
    }

    @Override
    public void addGroup(String groupName, Action<Long> result) {
        mDbProvider.addGroup(groupName, longNum -> { result.run(longNum); });
    }
}
