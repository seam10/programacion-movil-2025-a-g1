package com.BackPM.BackPM.services.IService;
// Define el paquete donde reside la interfaz de servicio para Salida

import com.BackPM.BackPM.models.Salida;
// Importa la entidad Salida para usarla como tipo genérico en la interfaz

/**
 * Interfaz de servicio específica para la entidad Salida.
 * Extiende de IBaseService para heredar operaciones CRUD y de estado.
 */
public interface ISalidaService extends IBaseService<Salida> {
    // Extiende la interfaz genérica IBaseService con Salida como tipo de entidad

    /**
     * Método derivado de Spring Data para buscar una Salida
     * según el ID de su Registro asociado.
     *
     * @param idRegistro ID del registro de ingreso
     * @return instancia de Salida vinculada a ese registro
     */
    Salida findByRegistro_Id(Long idRegistro);
    // Convención de nombre:
    // - findBy → indica operación de búsqueda
    // - Registro → nombre de la propiedad en Salida
    // - _Id → subpropiedad id de Registro
    // Spring Data genera automáticamente la consulta JPQL equivalente
}
