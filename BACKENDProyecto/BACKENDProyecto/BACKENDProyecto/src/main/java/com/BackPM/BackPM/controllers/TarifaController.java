package com.BackPM.BackPM.controllers;


import com.BackPM.BackPM.models.Tarifa;
import com.BackPM.BackPM.services.IService.ITarifaService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("/api/tarifas")
public class TarifaController extends ABaseController<Tarifa, ITarifaService> {

    private final ITarifaService tarifaService;

    public TarifaController(ITarifaService tarifaService) {
        super(tarifaService, "Tarifa");
        this.tarifaService = tarifaService;
    }

    @GetMapping("/tipo/{tipoVehiculo}")
    public Tarifa getByTipoVehiculo(@PathVariable String tipoVehiculo) {
        return tarifaService.obtenerPorTipoVehiculo(tipoVehiculo);
    }
}

