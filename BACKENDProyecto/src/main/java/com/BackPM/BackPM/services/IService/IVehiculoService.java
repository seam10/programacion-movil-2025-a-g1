package com.BackPM.BackPM.services.IService;


public interface IVehiculoService extends IBaseService<Vehiculo> {
    Vehiculo obtenerPorPlaca(String placa);
}

