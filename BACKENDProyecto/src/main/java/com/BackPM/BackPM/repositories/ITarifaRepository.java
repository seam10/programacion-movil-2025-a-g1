package com.BackPM.BackPM.repositories;

import com.BackPM.BackPM.models.Tarifa;
import org.springframework.stereotype.Repository;

@Repository
public interface ITarifaRepository extends IBaseRepository<Tarifa, Long> {
    Tarifa findByTipoVehiculo(String tipoVehiculo);
}

