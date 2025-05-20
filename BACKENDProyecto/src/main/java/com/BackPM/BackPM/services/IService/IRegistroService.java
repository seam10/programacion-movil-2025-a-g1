package com.BackPM.BackPM.services.IService;


import com.BackPM.BackPM.models.Registro;

public interface IRegistroService extends IBaseService<Registro> {

    Registro obtenerPorIdEspacio(Long idEspacio);
}

