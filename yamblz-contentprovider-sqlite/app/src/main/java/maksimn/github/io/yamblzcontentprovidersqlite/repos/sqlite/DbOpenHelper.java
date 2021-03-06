package maksimn.github.io.yamblzcontentprovidersqlite.repos.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper implements DbContract  {

    private static final int DB_VERSION = 1;

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + GROUPS + "(" +
                Groups.ID + " INTEGER PRIMARY KEY," +
                Groups.NAME + " TEXT UNIQUE NOT NULL);");
        db.execSQL("CREATE TABLE " + LEARNERS + "(" +
                Learners.ID + " INTEGER PRIMARY KEY, " +
                Learners.NAME + " TEXT NOT NULL, " +
                Learners.PHONE + " TEXT, " +
                Learners.GROUP_ID + " INTEGER NOT NULL REFERENCES " + GROUPS + "(" + Groups.ID +
                "));"
        );
        db.execSQL("CREATE INDEX idx_" + LEARNERS + Learners.NAME + " ON " +
                LEARNERS + "(" + Learners.NAME +");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + GROUPS);
        db.execSQL("DROP TABLE " + LEARNERS);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);

        db.rawQuery("PRAGMA journal_mode = WAL;", null);
        db.execSQL("PRAGMA foreign_keys = ON;");
    }
}
