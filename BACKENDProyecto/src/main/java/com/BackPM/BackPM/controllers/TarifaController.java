package com.BackPM.BackPM.controllers;

// Importación de la clase de modelo Tarifa
import com.BackPM.BackPM.models.Tarifa;
// Importación de la interfaz de servicio específica para Tarifa
import com.BackPM.BackPM.services.IService.ITarifaService;
// Importaciones de anotaciones para controladores REST y CORS
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8100")
// Permite solicitudes CORS desde http://localhost:8100 (tu frontend Ionic)
@RestController
// Marca la clase como controlador REST, combinando @Controller y @ResponseBody
@RequestMapping("/api/tarifas")
// Prefijo común para todas las rutas de este controlador: /api/tarifas
public class TarifaController extends ABaseController<Tarifa, ITarifaService> {

    // Servicio específico para manejar la lógica de negocio de Tarifa
    private final ITarifaService tarifaService;

    /**
     * Constructor que inyecta el servicio de Tarifa y llama al constructor de la clase padre.
     *
     * @param tarifaService instancia de ITarifaService proporcionada por Spring
     */
    public TarifaController(ITarifaService tarifaService) {
        super(tarifaService, "Tarifa");
        // Inicializa el servicio genérico en ABaseController y el nombre de entidad
        this.tarifaService = tarifaService;
    }

    /**
     * Endpoint para obtener una tarifa según el tipo de vehículo.
     * Mapea GET /api/tarifas/tipo/{tipoVehiculo}
     *
     * @param tipoVehiculo valor tomado de la ruta que identifica el tipo de vehículo
     * @return objeto Tarifa correspondiente al tipo proporcionado
     */
    @GetMapping("/tipo/{tipoVehiculo}")
    public Tarifa getByTipoVehiculo(@PathVariable String tipoVehiculo) {
        // Llama al servicio para buscar la tarifa por tipo de vehículo
        return tarifaService.obtenerPorTipoVehiculo(tipoVehiculo);
    }
}
//     * @return ResponseEntity con el objeto Tarifa correspondiente al tipo de vehículo
//     * @throws Exception si ocurre algún error al buscar la tarifa