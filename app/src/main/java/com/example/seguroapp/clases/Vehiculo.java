package com.example.seguroapp.clases;

import java.util.Calendar;

public class Vehiculo {
    String marca;
    String modelo;
    int anho;
    int antiguedad;

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnho() {
        return anho;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }

    //Metodo para calcular antiguedad
    public void calcularAntiguedad(){
        //Determinnamos el año actual
        int anhoActual = Calendar.getInstance().get(Calendar.YEAR);
        //Calculamos la antiguedad en base al año del objeto actual y el año en curso
        this.antiguedad = anhoActual - this.anho;
        //Si la antiguedad es cero establecemos en 1
        if(this.antiguedad == 0){
            this.antiguedad = 1;
        }
    }

}
