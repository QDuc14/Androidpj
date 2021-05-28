package com.example.nationinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nationinformation.api.ApiService;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView tvContent;
    Button getApi;
    ListView listView;
    CustomAdapter adapter;
    ArrayList<Geonames> geos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContent = (TextView)findViewById(R.id.tvContent);
        listView = (ListView)findViewById(R.id.listCoutry);
        ApiService.apiService.coverCountry("quachduc")
                .enqueue(new Callback<Countries>() {
                    @Override
                    public void onResponse(Call<Countries> call, Response<Countries> response) {
                        Toast.makeText(MainActivity.this,"Call api successfully",Toast.LENGTH_SHORT).show();
                        Countries ge = response.body();
                        if(ge != null){
                            geos = ge.getGeonames();
                            tvContent.setText(String.valueOf(ge.getGeonames().size()));
                            adapter = new CustomAdapter(MainActivity.this,R.layout.listnation,ge.getGeonames());
                            listView.setAdapter(adapter);
                        }
                    }
                    @Override
                    public void onFailure(Call<Countries> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Call api Error",Toast.LENGTH_SHORT).show();
                    }
                });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),detail_country.class);
                intent.putExtra("countryName",geos.get(position).getCountryName());
                intent.putExtra("areaInSqKm",geos.get(position).getAreaInSqKm());
                intent.putExtra("population",geos.get(position).getPopulation());
                intent.putExtra("countryCode",geos.get(position).getCountryCode());
                startActivity(intent);
            }
        });
    }
}