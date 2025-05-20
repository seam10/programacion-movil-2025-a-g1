package com.BackPM.BackPM.services.ServiceImpl;


import com.BackPM.BackPM.models.Espacio;
import com.BackPM.BackPM.repositories.IBaseRepository;
import com.BackPM.BackPM.repositories.IEspacioRepository;
import com.BackPM.BackPM.services.IService.IEspacioService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EspacioService extends ABaseService<Espacio> implements IEspacioService {

    private final IEspacioRepository espacioRepository;

    public EspacioService(IEspacioRepository espacioRepository) {
        this.espacioRepository = espacioRepository;
    }

    @Override
    protected IBaseRepository<Espacio, Long> getRepository() {
        return espacioRepository;
    }

    // Método específico para obtener los espacios disponibles
    public List<Espacio> obtenerDisponibles() {
        return espacioRepository.findDisponibles();  // Llamada al repositorio
    }

    @Override
    public void resetearTodos() {
        List<Espacio> espacios = espacioRepository.findAll();
        for (Espacio espacio : espacios) {
            espacio.setEstado(Espacio.EstadoEspacio.DISPONIBLE);
        }
        espacioRepository.saveAll(espacios);
    }
}


