package com.BackPM.BackPM.repositories;

// Importa la interfaz JpaRepository de Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// Importa la clase base que define los campos comunes de todas las entidades
import com.BackPM.BackPM.models.ABaseEntity;

/**
 * Interfaz genérica para repositorios que manejen entidades que extiendan ABaseEntity.
 * Al extender JpaRepository, hereda métodos CRUD y de paginación/sorting.
 *
 * @param <T>  Tipo de entidad (debe extender ABaseEntity)
 * @param <ID> Tipo de la clave primaria de la entidad (Long, Integer, etc.)
 */
public interface IBaseRepository<T extends ABaseEntity, ID>
        extends JpaRepository<T, ID> {
    // No se requiere código adicional: hereda todos los métodos de JpaRepository
}
