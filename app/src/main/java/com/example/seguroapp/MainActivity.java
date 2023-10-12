package com.example.seguroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.seguroapp.clases.Seguro;
import com.example.seguroapp.clases.Vehiculo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Variables de objeto
    EditText edtMarca,edtModelo,edtAnho;
    Button btnCalcular;
    Seguro seguro;
    Vehiculo vehiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vincularElementos();
        //Instanciamos la clase seguro
        seguro = new Seguro();
        vehiculo = new Vehiculo(); //Instanciamos clases
        obtenerValorUf();
        habilitarListener();
    }

    private void habilitarListener() {
        //Habilitamos listener onclick para el boton
        btnCalcular.setOnClickListener(this);
    }

    private void obtenerValorUf() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://mindicador.cl/api/uf/28-09-2023";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // manejamos la respuesta
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

                            //Desplegamos temporalmente el Toast el valor del seguro
                            CharSequence text = "Valor uf" +seguro.getValorUf();
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(MainActivity.this,text, duration);
                            toast.show();

                        }catch(JSONException ex){
                            ex.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(MainActivity.this,"Se ha producido un error",Toast.LENGTH_LONG);
                toast.show();
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

    @Override
    public void onClick(View view) {
        //Obtenemos los valores de la interfaz y los asociamos al objeto vehiculo
        vehiculo.setMarca(edtMarca.getText().toString()); //En el objeto vehiculo
        vehiculo.setModelo(edtModelo.getText().toString());
        vehiculo.setAnho(Integer.parseInt(edtAnho.getText().toString()));
        //Cargamos ResultadoActivity y transportamos los datos
        Intent intento = new Intent(MainActivity.this,ResultadoActivity.class);
        intento.putExtra("p_marca",vehiculo.getMarca());
        intento.putExtra("p_modelo",vehiculo.getModelo());
        intento.putExtra("p_anho",vehiculo.getAnho());
        intento.putExtra("p_valor_uf",seguro.getValorUf());

        startActivity(intento);


    }
}