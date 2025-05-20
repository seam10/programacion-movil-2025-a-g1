package com.BackPM.BackPM.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tarifa")
public class Tarifa extends ABaseEntity {
    private String tipoVehiculo;
    private double valorPorHora;
    private double valorPorDia;
    private boolean esTarifaEspecial;
    private String descripcion;

    public Tarifa() {}

    public Tarifa(String tipoVehiculo, double valorPorHora, double valorPorDia, boolean esTarifaEspecial, String descripcion) {
        this.tipoVehiculo = tipoVehiculo;
        this.valorPorHora = valorPorHora;
        this.valorPorDia = valorPorDia;
        this.esTarifaEspecial = esTarifaEspecial;
        this.descripcion = descripcion;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public double getValorPorHora() {
        return valorPorHora;
    }

    public void setValorPorHora(double valorPorHora) {
        this.valorPorHora = valorPorHora;
    }

    public double getValorPorDia() {
        return valorPorDia;
    }

    public void setValorPorDia(double valorPorDia) {
        this.valorPorDia = valorPorDia;
    }

    public boolean isEsTarifaEspecial() {
        return esTarifaEspecial;
    }

    public void setEsTarifaEspecial(boolean esTarifaEspecial) {
        this.esTarifaEspecial = esTarifaEspecial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

