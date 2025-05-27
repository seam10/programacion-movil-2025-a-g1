package com.BackPM.BackPM.models;

import jakarta.persistence.*;

/**
 * Entidad JPA que representa una tarifa para el cobro de uso de espacios.
 * Extiende de ABaseEntity para heredar campos comunes (id, status, timestamps, etc.).
 */
@Entity
// Mapea esta clase a la tabla "tarifa" en la base de datos
@Table(name = "tarifa")
public class Tarifa extends ABaseEntity {

    /**
     * Tipo de vehículo al que aplica la tarifa (autos, motos, etc.).
     * Columna de texto de hasta 20 caracteres, no puede ser nula.
     */
    @Column(name = "tipo_vehiculo", nullable = false, length = 20)
    private String tipoVehiculo;

    /**
     * Unidad de tiempo para el cobro (por hora, por día, etc.).
     * Columna de texto de hasta 20 caracteres, no puede ser nula.
     */
    @Column(name = "unidad_tiempo", nullable = false, length = 20)
    private String unidadTiempo;

    /**
     * Valor numérico de la tarifa según la unidad de tiempo.
     * Columna de tipo numérico, no puede ser nula.
     */
    @Column(name = "valor", nullable = false)
    private double valor;

    /**
     * Constructor vacío requerido por JPA para instanciar la entidad.
     */
    public Tarifa() {}

    /**
     * Constructor completo para inicializar los campos de la tarifa.
     * Además, marca el registro como activo (status = true).
     *
     * @param tipoVehiculo Nombre del tipo de vehículo
     * @param unidadTiempo Descripción de la unidad de tiempo (ej. "por hora")
     * @param valor        Monto a cobrar por la unidad de tiempo
     */
    public Tarifa(String tipoVehiculo, String unidadTiempo, double valor) {
        this.tipoVehiculo = tipoVehiculo;
        this.unidadTiempo = unidadTiempo;
        this.valor = valor;
        this.setStatus(true); // Activa el registro de tarifa
    }

    /** @return El tipo de vehículo al que aplica esta tarifa */
    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    /** @param tipoVehiculo Asigna el tipo de vehículo para la tarifa */
    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    /** @return La unidad de tiempo utilizada para el cobro */
    public String getUnidadTiempo() {
        return unidadTiempo;
    }

    /** @param unidadTiempo Establece la unidad de tiempo (ej. "por día") */
    public void setUnidadTiempo(String unidadTiempo) {
        this.unidadTiempo = unidadTiempo;
    }

    /** @return El valor numérico de la tarifa */
    public double getValor() {
        return valor;
    }

    /** @param valor Define el monto a cobrar por la unidad de tiempo */
    public void setValor(double valor) {
        this.valor = valor;
    }
}
//
// ---------- Getters y Setters ----------