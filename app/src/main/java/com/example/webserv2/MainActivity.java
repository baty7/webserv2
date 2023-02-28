package com.example.webserv2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webserv2.Modelo.CambioDivisas;
import com.example.webserv2.Modelo.Rates;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

//3f68851b06a54354834d0ed34bf7a78e
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static String BASE_URL = "https://openexchangerates.org/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGetData = (Button) findViewById(R.id.btnGetData);
        EditText etAPIID = (EditText) findViewById(R.id.etAPIID);
        //TextView tvBase = (TextView) findViewById(R.id.tvBase);
        //TextView tvConvert = (TextView) findViewById(R.id.tvConvert);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        ListView listViewDivisas = findViewById(R.id.listViewDivisas);
        listViewDivisas.setAdapter(adapter);


        btnGetData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(DivisasAPI.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                DivisasAPI divisasAPI = retrofit.create(DivisasAPI.class);
                Call<CambioDivisas> call = divisasAPI.getStuff(etAPIID.getText().toString());

                call.enqueue(new Callback<CambioDivisas>() {

                    @Override
                    public void onResponse(Call<CambioDivisas> call, Response<CambioDivisas> response) {
                        List<String> divisasList = new ArrayList<>();
                        divisasList.add("EUR : " + response.body().getRates().getEUR());
                        divisasList.add("AUD : " + response.body().getRates().getAUD());
                        divisasList.add("CNY : " + response.body().getRates().getCNY());
                        divisasList.add("ISK : " + response.body().getRates().getISK());
                        divisasList.add("MXN : " + response.body().getRates().getMXN());
                        divisasList.add("PLN : " + response.body().getRates().getPLN());

                        ListView listViewDivisas = findViewById(R.id.listViewDivisas);
                        ArrayAdapter<String> adapter = (ArrayAdapter<String>) listViewDivisas.getAdapter();
                        adapter.clear();
                        adapter.addAll(divisasList);
                        adapter.notifyDataSetChanged();
                    }


                    @Override
                    public void onFailure(Call<CambioDivisas> call, Throwable t) {
                        //Log.e(TAG, "Error: " + "onFailure: Algo salio mal:" + t.getMessage());
                        Toast.makeText(MainActivity.this, "Algo salio mal:", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}