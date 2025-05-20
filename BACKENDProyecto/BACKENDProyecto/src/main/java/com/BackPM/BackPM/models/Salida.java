package com.BackPM.BackPM.models;

import jakarta.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "salida")
public class Salida extends ABaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_registro", nullable = false)
    private Registro registro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_espacio", nullable = false)
    private Espacio espacio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tarifa", nullable = false)
    private Tarifa tarifa;

    @Column(name = "hora_salida", nullable = false)
    private LocalDateTime horaSalida;

    @Column(name = "total_calculado", nullable = false)
    private double totalCalculado;

    public Salida() {}

    public Salida(Registro registro, Espacio espacio, Tarifa tarifa, LocalDateTime horaSalida, double totalCalculado) {
        this.registro = registro;
        this.espacio = espacio;
        this.tarifa = tarifa;
        this.horaSalida = horaSalida;
        this.totalCalculado = totalCalculado;
        this.setStatus(true);
    }

    // Getters y Setters
    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public double getTotalCalculado() {
        return totalCalculado;
    }

    public void setTotalCalculado(double totalCalculado) {
        this.totalCalculado = totalCalculado;
    }
}

