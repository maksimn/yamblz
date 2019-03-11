package io.github.maksimn.yamblz2017intro.data.impl;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import io.github.maksimn.yamblz2017intro.App;
import io.github.maksimn.yamblz2017intro.R;
import io.github.maksimn.yamblz2017intro.data.pojo.LangsDirsRawData;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import okhttp3.Cache;
import retrofit2.http.Query;

public class HttpLangDataLoader {

    private interface LangDataRepository {

        @POST("api/v1.5/tr.json/getLangs")
        Single<LangsDirsRawData> fetchTranslationDirections(
                @Query("key") String key,
                @Query("ui") String ui
        );

        static boolean hasNetwork(Context context) {
            boolean isConnected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

            if (activeNetwork != null && activeNetwork.isConnected()) {
                isConnected = true;
            }

            return isConnected;
        }

        static LangDataRepository create() {
            long cacheSize = 64 * 1024;
            Context context = App.getApplication().getApplicationContext();
            Cache myCache = new Cache(context.getCacheDir(), cacheSize);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .cache(myCache)
                    .addInterceptor( chain -> {
                        Request request = chain.request();

                        if (hasNetwork(context)) {
                            request = request.newBuilder().header("Cache-Control",
                                    "public, max-age=" + 60)
                                    .build();
                        } else {
                            request = request.newBuilder().header("Cache-Control",
                                    "public, only-if-cached, max-stale=" + 60 * 60)
                                    .build();
                        }

                        return chain.proceed(request);
                    }).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://translate.yandex.net/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

            return retrofit.create(LangDataRepository.class);
        }
    }

    public Single<LangsDirsRawData> getLangsDirsRawData() {
        Resources resources = App.getApplication().getResources();
        String key = resources.getString(R.string.tr_yandex_api_key);
        String ui = "ru";

        return LangDataRepository.create().fetchTranslationDirections(key, ui);
    }
}
