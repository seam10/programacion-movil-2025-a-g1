package com.BackPM.BackPM.controllers;

import com.BackPM.BackPM.models.Espacio;
import com.BackPM.BackPM.services.IService.IEspacioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/espacios")
public class EspacioController extends ABaseController<Espacio, IEspacioService> {

    private final IEspacioService espacioService;

    public EspacioController(IEspacioService espacioService) {
        super(espacioService, "Espacio"); // llamado al constructor del padre
        this.espacioService = espacioService;
    }

    @GetMapping("/disponibles")
    public List<Espacio> getDisponibles() {
        return espacioService.obtenerDisponibles();
    }
}

