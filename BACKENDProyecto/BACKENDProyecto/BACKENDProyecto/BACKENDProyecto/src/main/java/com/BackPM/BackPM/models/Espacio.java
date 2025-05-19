package com.BackPM.BackPM.models;
import jakarta.persistence.*;

@Entity
@Table(name = "espacio")
public class Espacio extends ABaseEntity {
    private String codigo;
    private boolean ocupado;
    private boolean reservado;

    public Espacio() {}

    public Espacio(String codigo, boolean ocupado, boolean reservado) {
        this.codigo = codigo;
        this.ocupado = ocupado;
        this.reservado = reservado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }
}

