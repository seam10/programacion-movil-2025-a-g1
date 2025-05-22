package com.BackPM.BackPM.services.ServiceImpl;
// Define el paquete donde reside la implementación de servicios genéricos

import java.time.LocalDateTime;
// Importa la clase para manejar fechas y horas (timestamp)

import java.util.List;
// Importa la interfaz List para colecciones de entidades

import java.util.Optional;
// Importa Optional para manejo seguro de objetos que pueden ser nulos

import org.springframework.beans.BeanUtils;
// Importa BeanUtils para copiar propiedades entre objetos

import com.BackPM.BackPM.models.ABaseEntity;
// Importa la clase base de entidad que define campos comunes (id, timestamps, etc.)

import com.BackPM.BackPM.repositories.IBaseRepository;
// Importa la interfaz genérica de repositorio para acceso a datos

import com.BackPM.BackPM.services.IService.IBaseService;
// Importa la interfaz genérica de servicio que define operaciones CRUD

/**
 * Servicio base genérico que implementa la lógica CRUD común
 * para todas las entidades que extienden ABaseEntity.
 */
public abstract class ABaseService<T extends ABaseEntity> implements IBaseService<T> {

    /**
     * Método abstracto que debe ser implementado en cada subclase
     * para proveer el repositorio específico de la entidad T.
     */
    protected abstract IBaseRepository<T, Long> getRepository();

    @Override
    public List<T> all() {
        // Recupera y retorna todos los registros de la tabla correspondiente
        return getRepository().findAll();
    }

    @Override
    public List<T> findByStateTrue() {
        // Aquí debería filtrarse por status=true, pero actualmente devuelve todos
        return getRepository().findAll();
    }

    @Override
    public T findById(Long id) throws Exception {
        // Busca el registro por su ID
        Optional<T> op = getRepository().findById(id);

        if (op.isEmpty()) {
            // Lanza excepción si no se encuentra el registro
            throw new Exception("Registro no encontrado");
        }

        // Retorna la entidad encontrada
        return op.get();
    }

    @Override
    public T save(T entity) throws Exception {
        try {
            // Establece usuario creador (hardcodeado como 1L)
            entity.setCreatedBy(1L);
            // Establece la fecha y hora de creación al momento actual
            entity.setCreatedAt(LocalDateTime.now());
            // Guarda la entidad en la base de datos y la retorna
            return getRepository().save(entity);
        } catch (Exception e) {
            // Envuelve cualquier error en una excepción con mensaje descriptivo
            throw new Exception("Error al guardar la entidad: " + e.getMessage());
        }
    }

    @Override
    public void update(Long id, T entity) throws Exception {
        // Busca el registro existente por ID
        Optional<T> op = getRepository().findById(id);

        if (op.isEmpty()) {
            // Excepción si no existe
            throw new Exception("Registro no encontrado");
        } else if (op.get().getDeletedAt() != null) {
            // Excepción si el registro está marcado como eliminado
            throw new Exception("Registro inhabilitado");
        }

        // Obtiene la entidad persistente para actualizarla
        T entityUpdate = op.get();

        // Propiedades a ignorar al copiar (no sobrescribir ID, auditoría, etc.)
        String[] ignoreProperties = {
            "id", "createdAt", "deleteAt", "createdBy", "deletedBy"
        };
        // Copia todas las demás propiedades del objeto entrante
        BeanUtils.copyProperties(entity, entityUpdate, ignoreProperties);
        // Establece usuario que modifica (hardcodeado como 2L)
        entityUpdate.setUpdatedBy(2L);
        // Establece la fecha y hora de modificación al momento actual
        entityUpdate.setUpdatedAt(LocalDateTime.now());
        // Guarda los cambios sobre la entidad existente
        getRepository().save(entityUpdate);
    }

    @Override
    public void delete(Long id) throws Exception {
        // Busca el registro para eliminación lógica
        Optional<T> op = getRepository().findById(id);

        if (op.isEmpty()) {
            // Excepción si no existe
            throw new Exception("Registro no encontrado");
        }

        // Marca usuario y fecha de eliminación lógica
        T entityUpdate = op.get();
        entityUpdate.setDeletedBy(3L);
        entityUpdate.setDeletedAt(LocalDateTime.now());

        // Guarda la marca de eliminación en la base de datos
        getRepository().save(entityUpdate);
    }

    @Override
    public void setStatus(Long id, boolean state) {
        // Busca la entidad o lanza RuntimeException si no se encuentra
        T entityUpdate = getRepository()
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Entity with ID " + id + " not found"));
        // Marca usuario eliminador (hardcodeado como 3L) – aunque el método cambia estado
        entityUpdate.setDeletedBy(3L);
        entityUpdate.setDeletedAt(LocalDateTime.now());
        // Actualiza el campo status según el parámetro
        entityUpdate.setStatus(state);
        // Guarda la entidad con el nuevo estado
        getRepository().save(entityUpdate);
    }
}
