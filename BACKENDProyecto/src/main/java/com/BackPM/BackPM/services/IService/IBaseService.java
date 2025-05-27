package com.BackPM.BackPM.services.IService;
// Define el paquete donde se ubica esta interfaz de servicio genérico

import java.util.List;
// Importa la interfaz List para devolver colecciones de entidades

import com.BackPM.BackPM.models.ABaseEntity;
// Importa la clase base que todas las entidades extienden

/**
 * Interfaz genérica que define las operaciones CRUD y de estado
 * para cualquier entidad que extienda ABaseEntity.
 *
 * @param <T> Tipo de entidad gestionada (debe heredar de ABaseEntity)
 */
public interface IBaseService<T extends ABaseEntity> {

    /**
     * Recupera todos los registros de la entidad, sin filtrar por estado.
     *
     * @return Lista con todas las entidades T
     */
    List<T> all();

    /**
     * Recupera únicamente los registros cuyo campo 'status' es true.
     *
     * @return Lista de entidades T activas
     */
    List<T> findByStateTrue();

    /**
     * Busca una entidad por su ID.
     *
     * @param id Identificador de la entidad
     * @return La entidad encontrada
     * @throws Exception Si no existe o ocurre un error en la búsqueda
     */
    T findById(Long id) throws Exception;

    /**
     * Guarda una nueva entidad en la base de datos.
     * @param entity Instancia de T a guardar
     * @return La entidad guardada (con ID generado)
     * @throws Exception Si falla la validación o persistencia
     */
    T save(T entity) throws Exception;

    /**
     * Actualiza un registro existente identificado por ID.
     * @param id     ID del registro a actualizar
     * @param entity Objeto que contiene los nuevos datos
     * @throws Exception Si no se encuentra el registro o falla la actualización
     */
    void update(Long id, T entity) throws Exception;

    /**
     * Elimina una entidad por su ID.
     *
     * @param id ID de la entidad a eliminar
     * @throws Exception Si la entidad no existe o falla la eliminación
     */
    void delete(Long id) throws Exception;

    /**
     * Cambia el estado lógico ('status') de una entidad.
     *
     * @param id    ID de la entidad cuyo estado se modifica
     * @param state Nuevo valor de status (true = activo, false = inactivo)
     */
    void setStatus(Long id, boolean state);
}
