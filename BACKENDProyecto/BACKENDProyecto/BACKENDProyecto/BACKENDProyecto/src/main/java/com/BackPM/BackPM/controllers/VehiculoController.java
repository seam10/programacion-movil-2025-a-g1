package com.BackPM.BackPM.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController extends ABaseController<Vehiculo, IVehiculoService>{

    private final IVehiculoService vehiculoService;

    public VehiculoController(IVehiculoService vehiculoService) {
        super(vehiculoService, "Vehiculo");
        this.vehiculoService = vehiculoService;
    }

    @GetMapping("/placa/{placa}")
    public Vehiculo getByPlaca(@PathVariable String placa) {
        return vehiculoService.obtenerPorPlaca(placa);
    }
}

