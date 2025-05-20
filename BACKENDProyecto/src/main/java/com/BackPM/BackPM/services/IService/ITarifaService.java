package com.BackPM.BackPM.services.IService;


import com.BackPM.BackPM.models.Tarifa;

public interface ITarifaService extends IBaseService<Tarifa> {
    Tarifa obtenerPorTipoVehiculo(String tipoVehiculo);
}

