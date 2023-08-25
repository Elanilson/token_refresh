package com.apkdoandroid.demo_token_refresh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.apkdoandroid.demo_token_refresh.model.StatusResposta;
import com.apkdoandroid.demo_token_refresh.remoto.DatavalidAPI;
import com.apkdoandroid.demo_token_refresh.remoto.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private DatavalidAPI service;
    private Button button;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = RetrofitClient.classService(DatavalidAPI.class,this);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("DEMO TOKEN REFRESH");
                Call<StatusResposta> call = service.verificarServico();
                call.enqueue(new Callback<StatusResposta>() {
                    @Override
                    public void onResponse(Call<StatusResposta> call, Response<StatusResposta> response) {
                        if(response.isSuccessful()){
                            textView.setText("SERVIÇO FUNCIONANDO!");
                            Log.d("Responde_Datavalid","Serviço funcionando");
                            Log.d("Responde_Datavalid","Code: "+response.code()+" - "+response.body());
                        }else{
                            Log.d("Responde_Datavalid","Code: "+response.code()+" - "+response.errorBody());
                            Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<StatusResposta> call, Throwable t) {
                        Log.d("Responde_Datavalid","Code: "+t.getMessage());
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}