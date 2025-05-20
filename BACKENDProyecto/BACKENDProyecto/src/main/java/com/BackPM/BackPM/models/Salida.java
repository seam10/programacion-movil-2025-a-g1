package com.BackPM.BackPM.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "salida")
public class Salida extends ABaseEntity {

    @ManyToOne
    @JoinColumn(name = "id_registro", nullable = false)  // Aquí hacemos la relación
    private Registro registro;

    @Column(name = "hora_salida", nullable = false)
    private LocalDateTime horaSalida;

    @Column(name = "tarifa_calculada", nullable = false)
    private double tarifaCalculada;

    // Constructor vacío requerido por JPA
    public Salida() {
    }

    // Constructor con parámetros
    public Salida(Registro registro, LocalDateTime horaSalida, double tarifaCalculada) {
        this.registro = registro;
        this.horaSalida = horaSalida;
        this.tarifaCalculada = tarifaCalculada;
    }

    // Getters y Setters
    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public double getTarifaCalculada() {
        return tarifaCalculada;
    }

    public void setTarifaCalculada(double tarifaCalculada) {
        this.tarifaCalculada = tarifaCalculada;
    }
}
