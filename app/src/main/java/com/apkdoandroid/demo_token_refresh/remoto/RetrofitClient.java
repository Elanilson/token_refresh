package com.apkdoandroid.demo_token_refresh.remoto;

import android.content.Context;
import android.util.Log;


import com.apkdoandroid.demo_token_refresh.model.TokenManager;
import com.apkdoandroid.demo_token_refresh.util.Constantes;
import com.apkdoandroid.demo_token_refresh.util.SharedPreferenceManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit INSTANCE;
    private static SharedPreferenceManager sharedPreferenceManager;
    private static OkHttpClient.Builder clientBuilder = null;
    private static TokenManager tokenManager;
    private RetrofitClient() {
    }

    private static Retrofit getInstance(Context context) {
        sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = sharedPreferenceManager.getAccessToken();
        tokenManager = new TokenManager(context);
        Log.d("Responde_Datavalid", "access_token: " + token);


        if(INSTANCE == null){
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(Constantes.base_url_DEV) //demonstração
                    .client(clientHttp(token,tokenManager).build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

            Log.d("Responde_Datavalid", "Retrofit com cliente ");


        return INSTANCE;
    }


    public static <T> T classService(Class<T> classService, Context context) {
        return getInstance(context).create(classService);
    }


    public static OkHttpClient.Builder clientHttp(String token,TokenManager tokenManager) {

        /*
            Os valores padrão para os tempos limite do OkHttpClient são os seguintes:

            connectTimeout: 10 segundos
            readTimeout: 10 segundos
            writeTimeout: 10 segundos
         */
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        // Define os tempos limite
        client.connectTimeout(22, TimeUnit.SECONDS); // Tempo limite para estabelecer a conexão
        client.readTimeout(22, TimeUnit.SECONDS);    // Tempo limite para leitura da resposta
        client.writeTimeout(22, TimeUnit.SECONDS);   // Tempo limite para escrever a requisição


        //ESSE É USADO PARA RENOVAR O TOKEN
        client.addInterceptor(new Interceptor() {
            //TALVEZ PRECISE MELHORAR ESSA LÓGICA AQUI POR CAUSA DE ERROS, MAS TA FUNCIONANDO ATÉ ONDE TESTEI
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                if (response.code() == 401) {
                    // Feche a resposta anterior para liberar os recursos
                    response.close();
                    // Renove o token aqui e atualize o cabeçalho de autorização
                    String newToken = tokenManager.getNewToken();
                    if (newToken != null) {
                        // Atualize o cabeçalho de autorização no request
                        Request newRequest = request.newBuilder()
                                .header("Authorization", "Bearer " + newToken)
                                .build();

                        return chain.proceed(newRequest);
                    }
                }
                return response;
            }
        });


        // Esse é usado com o token normalmente até ele expirar
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()// toke fixo porque é apenas demonstração
                          .addHeader("Authorization", "Bearer 06aef429-a981-3ec5-a1f8-71d38d86481e")
                       // .addHeader("Authorization", "Bearer " + token)
                        .build();
                return chain.proceed(request);
            }

        });
        return client;
    }




}
