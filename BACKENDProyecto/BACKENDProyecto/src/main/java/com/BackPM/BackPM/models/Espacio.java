package com.BackPM.BackPM.models;

import jakarta.persistence.*;

@Entity
@Table(name = "espacio")
public class Espacio extends ABaseEntity {

    public enum EstadoEspacio {
        DISPONIBLE,
        OCUPADO,
        RESERVADO
    }


    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoEspacio estado;  // DISPONIBLE, OCUPADO, RESERVADO

    public Espacio() {
        this.estado = EstadoEspacio.DISPONIBLE;  // Por defecto
    }

    public Espacio(EstadoEspacio estado) {

        this.estado = estado;
    }


    public EstadoEspacio getEstado() {
        return estado;
    }

    public void setEstado(EstadoEspacio estado) {
        this.estado = estado;
    }

    public boolean estaDisponible() {
        return this.estado == EstadoEspacio.DISPONIBLE;
    }

    public boolean estaOcupado() {
        return this.estado == EstadoEspacio.OCUPADO;
    }

    public boolean estaReservado() {
        return this.estado == EstadoEspacio.RESERVADO;
    }
}
