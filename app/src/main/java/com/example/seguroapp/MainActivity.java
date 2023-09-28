package com.example.seguroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.seguroapp.clases.Seguro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //Variables de objeto
    EditText edtMarca,edtModelo,edtAnho;
    Button btnCalcular;
    Seguro seguro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vincularElementos();
        //Instanciamos la clase seguro
        seguro = new Seguro();
        obtenerValorUf();
    }

    private void obtenerValorUf() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://mindicador.cl/api/uf/28-09-2023";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // manejamis la respuesta
                        try {
                            //Creamos una variable de objeto de la clase JSONObject
                            JSONObject respuestaJson = new JSONObject(response);
                            //Nos situamos sobre un componente del objeto Json
                            JSONArray arrayJson = respuestaJson.getJSONArray("serie");
                            //Nos posicionamos en el primer elemento del arreglo (indice base 0 )
                            JSONObject contenidoJson = arrayJson.getJSONObject(0);
                            //Obtenemos el valor de la UF
                            String valorUfString = contenidoJson.getString("valor");
                            //Convertimos el string en double (numero)
                            double valorUF = Double.parseDouble(valorUfString);
                            //Asignamos el número con el valor UF al objeto
                            seguro.setValorUf(valorUF);

                        }catch(JSONException ex){
                            ex.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }


    private void vincularElementos() {
        edtMarca = (EditText) findViewById(R.id.edt_marca);
        edtModelo = (EditText) findViewById(R.id.edt_marca);
        edtAnho = (EditText) findViewById(R.id.edt_anho);
        btnCalcular = (Button) findViewById(R.id.btn_calcular);

    }
}