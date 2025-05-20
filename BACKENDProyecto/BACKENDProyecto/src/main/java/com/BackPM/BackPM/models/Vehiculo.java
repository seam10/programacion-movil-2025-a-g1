package com.BackPM.BackPM.models;

import jakarta.persistence.*;
@Entity
@Table(name = "vehiculo")
public class Vehiculo extends ABaseEntity {
    private String placa;
    private String tipo; // Ej: automóvil, motocicleta

    public Vehiculo() {}

    public Vehiculo(String placa, String tipo) {
        this.placa = placa;
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

