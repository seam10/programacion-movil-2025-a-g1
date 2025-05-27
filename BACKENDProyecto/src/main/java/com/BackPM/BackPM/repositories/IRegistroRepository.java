package com.BackPM.BackPM.repositories;

// Importa la entidad Registro para usarla como tipo genérico
import com.BackPM.BackPM.models.Registro;
// Importa la anotación para marcar esta interfaz como repositorio Spring
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA específico para la entidad Registro.
 * Extiende IBaseRepository para heredar operaciones CRUD de JpaRepository.
 *
 * @see IBaseRepository
 */
@Repository
public interface IRegistroRepository extends IBaseRepository<Registro, Long> {

    /**
     * Busca un registro según el ID del espacio asociado.
     * Spring Data genera automáticamente la consulta basada en el nombre del método.
     *
     * @param idEspacio ID del espacio vinculado al registro
     * @return instancia de Registro cuya relación espacio tiene el ID dado
     */
    Registro findByEspacioId(Long idEspacio);

}
