package com.apkdoandroid.demo_token_refresh.model;

import android.content.Context;


import com.apkdoandroid.demo_token_refresh.remoto.DatavalidAPI;
import com.apkdoandroid.demo_token_refresh.util.Constantes;
import com.apkdoandroid.demo_token_refresh.util.SharedPreferenceManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Classe responsável por gerenciar o token de autenticação para a API.
 */
public class TokenManager {

    private Context context;
    private SharedPreferenceManager sharedPreferenceManager;
    private DatavalidAPI datavalidAPI;

    /**
     * Construtor da classe TokenManager.
     *
     * @param context O contexto da aplicação.
     */
    public TokenManager(Context context) {
        this.context = context;
        sharedPreferenceManager = new SharedPreferenceManager(context);
    }

    /**
     * Obtém um novo token de autenticação da API.
     *
     * @return O token de autenticação renovado.
     */
    public String getNewToken() {
        // Implemente a lógica para obter um novo token da API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.base_url_PROD)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        datavalidAPI = retrofit.create(DatavalidAPI.class);

        Call<TokenResponse> call = datavalidAPI.getToken(Constantes.token, "client_credentials");

        try {
            retrofit2.Response<TokenResponse> response = call.execute();
            if (response.isSuccessful()) {
                // Salva o token renovado nas preferências compartilhadas
                sharedPreferenceManager.saveToken(response.body());

                // Retorna o novo token
                return response.body().getAccess_token();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Retorna null em caso de falha ao obter o novo token
        return null;
    }
}

