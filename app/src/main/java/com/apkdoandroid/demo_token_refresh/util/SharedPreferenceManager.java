package com.apkdoandroid.demo_token_refresh.util;

import android.content.Context;
import android.content.SharedPreferences;


import com.apkdoandroid.demo_token_refresh.model.TokenResponse;
import com.google.gson.Gson;

public class SharedPreferenceManager {

    private static final String PREF_NAME = "PreferencesToken";
    private static final String KEY_TOKEN = "token";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public SharedPreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveToken(TokenResponse tokenResponse) {
        String tokenJson = gson.toJson(tokenResponse);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TOKEN, tokenJson);
        editor.apply();
    }

    public TokenResponse getToken() {
        String tokenJson = sharedPreferences.getString(KEY_TOKEN, null);
        if (tokenJson != null) {
            return gson.fromJson(tokenJson, TokenResponse.class);
        }
        return null;
    }

    public String getAccessToken() {
        TokenResponse tokenResponse = getToken();
        return tokenResponse != null ? tokenResponse.getAccess_token() : null;
    }

    public String getScope() {
        TokenResponse tokenResponse = getToken();
        return tokenResponse != null ? tokenResponse.getScope() : null;
    }

    public String getTokenType() {
        TokenResponse tokenResponse = getToken();
        return tokenResponse != null ? tokenResponse.getToken_type() : null;
    }

    public long getExpiresIn() {
        TokenResponse tokenResponse = getToken();
        return tokenResponse != null ? tokenResponse.getExpires_in() : 0;
    }

    public void clearToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_TOKEN);
        editor.apply();
    }
}

