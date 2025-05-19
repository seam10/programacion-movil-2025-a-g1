package com.BackPM.BackPM.services.ServiceImpl;

import com.BackPM.BackPM.models.Salida;
import com.BackPM.BackPM.repositories.ISalidaRepository;
import com.BackPM.BackPM.services.IService.ISalidaService;
import org.springframework.stereotype.Service;

@Service
public class SalidaService extends ABaseService<Salida> implements ISalidaService {

    private final ISalidaRepository salidaRepository;

    public SalidaService(ISalidaRepository salidaRepository) {
        this.salidaRepository = salidaRepository;
    }

    @Override
    protected ISalidaRepository getRepository() {
        return salidaRepository;
    }

    @Override
    public Salida findByRegistro_Id(Long idRegistro) {
        return salidaRepository.findByRegistro_Id(idRegistro);
    }
}
