package com.BackPM.BackPM.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad JPA que representa la salida de un vehículo del parqueadero.
 * Extiende de ABaseEntity para heredar campos comunes (id, status, timestamps, etc.).
 */
@Entity  
// Marca esta clase como una entidad que se mapeará a una tabla en la BD
@Table(name = "salida")  
// Especifica que la tabla en la BD se llama "salida"
public class Salida extends ABaseEntity {

    /**
     * Relación Many-to-One con Registro.
     * Cada Salida está asociada a un único Registro de ingreso.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    // Carga inmediata del objeto Registro al obtener una Salida
    @JoinColumn(name = "id_registro", nullable = false)
    // Columna "id_registro" como clave foránea, no puede ser nula
    private Registro registro;

    /**
     * Relación Many-to-One con Espacio.
     * Guarda el espacio asociado a esta salida (para liberar o auditar).
     */
    @ManyToOne(fetch = FetchType.EAGER)
    // Carga inmediata del objeto Espacio
    @JoinColumn(name = "id_espacio", nullable = false)
    // Columna "id_espacio" como clave foránea, no puede ser nula
    private Espacio espacio;

    /**
     * Relación Many-to-One con Tarifa.
     * Indica la tarifa usada para calcular el total.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    // Carga inmediata del objeto Tarifa
    @JoinColumn(name = "id_tarifa", nullable = false)
    // Columna "id_tarifa" como clave foránea, no puede ser nula
    private Tarifa tarifa;

    /**
     * Momento exacto en que se produjo la salida.
     */
    @Column(name = "hora_salida", nullable = false)
    // Columna "hora_salida" de tipo timestamp, no puede ser nula
    private LocalDateTime horaSalida;

    /**
     * Total calculado según duración y tarifa aplicada.
     */
    @Column(name = "total_calculado", nullable = false)
    // Columna "total_calculado" de tipo numérico, no puede ser nula
    private double totalCalculado;

    /**
     * Constructor vacío requerido por JPA para crear instancias mediante reflexión.
     */
    public Salida() {}

    /**
     * Constructor completo para inicializar todos los campos relevantes.
     * Además, marca el registro como activo (status = true).
     *
     * @param registro       Registro de ingreso asociado
     * @param espacio        Espacio del parqueadero liberado
     * @param tarifa         Tarifa usada para el cálculo
     * @param horaSalida     Fecha y hora de la salida
     * @param totalCalculado Precio total calculado
     */
    public Salida(
        Registro registro,
        Espacio espacio,
        Tarifa tarifa,
        LocalDateTime horaSalida,
        double totalCalculado
    ) {
        this.registro = registro;
        this.espacio = espacio;
        this.tarifa = tarifa;
        this.horaSalida = horaSalida;
        this.totalCalculado = totalCalculado;
        this.setStatus(true); // Activa el registro de salida
    }

    // ---------- Getters y Setters ----------

    /** @return Registro asociado a esta salida */
    public Registro getRegistro() {
        return registro;
    }

    /** @param registro Asigna el registro de ingreso a esta salida */
    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    /** @return Espacio liberado en esta salida */
    public Espacio getEspacio() {
        return espacio;
    }

    /** @param espacio Asigna el espacio al liberar el vehículo */
    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    /** @return Tarifa aplicada para calcular el total */
    public Tarifa getTarifa() {
        return tarifa;
    }

    /** @param tarifa Establece la tarifa para este registro de salida */
    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    /** @return Fecha y hora en que ocurrió la salida */
    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    /** @param horaSalida Define la marca de tiempo de la salida */
    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    /** @return Total calculado según tiempo y tarifa */
    public double getTotalCalculado() {
        return totalCalculado;
    }

    /** @param totalCalculado Establece el monto total a pagar */
    public void setTotalCalculado(double totalCalculado) {
        this.totalCalculado = totalCalculado;
    }
}
//             // Devuelve 500 en caso de error
//             return ResponseEntity.internalServerError()
//                 .body(new ApiResponseDto<>(e.getMessage(), null, false));
//         }
//     }