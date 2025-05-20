package com.BackPM.BackPM.services.ServiceImpl;

import org.springframework.stereotype.Service;

@Service
public class VehiculoService extends ABaseService<Vehiculo> implements IVehiculoService {

    private final IVehiculoRepository vehiculoRepository;

    public VehiculoService(IVehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    protected IVehiculoRepository getRepository() {
        return vehiculoRepository;
    }

    @Override
    public Vehiculo obtenerPorPlaca(String placa) {
        return vehiculoRepository.findByPlaca(placa);
    }
}
