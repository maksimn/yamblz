package maksimn.github.io.yamblzcontentprovidersqlite.repos.sqlite;

import android.os.Handler;
import android.os.Looper;
import java.util.HashSet;

public class DbNotificationManager {

    private HashSet<Listener> mListeners = new HashSet<>();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Runnable mNotifyRunnable = () -> { notifyOnUiThread(); };

    public interface Listener {
        void onDataUpdated();
    }

    public void addListener(Listener listener) {
        mListeners.add(listener);
    }

    public void removeListener(Listener listener) {
        mListeners.remove(listener);
    }

    /* package-private */ void notifyListeners() {
        mHandler.removeCallbacks(mNotifyRunnable);
        mHandler.post(mNotifyRunnable);
    }

    private void notifyOnUiThread() {
        for (Listener l : mListeners) l.onDataUpdated();
    }
}
