package com.BackPM.BackPM.services.IService;
// Define el paquete donde se encuentra la interfaz de servicio para Espacio

import com.BackPM.BackPM.models.Espacio;
// Importa la entidad Espacio para usarla como tipo genérico

import java.util.List;
// Importa la interfaz List para devolver colecciones de Espacio

/**
 * Interfaz de servicio específica para la entidad Espacio.
 * Extiende IBaseService para heredar operaciones CRUD y de estado.
 */
public interface IEspacioService extends IBaseService<Espacio> {

    /**
     * Método personalizado para obtener solo los espacios disponibles.
     * @return Lista de espacios cuyo estado es DISPONIBLE
     */
    List<Espacio> obtenerDisponibles();

    /**
     * Método personalizado para resetear el estado de todos los espacios.
     * Marca cada espacio como DISPONIBLE.
     */
    void resetearTodos();
}
