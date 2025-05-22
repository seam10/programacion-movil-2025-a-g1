package com.BackPM.BackPM.models;

import jakarta.persistence.*;

/**
 * Entidad JPA que representa un Espacio en el parqueadero.
 * Extiende de ABaseEntity para heredar campos comunes (id, status, timestamps, etc.).
 */
@Entity
// Mapea esta clase a la tabla "espacio" en la base de datos
@Table(name = "espacio")
public class Espacio extends ABaseEntity {

    /**
     * Enumeración interna que define los posibles estados de un espacio:
     * - DISPONIBLE: ningún vehículo ocupa el espacio
     * - OCUPADO: un vehículo está actualmente en el espacio
     * - RESERVADO: el espacio ha sido apartado para uso futuro
     */
    public enum EstadoEspacio {
        DISPONIBLE,
        OCUPADO,
        RESERVADO
    }

    @Enumerated(EnumType.STRING)
    // Almacena el valor del enum como texto en la columna "estado"
    @Column(name = "estado", nullable = false)
    private EstadoEspacio estado;  // DISPONIBLE, OCUPADO o RESERVADO

    /**
     * Constructor por defecto.
     * Inicializa el estado en DISPONIBLE para nuevos registros.
     */
    public Espacio() {
        this.estado = EstadoEspacio.DISPONIBLE;
    }

    /**
     * Constructor con parámetro para inicializar con un estado específico.
     *
     * @param estado Estado inicial del espacio
     */
    public Espacio(EstadoEspacio estado) {
        this.estado = estado;
    }

    /** @return El estado actual del espacio (DISPONIBLE, OCUPADO o RESERVADO) */
    public EstadoEspacio getEstado() {
        return estado;
    }

    /**
     * @param estado Nuevo estado a asignar al espacio
     */
    public void setEstado(EstadoEspacio estado) {
        this.estado = estado;
    }

    /**
     * @return true si el espacio está libre (DISPONIBLE), false en cualquier otro caso
     */
    public boolean estaDisponible() {
        return this.estado == EstadoEspacio.DISPONIBLE;
    }

    /**
     * @return true si el espacio está ocupado (OCUPADO), false en cualquier otro caso
     */
    public boolean estaOcupado() {
        return this.estado == EstadoEspacio.OCUPADO;
    }

    /**
     * @return true si el espacio está reservado (RESERVADO), false en cualquier otro caso
     */
    public boolean estaReservado() {
        return this.estado == EstadoEspacio.RESERVADO;
    }
}
//         );
//     } catch (Exception e) {
//         // En caso de error, devuelve 500 Internal Server Error con el mensaje
//         return ResponseEntity.internalServerError()
//             .body(new ApiResponseDto<>(e.getMessage(), null, false));
//     }
// }