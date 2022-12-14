package ru.mpei.profcom.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    private static final Gson gson = new GsonBuilder()
        .setLenient()
        .create();

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .addNetworkInterceptor(getLoggingInterceptor())
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(15, TimeUnit.SECONDS)
        .build();

    private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(Constants.ENDPOINT_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson));

    private static HttpLoggingInterceptor getLoggingInterceptor(){
        HttpLoggingInterceptor _loggingInterceptor = new HttpLoggingInterceptor();
        _loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return _loggingInterceptor;
    }

    public static <T> T createApi(Class<T> clazz){
        return retrofitBuilder.build().create(clazz);
    }
}
