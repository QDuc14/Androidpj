package com.example.money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText edtxtFrom;
    private EditText edtxtTo;
    private Spinner spinFrom;
    private Spinner spinTo;
    private Button btnConvert, btn_2way;
    private ServiceHandler serviceHandler;
    private double description = 1;
    private ArrayList<CurrencyClass> lst_curr;
    private boolean flagF = false;
    private final String url_default = "https://aud.fxexchangerate.com/rss.xml";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtxtFrom = (EditText) findViewById(R.id.edtFrom);
        edtxtTo = (EditText) findViewById(R.id.edtTo);
        btnConvert = (Button) findViewById(R.id.btnConvert);
        spinFrom = (Spinner) findViewById(R.id.spinnerFrom);
        spinTo = (Spinner) findViewById(R.id.spinnerTo);
        btn_2way = (Button) findViewById(R.id.btn2way);
        if(InternetConnection.checkInternetConnection(this)){
            getCurrency(url_default, "");
        }

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResult();
            }
        });
        btn_2way.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "", link = "";
                for(int i=0; i<lst_curr.size(); i++){
                    if(lst_curr.get(i).getTitle().equalsIgnoreCase(spinFrom.getSelectedItem().toString())){
                        title = lst_curr.get(i).getTitle();
                    }
                    if(lst_curr.get(i).getTitle().equalsIgnoreCase(spinTo.getSelectedItem().toString())){
                        link = lst_curr.get(i).getLink();
                    }
                }
                if(!title.equals("") && !link.equals(""))
                    getCurrency(link, title);
            }
        });

        spinFrom.setOnItemSelectedListener(this);
        spinTo.setOnItemSelectedListener(this);
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        lst_curr = serviceHandler.getData();
        switch (parent.getId()){
            case R.id.spinnerFrom:
                if(flagF){
                    clearTxt();
                    String spinTo_Title = spinTo.getSelectedItem().toString();
                    for(int i=0; i<lst_curr.size(); i++){
                        if(lst_curr.get(i).getTitle().equalsIgnoreCase(parent.getSelectedItem().toString())){
                            getCurrency(lst_curr.get(i).getLink(), spinTo_Title);
                            break;
                        }
                    }
                    lst_curr = serviceHandler.getData();
                    for(int j=0; j<lst_curr.size(); j++){
                        if(lst_curr.get(j).getTitle().equalsIgnoreCase(spinTo_Title)){
                            description = lst_curr.get(j).getDescription();
                            break;
                        }
                    }
                    flagF = false;
                }
                else{
                    flagF = true;
                }
                break;
            case R.id.spinnerTo:
                clearTxt();
                for(int i=0; i<lst_curr.size(); i++){
                    if(lst_curr.get(i).getTitle().equalsIgnoreCase(parent.getSelectedItem().toString())){
                        description = lst_curr.get(i).getDescription();
                        break;
                    }
                }
                break;
        }

    }
    private void getCurrency(String url, String titleTo){
        serviceHandler = new ServiceHandler(this, spinFrom, spinTo, url, titleTo);
        serviceHandler.execute();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void clearTxt(){
        edtxtFrom.setText("");
        edtxtTo.setText("");
    }

    private void getResult(){
        String temp = edtxtFrom.getText().toString();
        if(!temp.equals("")){
            double edtxt_From = Double.parseDouble(temp);
            double result = description * edtxt_From;
            edtxtTo.setText(NumberFormat.getInstance().format(result));
            String f = formatCurrencyCode(spinFrom.getSelectedItem().toString());
            String t = formatCurrencyCode(spinTo.getSelectedItem().toString());
            String edittxtFrom_Format = NumberFormat.getInstance().format(edtxt_From);
            String history = f + ":"+ edittxtFrom_Format
                    + ";"+ t +":"+edtxtTo.getText();
        }
        else{
            edtxtTo.setText("");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    private String formatCurrencyCode(String s){
        String[] t = s.split("\\(");
        return t[1].substring(0, t[1].length()-1);
    }
}