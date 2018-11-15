package maksimn.github.io.yamblzcontentprovidersqlite.repos.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbBackend implements DbContract {

    private final DbOpenHelper mDbOpenHelper;

    DbBackend(Context context) {
        mDbOpenHelper = new DbOpenHelper(context);
    }

    public Cursor getGroups() {
        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();

        Cursor cursor = db.query(GROUPS, new String[]{"*"}, null, null, null, null, Groups.ID);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }
}
