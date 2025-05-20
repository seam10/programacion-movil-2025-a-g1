package com.BackPM.BackPM.repositories;

import com.BackPM.BackPM.models.Espacio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEspacioRepository extends IBaseRepository<Espacio, Long> {

    // Usando la consulta personalizada
    @Query("SELECT e FROM Espacio e WHERE e.estado = com.BackPM.BackPM.models.Espacio.EstadoEspacio.DISPONIBLE")
    List<Espacio> findDisponibles();


}

