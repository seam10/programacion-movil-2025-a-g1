package com.BackPM.BackPM.repositories;

import com.BackPM.BackPM.models.Salida;
import org.springframework.stereotype.Repository;

@Repository
public interface ISalidaRepository extends IBaseRepository<Salida, Long> {
    Salida findByRegistro_Id(Long idRegistro);
}
