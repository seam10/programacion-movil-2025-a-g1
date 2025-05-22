package com.BackPM.BackPM.services.IService;
// Define el paquete donde se ubica esta interfaz de servicio para Tarifa

import com.BackPM.BackPM.models.Tarifa;
// Importa la entidad Tarifa para usarla como tipo genérico en la interfaz

/**
 * Interfaz de servicio específica para la entidad Tarifa.
 * Extiende de IBaseService para heredar operaciones CRUD y de estado.
 */
public interface ITarifaService extends IBaseService<Tarifa> {
    // Extiende la interfaz genérica IBaseService con Tarifa como tipo de entidad

    /**
     * Método personalizado para obtener una Tarifa según el tipo de vehículo.
     * @param tipoVehiculo el tipo de vehículo (por ejemplo, "auto", "moto")
     * @return instancia de Tarifa que coincide con el tipo dado
     */
    Tarifa obtenerPorTipoVehiculo(String tipoVehiculo);
    // Convención de nombre:
    // - obtener → indica operación personalizada de búsqueda
    // - PorTipoVehiculo → criterio de búsqueda basado en la propiedad tipoVehiculo
    // Este método será implementado en la capa de servicio para encapsular
    // la lógica de consulta al repositorio correspondiente.
}
