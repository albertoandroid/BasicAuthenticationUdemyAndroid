package com.androiddesdecero.basicauthentication.api;

import android.util.Base64;

import java.io.IOException;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by albertopalomarrobledo on 14/12/18.
 */

public class WebServiceBA {

    private static final String BASE_URL_BA = "http://10.0.2.2:8045/";
    private static WebServiceBA instance;
    private Retrofit retrofit;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;

    private static final String AUTH_ADMIN = "Basic " + Base64.encodeToString(("alberto:alberto").getBytes(), Base64.NO_WRAP);
    private static final String AUTH_USER = "Basic " + Base64.encodeToString(("user:b14361404c078ffd549c03db443c3fede2f3e534d73f78f77301ed97d4a436a9fd9db05ee8b325c0ad36438b43fec8510c204fc1c1edb21d0941c00e9e2c1ce2").getBytes(), Base64.NO_WRAP);

    private WebServiceBA(){
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);

        /*
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();
                Request.Builder requestBuild = original.newBuilder()
                        .addHeader("Authorization", AUTH_USER)
                        .method(original.method(), original.body());

                Request request = requestBuild.build();
                return chain.proceed(request);
            }
        });
        */

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_BA)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized WebServiceBA getInstance(){
        if(instance == null){
            instance = new WebServiceBA();
        }
        return instance;
    }

    public <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }
}
