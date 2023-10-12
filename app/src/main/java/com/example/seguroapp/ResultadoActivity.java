package com.example.seguroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seguroapp.clases.Seguro;
import com.example.seguroapp.clases.Vehiculo;

public class ResultadoActivity extends AppCompatActivity {
    //Definimos variables de objeto para la gestion de datos recibidos desde activity anterior
    Bundle bundle; //Recibe los valores del activity
    Intent intento;
    //Definimos variables de objeto para acceder a elementos de la interfaz
    TextView txvAsegurable;
    TextView txvValorseguro;
    ImageView imvAsegurable;
    Button btnRegresar;
    //Definimos variables de objeto para objetos de negocio
    Vehiculo vehiculo;
    Seguro seguro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        vincularElementos();
        obtenerDatos();
        asignarValores();
        instanciarObjetos();

    }

    private void instanciarObjetos() {
        vehiculo = new Vehiculo();
        seguro = new Seguro();
    }

    private void asignarValores() {
        //Instanciamos variables de objeto
        vehiculo.setMarca(bundle.getString("p_marca"));
        vehiculo.setModelo(bundle.getString("p_modelo"));
        vehiculo.setAnho(bundle.getInt("p_anho"));
        seguro.setValorUf(bundle.getDouble("p_valor_uf"));
        vehiculo.calcularAntiguedad();
        seguro.calcularValorSeguro(vehiculo.getAntiguedad());
    }

    private void obtenerDatos() {
        //Instanciamos y obtenemos el intento
        intento = getIntent();
        //Obtenemos los datos del intento y asociamos a objeto Bundle
        bundle = intento.getExtras();
    }

    private void vincularElementos() { //Tenemos acceso a los elementos de la interfaz
        txvAsegurable = (TextView) findViewById(R.id.txv_asegurable);
        txvValorseguro = (TextView) findViewById(R.id.txv_valor_seguro);
        imvAsegurable = (ImageView) findViewById(R.id.imv_asegurable);
        btnRegresar = (Button) findViewById(R.id.btn_calcular);
    }
}