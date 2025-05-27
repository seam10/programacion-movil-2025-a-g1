package com.BackPM.BackPM.repositories;
// Define el paquete donde se ubica esta interfaz de repositorio

import com.BackPM.BackPM.models.Tarifa;
// Importa la entidad Tarifa, que es el tipo de dato gestionado por este repositorio

import org.springframework.stereotype.Repository;
// Importa la anotación @Repository para que Spring la detecte como componente de persistencia

/**
 * Repositorio JPA para la entidad Tarifa.
 * Extiende de IBaseRepository para heredar métodos CRUD básicos.
 */
@Repository
// Marca esta interfaz como un bean de repositorio gestionado por Spring
public interface ITarifaRepository extends IBaseRepository<Tarifa, Long> {
    // Extiende IBaseRepository<Tarifa, Long> donde:
    // - Tarifa es la entidad que maneja
    // - Long es el tipo de dato de la clave primaria (ID)

    /**
     * Busca una tarifa según el tipo de vehículo.
     * Spring Data genera automáticamente la consulta basada en el nombre del método:
     *   SELECT t FROM Tarifa t WHERE t.tipoVehiculo = :tipoVehiculo
     *
     * @param tipoVehiculo el tipo de vehículo (por ejemplo, "auto", "moto")
     * @return la instancia de Tarifa que coincide con el tipo dado
     */
    Tarifa findByTipoVehiculo(String tipoVehiculo);
    // Método derivado de Spring Data:
    // - findBy → indica operación de búsqueda
    // - TipoVehiculo → propiedad de la entidad Tarifa a comparar
    // Spring construye la consulta JPQL basándose en esta convención de nombre
}
