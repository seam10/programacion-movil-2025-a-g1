package com.BackPM.BackPM.services.ServiceImpl;


import com.BackPM.BackPM.models.Registro;
import com.BackPM.BackPM.repositories.IRegistroRepository;
import com.BackPM.BackPM.services.IService.IRegistroService;
import org.springframework.stereotype.Service;

@Service
public class RegistroService extends ABaseService<Registro> implements IRegistroService {

    private final IRegistroRepository registroRepository;

    public RegistroService(IRegistroRepository registroRepository) {
        this.registroRepository = registroRepository;
    }

    @Override
    protected IRegistroRepository getRepository() {
        return registroRepository;
    }
    @Override
    public Registro obtenerPorIdEspacio(Long idEspacio) {
        return registroRepository.findByEspacioId(idEspacio);
    }




}
