package com.BackPM.BackPM.controllers;

import java.util.List;

// Importación de clases para construir las respuestas HTTP
import org.springframework.http.ResponseEntity;
// Importación de anotaciones para mapear rutas HTTP en métodos
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

// Importación de DTO genérico para envolver respuestas de la API
import com.BackPM.BackPM.Dto.ApiResponseDto;
// Importación de la entidad base que extienden todas las entidades de negocio
import com.BackPM.BackPM.models.ABaseEntity;
// Importación de la interfaz de servicio genérico con operaciones CRUD
import com.BackPM.BackPM.services.IService.IBaseService;

public abstract class ABaseController<T extends ABaseEntity, S extends IBaseService<T>> {
    // Servicio inyectado que contiene la lógica de negocio para la entidad T
    protected S service;
    // Nombre de la entidad, usado en mensajes de éxito o error
    protected String entityName;

    /**
     * Constructor que inicializa el servicio y el nombre de la entidad.
     *
     * @param service    instancia del servicio genérico para T
     * @param entityName nombre descriptivo de la entidad T
     */
    protected ABaseController(S service, String entityName) {
        this.service = service;
        this.entityName = entityName;
    }

    /**
     * Obtiene todos los registros con estado true (lógicos).
     * Mapea GET / a este método.
     */
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<T>>> findByStateTrue() {
        try {
            // Llama al servicio para recuperar los datos filtrados
            List<T> datos = service.findByStateTrue();
            // Devuelve 200 OK con los datos envueltos en un ApiResponseDto
            return ResponseEntity.ok(
                new ApiResponseDto<>("Datos obtenidos", datos, true)
            );
        } catch (Exception e) {
            // En caso de error, devuelve 500 Internal Server Error con el mensaje
            return ResponseEntity.internalServerError()
                .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Muestra un registro por su ID.
     * Mapea GET /{id} a este método.
     *
     * @param id identificador del registro
     */
    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDto<T>> show(@PathVariable Long id) {
        try {
            // Obtiene la entidad por ID desde el servicio
            T entity = service.findById(id);
            // Devuelve 200 OK con la entidad encontrada
            return ResponseEntity.ok(
                new ApiResponseDto<>("Registro encontrado", entity, true)
            );
        } catch (Exception e) {
            // En caso de excepción, devuelve 500 con detalle del error
            return ResponseEntity.internalServerError()
                .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Guarda un nuevo registro.
     * Mapea POST / a este método.
     *
     * @param entity objeto JSON enviado en el body que representa la nueva entidad
     */
    @PostMapping
    public ResponseEntity<ApiResponseDto<T>> save(@RequestBody T entity) {
        try {
            // Llama al servicio para guardar la entidad y la retorna
            T saved = service.save(entity);
            return ResponseEntity.ok(
                new ApiResponseDto<>("Datos guardados", saved, true)
            );
        } catch (Exception e) {
            // Devuelve 500 si ocurre un error al guardar
            return ResponseEntity.internalServerError()
                .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Actualiza un registro existente.
     * Mapea PUT /{id} a este método.
     *
     * @param id     identificador de la entidad a actualizar
     * @param entity objeto JSON con los nuevos datos de la entidad
     */
    @PutMapping("{id}")
    public ResponseEntity<ApiResponseDto<T>> update(
        @PathVariable Long id,
        @RequestBody T entity
    ) {
        try {
            // Invoca el servicio para actualizar la entidad
            service.update(id, entity);
            // Devuelve 200 OK confirmando la actualización
            return ResponseEntity.ok(
                new ApiResponseDto<>("Datos actualizados", null, true)
            );
        } catch (Exception e) {
            // Devuelve 500 en caso de error
            return ResponseEntity.internalServerError()
                .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    /**
     * Eliminación lógica de un registro (no lo borra físicamente).
     * Mapea DELETE /{id} a este método.
     *
     * @param id identificador de la entidad a eliminar lógicamente
     */
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponseDto<T>> delete(@PathVariable Long id) {
        try {
            // Llama al servicio para desactivar el registro (status = false)
            service.setStatus(id, false);
            // Devuelve 200 OK confirmando la eliminación lógica
            return ResponseEntity.ok(
                new ApiResponseDto<>("Registro eliminado lógicamente", null, true)
            );
        } catch (Exception e) {
            // Devuelve 500 si falla la operación
            return ResponseEntity.internalServerError()
                .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }
}
