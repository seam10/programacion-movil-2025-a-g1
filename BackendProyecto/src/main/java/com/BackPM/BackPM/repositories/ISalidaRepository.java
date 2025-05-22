package com.BackPM.BackPM.repositories;
// Define el paquete donde reside esta interfaz de repositorio

import com.BackPM.BackPM.models.Salida;
// Importa la entidad Salida, que es el tipo de dato que manejará este repositorio

import org.springframework.stereotype.Repository;
// Importa la anotación @Repository para que Spring detecte este componente

/**
 * Interfaz de repositorio para la entidad Salida.
 * Extiende de IBaseRepository, heredando métodos CRUD.
 */
@Repository
// Indica a Spring que esta interfaz es un bean de tipo repositorio
public interface ISalidaRepository extends IBaseRepository<Salida, Long> {
    // Extiende IBaseRepository<Salida, Long>, donde:
    // - Salida es la entidad gestionada
    // - Long es el tipo de dato de la clave primaria (ID)

    /**
     * Método para buscar una salida según el ID de su registro asociado.
     * Spring Data interpreta el nombre del método y construye automáticamente
     * la consulta JPQL equivalente:
     *   SELECT s FROM Salida s WHERE s.registro.id = :idRegistro
     *
     * @param idRegistro el identificador del registro de ingreso
     * @return la instancia de Salida vinculada a ese registro
     */
    Salida findByRegistro_Id(Long idRegistro);
    // Declaración de método derivado:
    // - findBy → prefijo que indica búsqueda
    // - Registro → propiedad de la entidad Salida
    // - _Id → subpropiedad id de Registro
    // Spring genera la consulta basándose en esta convención de nomenclatura
}
