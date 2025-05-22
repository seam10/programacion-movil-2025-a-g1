package com.BackPM.BackPM.repositories;

// Importa la anotación para definir consultas JPQL personalizadas
import org.springframework.data.jpa.repository.Query;
// Importa la anotación para marcar esta interfaz como un bean de repositorio
import org.springframework.stereotype.Repository;

// Importa la entidad Espacio para usarla como tipo genérico
import com.BackPM.BackPM.models.Espacio;

import java.util.List;

/**
 * Repositorio JPA específico para la entidad Espacio.
 * Extiende IBaseRepository para heredar métodos CRUD de JpaRepository.
 */
@Repository
public interface IEspacioRepository extends IBaseRepository<Espacio, Long> {

    /**
     * Consulta JPQL personalizada para obtener sólo los espacios disponibles.
     * El enum EstadoEspacio se compara mediante su valor STRING en la base de datos.
     *
     * @return Lista de objetos Espacio cuyo estado es DISPONIBLE
     */
    @Query(
    "SELECT e " +
    "FROM Espacio e " +
    "WHERE e.estado = com.BackPM.BackPM.models.Espacio.EstadoEspacio.DISPONIBLE"
    )
    List<Espacio> findDisponibles();

}
