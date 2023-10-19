package com.example.seguroapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seguroapp.clases.Seguro;
import com.example.seguroapp.clases.Vehiculo;

public class ResultadoActivity extends AppCompatActivity implements View.OnClickListener {
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
        activarListener();
        obtenerDatos();
        instanciarObjetos();
        asignarValores();
        mostrarResultados();
    }

    private void activarListener() {
        btnRegresar.setOnClickListener(this);
    }

    //Hace uso de los datos obtenidos para mostrar en el layout
    private void mostrarResultados() {
        //Mensaje para mostrar asegurabilidad
        if(seguro.isAsegurable()) {
            txvAsegurable.setText("Vehiculo asegurable");
            imvAsegurable.setImageResource(R.drawable.success);
            txvValorseguro.setText("Valor seguro :" +String.format("%.0f",seguro.getValorSeguro()));
        }else{
            txvAsegurable.setText("Vehiculo no asegurable, tiene mas de 10 años");
            imvAsegurable.setImageResource(R.drawable.error);
        }

    }

    private void instanciarObjetos() {
        vehiculo = new Vehiculo();
        seguro = new Seguro();
    }

    private void asignarValores() {
        //Obtenemos valores ingresados por el usuario enviados en el intento
        vehiculo.setMarca(bundle.getString("p_marca"));
        vehiculo.setModelo(bundle.getString("p_modelo"));
        vehiculo.setAnho(bundle.getInt("p_anho"));
        //Obtenemos valor consultado a API REST y enviado en el intento
        seguro.setValorUf(bundle.getDouble("p_valor_uf"));
        vehiculo.calcularAntiguedad();
        //Obtenemos valores segun calculo de valor seguro y seguribilidad
        seguro.calcularValorSeguro(vehiculo.getAntiguedad());
        seguro.determinarAsegurabilidad(vehiculo.getAntiguedad());
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
        btnRegresar = (Button) findViewById(R.id.btn_regresar);
    }

    @Override
    public void onClick(View view) {
        //Intento para cargar Activity Main
        Intent intent = new Intent(ResultadoActivity.this,MainActivity.class);
        startActivity(intent);

    }
}