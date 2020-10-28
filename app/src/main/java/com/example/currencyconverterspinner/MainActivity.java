package com.example.currencyconverterspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner from;
    Spinner to;
    EditText amount;
    double value,res;
    String text;
    TextView result;
    int fr=0;
    int t=0;
    ArrayAdapter<String> adapter;

    String currencies [] = {"USD"    ,"VND"      ,"EUR"    ,"Pound"  ,"Yen"  ,"CNY"   ,"RUB"  ,"KRW"     ,"CAD"   ,"AUD"};

    double convertrate [] = {1       , 1/23176.50, 1.18    , 1.3     , 0.0096, 0.15   , 0.013 , 0.0008495, 0.76   , 0.71    ,
                             23176.50, 1         , 27420.46, 30212.89, 221.52, 3455.67, 303.15, 20.4     , 17603.94, 16535.04,
                             0.85    , 1/27420.46, 1       ,1.10     , 0.0081, 0.13   , 0.011 , 1/1366.98, 0.64   , 0.60    ,
                             0.77    , 1/30212.89, 0.91    , 1       , 0.0073, 0.11   , 0.010 , 1/1468.48, 0.58   , 0.55    ,
                             104.66  , 0.004515  , 123.77  , 136.42  , 1     , 15.60  , 1.37  , 1/11.1349, 79.45  , 74.63   ,
                             6.71    , 0.00029417, 7.93    , 8.74    , 0.064 , 1      , 0.088 , 0.0058099, 5.09   , 4.78    ,
                             76.50   , 1/297.298 , 90.47   , 99.71   , 0.73  , 11.41  , 1     , 1/14.9129, 58.05  , 54.56   ,
                             1126.56 , 0.04876   , 1332.23 , 1468.28 , 10.76 , 167.99 , 14.72 , 1        , 854.90 , 803.52  ,
                             1.32    , 0.00005788, 1.56    , 1.72    , 0.013 , 0.20   , 0.017 , 0.0011392, 1      , 0.94    ,
                             1.40    , 0.00006021, 1.66    , 1.83    , 0.013 , 0.21   , 0.018 , 0.0011966, 1.06   , 1        };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        from = (Spinner) findViewById(R.id.to);
        to = (Spinner) findViewById(R.id.fro);
        amount = (EditText) findViewById(R.id.amount);
        result = (TextView) findViewById(R.id.result);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,currencies);

        to.setAdapter(adapter);
        from.setAdapter(adapter);
        to.setOnItemSelectedListener(this);
        from.setOnItemSelectedListener(this);

        text = amount.getText().toString();
        if(!text.isEmpty())  value = Double.parseDouble(text);

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                text = amount.getText().toString();
                if(!text.isEmpty())
                    value = Double.parseDouble(text);
                else value=0;
                res = value*convertrate[fr+t*10];
                result.setText(String.format("%f",res));
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spin1 = (Spinner) parent;
        Spinner spin2 = (Spinner) parent;
        if(spin1.getId()==R.id.fro)
            fr=position;
        if(spin2.getId()==R.id.to)
            t=position;
        text = amount.getText().toString();
        if(!text.isEmpty())
            value = Double.parseDouble(text);
        else value=0;
        res = value*convertrate[fr+t*10];
        result.setText(String.format("%f",res));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}