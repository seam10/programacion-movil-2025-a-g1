package com.BackPM.BackPM.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registro")
public class Registro extends ABaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_espacio", nullable = false)
    private Espacio espacio;

    //entidad de la placa del vehiculo
    @Column(name = "placa_vehiculo", nullable = false, length = 10)
    private String placaVehiculo;

    @Column(name = "hora_ingreso", nullable = false)
    private LocalDateTime horaIngreso;

    // Constructor vacío requerido por JPA
    public Registro() {}

    // Constructor con parámetros
    public Registro(Espacio espacio, String placaVehiculo, LocalDateTime horaIngreso) {
        this.espacio = espacio;
        this.placaVehiculo = placaVehiculo;
        this.horaIngreso = horaIngreso;
        this.setStatus(true);
    }

    // Getters y Setters
    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalDateTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }
}
