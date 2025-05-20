package com.BackPM.BackPM.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface IVehiculoRepository extends IBaseRepository<Vehiculo, Long>{
    Vehiculo findByPlaca(String placa);
}

