package maksimn.github.io.yamblzcontentprovidersqlite.repos.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbBackend implements DbContract {

    private final DbOpenHelper mDbOpenHelper;

    DbBackend(Context context) {
        mDbOpenHelper = new DbOpenHelper(context);
    }

    public Cursor getGroups() {
        final SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();

        final Cursor cursor = db.query(GROUPS, new String[]{"*"}, null, null,
                null, null, Groups.ID);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public long addGroup(final String groupName) {
        final SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
        final ContentValues values = new ContentValues();

        values.put(Groups.NAME, groupName);

        return db.insert(GROUPS, null, values);
    }
}
