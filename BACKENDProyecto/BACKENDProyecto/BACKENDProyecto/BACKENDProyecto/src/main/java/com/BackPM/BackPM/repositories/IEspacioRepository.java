package com.BackPM.BackPM.repositories;

import com.BackPM.BackPM.models.Espacio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEspacioRepository extends IBaseRepository<Espacio, Long> {

    // Método para obtener los espacios disponibles
    @Query("SELECT e FROM Espacio e WHERE e.ocupado = false AND e.reservado = false")
    List<Espacio> findDisponibles();

}

