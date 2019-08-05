package tech.aftershock.athenaeum.libs;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    private static final String BASE_URL = "http://192.168.0.105/";
    private static Retrofit sRetrofit = null;
    private static final int sCacheSize = 10 * 1024 * 1024;

    private static Retrofit getRetrofit(Context context) {
        if(sRetrofit == null) {
            File cacheFolder = new File(context.getCacheDir(), "cache");
            if(!cacheFolder.exists())
                cacheFolder.mkdirs();

            Cache cache = new Cache(cacheFolder, sCacheSize);
            OkHttpClient client = new OkHttpClient.Builder()
                    .cache(cache)
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();

            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return sRetrofit;
    }

    public static NetworkOperations getOperations(Context context) {
        return getRetrofit(context).create(NetworkOperations.class);
    }

    public static String getPreviousQuestionBase(){
        return BASE_URL + "contents/prev_questions/";
    }
}
