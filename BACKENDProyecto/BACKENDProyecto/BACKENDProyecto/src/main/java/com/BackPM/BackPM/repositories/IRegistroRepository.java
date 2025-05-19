package com.BackPM.BackPM.repositories;

import com.BackPM.BackPM.models.Registro;
import org.springframework.stereotype.Repository;


@Repository
public interface IRegistroRepository extends IBaseRepository<Registro, Long> {
    Registro findByEspacioId(Long idEspacio);


}
