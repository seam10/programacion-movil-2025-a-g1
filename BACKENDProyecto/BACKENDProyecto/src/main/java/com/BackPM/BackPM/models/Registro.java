package com.BackPM.BackPM.models;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "registro")
public class Registro extends ABaseEntity {

    // Relación con la entidad Espacio
    @ManyToOne(fetch = FetchType.EAGER) // Carga las relaciones de inmediato
    @JoinColumn(name = "id_espacio", nullable = false) // Mapeo de la columna 'id_espacio' a la propiedad 'espacio'
    private Espacio espacio;

    // Relación con la entidad Vehiculo (si existe una entidad Vehiculo)
    @ManyToOne(fetch = FetchType.EAGER) // Carga las relaciones de inmediato
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private Vehiculo vehiculo;

    @Column(name = "hora_ingreso", nullable = false)
    private LocalDateTime horaIngreso;

    // Constructor vacío requerido por JPA
    public Registro() {
    }

    // Constructor con parámetros
    public Registro(Espacio espacio, Vehiculo vehiculo, LocalDateTime horaIngreso) {
        this.espacio = espacio;
        this.vehiculo = vehiculo;
        this.horaIngreso = horaIngreso;
        this.setStatus(true); // Valor predeterminado para 'status'
    }

    // Getters y Setters
    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalDateTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }
}


