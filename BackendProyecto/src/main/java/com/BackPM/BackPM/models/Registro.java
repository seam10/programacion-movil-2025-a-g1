package com.BackPM.BackPM.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad JPA que representa un registro de ingreso de vehículo al parqueadero.
 * Extiende de ABaseEntity para heredar campos comunes (id, status, timestamps, etc.).
 */
@Entity
// Mapea esta clase a la tabla "registro" en la base de datos
@Table(name = "registro")
public class Registro extends ABaseEntity {

    /**
     * Relación Many-to-One con la entidad Espacio.
     * Cada registro apunta a un espacio específico (no nullable).
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_espacio", nullable = false)
    private Espacio espacio;

    /**
     * Placa del vehículo registrado.
     * Columna de texto con longitud máxima de 10 caracteres.
     */
    @Column(name = "placa_vehiculo", nullable = false, length = 10)
    private String placaVehiculo;

    /**
     * Fecha y hora de ingreso del vehículo.
     * Se almacena como timestamp en la base de datos.
     */
    @Column(name = "hora_ingreso", nullable = false)
    private LocalDateTime horaIngreso;

    /**
     * Constructor vacío requerido por JPA para instanciar la entidad.
     */
    public Registro() {}

    /**
     * Constructor con parámetros para crear un registro completo.
     * Inicializa el espacio, la placa y la hora de ingreso, y activa el status.
     *
     * @param espacio       instancia de Espacio donde ingresa el vehículo
     * @param placaVehiculo cadena con la placa del vehículo (máx. 10 caracteres)
     * @param horaIngreso   fecha y hora exacta de ingreso
     */
    public Registro(Espacio espacio, String placaVehiculo, LocalDateTime horaIngreso) {
        this.espacio = espacio;
        this.placaVehiculo = placaVehiculo;
        this.horaIngreso = horaIngreso;
        this.setStatus(true); // Marca el registro como activo/visible
    }

    /** @return Espacio asociado al registro */
    public Espacio getEspacio() {
        return espacio;
    }

    /** @param espacio Asigna el espacio al registro */
    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    /** @return Placa del vehículo registrado */
    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    /** @param placaVehiculo Establece la placa del vehículo (máx. 10 caracteres) */
    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    /** @return Fecha y hora en que ingresó el vehículo */
    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }

    /** @param horaIngreso Define la fecha y hora de ingreso del vehículo */
    public void setHoraIngreso(LocalDateTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }
}
//     public Long getCreatedBy() {
//         return createdBy;
//     }