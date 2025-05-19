package com.BackPM.BackPM.controllers;

import com.BackPM.BackPM.models.Salida;
import com.BackPM.BackPM.services.IService.ISalidaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salidas")
public class SalidaController extends ABaseController<Salida, ISalidaService> {

    private final ISalidaService salidaService;

    public SalidaController(ISalidaService salidaService) {
        super(salidaService, "Salida");
        this.salidaService = salidaService;
    }

    // Obtener una Salida por el id del registro
    @GetMapping("/registro/{idRegistro}")
    public Salida getByRegistroId(@PathVariable Long idRegistro) {
        return salidaService.findByRegistro_Id(idRegistro);
    }
}
