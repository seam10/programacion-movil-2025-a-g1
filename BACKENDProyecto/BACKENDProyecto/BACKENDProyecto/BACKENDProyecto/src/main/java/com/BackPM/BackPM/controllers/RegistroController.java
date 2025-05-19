package com.BackPM.BackPM.controllers;


import com.BackPM.BackPM.models.Registro;
import com.BackPM.BackPM.services.IService.IRegistroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
public class RegistroController extends ABaseController<Registro, IRegistroService> {

    private final IRegistroService registroService;

    public RegistroController(IRegistroService registroService) {
        super(registroService, "Registro");
        this.registroService = registroService;
    }

    // Obtener un Registro por el id del espacio
    @GetMapping("/espacio/{idEspacio}")
    public Registro getByEspacioId(@PathVariable Long idEspacio) {
        return registroService.obtenerPorIdEspacio(idEspacio);
    }

}

