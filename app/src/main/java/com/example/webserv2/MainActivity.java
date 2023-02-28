package com.example.webserv2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webserv2.Modelo.CambioDivisas;
import com.example.webserv2.Modelo.Rates;
import com.squareup.picasso.Picasso;

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
        // Referencia a la ImageView en el archivo XML de diseño
        ImageView imgvLogin = findViewById(R.id.imgvLogin);
        TextView tvInstructions = findViewById(R.id.tv_instructions);

        // Carga la imagen de divisas en la ImageView usando Picasso
        String imageUrl = "https://quierocredito.mx/wp-content/uploads/2020/07/colorful-abstract-background-made-different-metal-coins-american-ukrainian-bills-euro-banknotes-currency-money-finances-successful-investment-concept_127089-3599-840x560.jpg";
        Picasso.get()
                .load(imageUrl)
                .fit()
                .into(imgvLogin);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        ListView listViewDivisas = findViewById(R.id.listViewDivisas);
        listViewDivisas.setAdapter(adapter);


        listViewDivisas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                tvInstructions.setVisibility(View.VISIBLE);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("¿Estás seguro?");
                builder.setMessage("¿Deseas borrar este elemento?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.remove(adapter.getItem(position));
                        adapter.notifyDataSetChanged();
                        tvInstructions.setVisibility(View.GONE);
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


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