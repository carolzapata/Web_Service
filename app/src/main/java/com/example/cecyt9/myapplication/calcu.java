package com.example.cecyt9.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class calcu extends AppCompatActivity {

    public EditText NoU, NoD;
    int nouno, nodos;
    double res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcu);

        NoU = (EditText) findViewById(R.id.editText3);
        NoD = (EditText) findViewById(R.id.editText4);
    }

    public void onClickMas(View v){
        nouno = Integer.parseInt(NoU.getText().toString());
        nodos = Integer.parseInt(NoD.getText().toString());
        res = nouno+nodos;
    }
    public void onClickMenos(View v){
        nouno = Integer.parseInt(NoU.getText().toString());
        nodos = Integer.parseInt(NoD.getText().toString());
        res = nouno-nodos;
    }
    public void onClickPor(View v){
        nouno = Integer.parseInt(NoU.getText().toString());
        nodos = Integer.parseInt(NoD.getText().toString());
        res = nouno*nodos;
    }
    public void onClickDivi(View v){
        nouno = Integer.parseInt(NoU.getText().toString());
        nodos = Integer.parseInt(NoD.getText().toString());
        res = nouno/nodos;
    }
}
