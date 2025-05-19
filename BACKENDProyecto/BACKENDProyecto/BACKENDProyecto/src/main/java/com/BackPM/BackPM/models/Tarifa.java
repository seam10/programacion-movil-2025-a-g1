package com.BackPM.BackPM.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tarifa")
public class Tarifa extends ABaseEntity {

    @Column(name = "tipo_vehiculo", nullable = false, length = 20)
    private String tipoVehiculo;

    @Column(name = "unidad_tiempo", nullable = false, length = 20)
    private String unidadTiempo; // Ej: "por hora", "por día"

    @Column(name = "valor", nullable = false)
    private double valor;

    public Tarifa() {}

    public Tarifa(String tipoVehiculo, String unidadTiempo, double valor) {
        this.tipoVehiculo = tipoVehiculo;
        this.unidadTiempo = unidadTiempo;
        this.valor = valor;
        this.setStatus(true); // Si ABaseEntity maneja status
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getUnidadTiempo() {
        return unidadTiempo;
    }

    public void setUnidadTiempo(String unidadTiempo) {
        this.unidadTiempo = unidadTiempo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
