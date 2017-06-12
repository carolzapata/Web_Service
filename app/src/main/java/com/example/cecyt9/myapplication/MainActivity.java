package com.example.cecyt9.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends Activity {

    public EditText txt, pas;
    String resultado, user, pasw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (EditText) findViewById(R.id.editText);
        pas = (EditText) findViewById(R.id.editText2);

    }

    public void onClickboton(View v) {
        user = txt.getText().toString().trim();
        pasw = pas.getText().toString().trim();
    }

    private class llamaWS extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
// TODO: attempt authentication against a network service.
//WebService - Opciones
            String NAMESPACE = "http://WebService/";
            String URL = "http://192.168.9.123:8080/WebApplication/Servicios?WSDL";
            String METHOD_NAME = "login";
            String SOAP_ACTION = "http://WebService//login";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("usuario", user);
            request.addProperty("pasword", pasw);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);


            HttpTransportSE ht = new HttpTransportSE(URL);
            try {
                ht.call(SOAP_ACTION, envelope);
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                resultado = response.toString();

                Log.i("Resultado: ", resultado);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success == false) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getApplicationContext(), " " + resultado, Toast.LENGTH_LONG).show();
                if (resultado == "Bienvenido Vania" ){
                    new AlertDialog.Builder(Lectura.this)
                        .setTitle("Resultado")
                        .setMessage("¡¡¡Felicidades!!!  Correcto")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent rom = new Intent(MainActivity.this, calcu.class);
                                startActivity(rom);
                            }
                        }).show();

                }

            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }

    }
}

