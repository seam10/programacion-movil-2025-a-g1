package com.BackPM.BackPM.services.ServiceImpl;

import com.BackPM.BackPM.models.Tarifa;
import com.BackPM.BackPM.repositories.ITarifaRepository;
import com.BackPM.BackPM.services.IService.ITarifaService;
import org.springframework.stereotype.Service;

@Service
public class TarifaService extends ABaseService<Tarifa> implements ITarifaService {

    private final ITarifaRepository tarifaRepository;

    public TarifaService(ITarifaRepository tarifaRepository) {
        this.tarifaRepository = tarifaRepository;
    }

    @Override
    protected ITarifaRepository getRepository() {
        return tarifaRepository;
    }

    @Override
    public Tarifa obtenerPorTipoVehiculo(String tipoVehiculo) {
        return tarifaRepository.findByTipoVehiculo(tipoVehiculo);
    }
}

