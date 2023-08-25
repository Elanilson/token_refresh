package com.apkdoandroid.demo_token_refresh.remoto;



import com.apkdoandroid.demo_token_refresh.model.StatusResposta;
import com.apkdoandroid.demo_token_refresh.model.TokenResponse;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface DatavalidAPI {

    /**
     * Permite obter informação da disponibilidade do serviço, onde é obtido através do código HTTP de retorno
     * @return
     */
    @GET("status")
    Call<StatusResposta> verificarServico();



    @FormUrlEncoded
    @POST("/token")
    Call<TokenResponse> getToken(
            @Header("Authorization") String authHeader,
            @Field("grant_type") String grantType
    );




}
