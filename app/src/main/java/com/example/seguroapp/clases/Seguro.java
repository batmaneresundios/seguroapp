package com.example.seguroapp.clases;

public class Seguro {
    double valorUf;
    double valorSeguro;
    boolean asegurable;

    public void calcularValorSeguro(int antiguedad){
        this.valorSeguro = (this.valorUf * 0.1) * antiguedad;

    }
    //Determinamos si el vehiculo es menor o igual a 10 años para determinar si es asegurable
    public void determinarAsegurabilidad(int antiguedad) {
        this.asegurable = false;
        if(antiguedad <=10){
            this.asegurable = true;
        }


    }

    public double getValorUf() {
        return valorUf;
    }


    public void setValorUf(double valorUf) {
        this.valorUf = valorUf;
    }

    public double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public boolean isAsegurable() {
        return asegurable;
    }

    public void setAsegurable(boolean asegurable) {
        this.asegurable = asegurable;
    }


}
