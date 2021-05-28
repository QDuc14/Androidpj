package com.example.nationinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class detail_country extends AppCompatActivity {

    TextView tvCountryName,tvArea,tvPopulation;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_countries);
        tvArea = (TextView)findViewById(R.id.txtAreaCountry);
        tvCountryName = (TextView)findViewById(R.id.txtNameCountry);
        tvPopulation = (TextView)findViewById(R.id.txtPopulation);
        imageView = (ImageView) findViewById(R.id.imgCountry);


        Intent intent = getIntent();
        String countryName = intent.getStringExtra("countryName");
        String areaInSqKm = intent.getStringExtra("areaInSqKm");
        String population = intent.getStringExtra("population");
        String countryCode = intent.getStringExtra("countryCode");
        countryCode.toLowerCase();
        new DownloadImageTask(imageView).execute("https://img.geonames.org/flags/x/"+countryCode.toLowerCase()+".gif");
        tvCountryName.setText(countryName);
        tvArea.setText(areaInSqKm);
        tvPopulation.setText(population);
    }
    private class  DownloadImageTask extends AsyncTask<String, Void, Bitmap>{
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            String urldisplay = strings[0];
            Bitmap mIcon11 = null;
            InputStream in = null;
            try {
                in = new URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mIcon11;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}