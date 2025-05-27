package com.BackPM.BackPM.services.IService;
// Define el paquete donde se ubica la interfaz de servicio para Registro

import com.BackPM.BackPM.models.Registro;
// Importa la entidad Registro para usarla como tipo genérico en la interfaz

/**
 * Interfaz de servicio específica para la entidad Registro.
 * Extiende de IBaseService para heredar operaciones CRUD y de estado.
 */
public interface IRegistroService extends IBaseService<Registro> {
    // Registro: entidad de negocio que representa un ingreso de vehículo

    /**
     * Obtiene un Registro según el ID del espacio asociado.
     * @param idEspacio ID del espacio vinculado al registro
     * @return instancia de Registro correspondiente
     */
    Registro obtenerPorIdEspacio(Long idEspacio);
    // Método personalizado para buscar un registro por la clave foránea idEspacio

    /**
     * Guarda un nuevo Registro con la lógica de negocio asociada.
     * @param registro objeto Registro con datos de espacio, placa y hora ingreso
     * @return el Registro persistido con ID generado
     */
    Registro guardarRegistro(Registro registro);
    // Método personalizado para encapsular la lógica de validación y guardado de registros
}
