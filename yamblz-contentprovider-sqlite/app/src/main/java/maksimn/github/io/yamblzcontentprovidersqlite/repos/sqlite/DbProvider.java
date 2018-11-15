package maksimn.github.io.yamblzcontentprovidersqlite.repos.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DbProvider {

    private final DbBackend mDbBackend;
    private final ThreadPoolExecutor mExecutor;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final static DbNotificationManager mDbNotificationManager = new DbNotificationManager();

    public DbProvider(Context context) {
        mDbBackend = new DbBackend(context);
        mExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    public interface ResultCallback<T> {
        void onFinished(T result);
    }

    public void getGroups(final ResultCallback<Cursor> callback) {
        mExecutor.execute(() -> {
            final Cursor cursor = mDbBackend.getGroups();
            mHandler.post(() -> callback.onFinished(cursor));
        });
    }

    public void addGroup(final String groupName, ResultCallback<Long> callback) {
        mExecutor.execute(() -> {
            final long rowId = mDbBackend.addGroup(groupName);

            if (rowId >= 0) {
                mDbNotificationManager.notifyListeners();
            }

            callback.onFinished(rowId);
        });
    }
}
