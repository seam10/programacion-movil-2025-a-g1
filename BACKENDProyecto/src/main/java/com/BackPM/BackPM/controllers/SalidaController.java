package com.BackPM.BackPM.controllers;

// Importación de la clase de modelo Salida
import com.BackPM.BackPM.models.Salida;
// Importación de la interfaz de servicio específica para Salida
import com.BackPM.BackPM.services.IService.ISalidaService;
// Importaciones de anotaciones para controladores REST y CORS
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8100")  
// Permite solicitudes CORS desde http://localhost:8100 (tu frontend Ionic)
@RestController  
// Marca la clase como controlador REST, combinando @Controller y @ResponseBody
@RequestMapping("/api/salidas")  
// Prefijo común para todas las rutas de este controlador: /api/salidas
public class SalidaController extends ABaseController<Salida, ISalidaService> {

    // Servicio específico para manejar la lógica de negocio de Salida
    private final ISalidaService salidaService;

    /**
     * Constructor que inyecta el servicio de Salida.
     * Llama al constructor de la clase padre con el servicio y el nombre de la entidad.
     *
     * @param salidaService instancia de ISalidaService proporcionada por Spring
     */
    public SalidaController(ISalidaService salidaService) {
        super(salidaService, "Salida");  
        this.salidaService = salidaService;
    }

    /**
     * Endpoint para obtener una Salida según el ID de su Registro asociado.
     * Mapea GET /api/salidas/registro/{idRegistro}
     *
     * @param idRegistro identificador del registro cuyo salida se solicita
     * @return objeto Salida correspondiente al registro
     */
    @GetMapping("/registro/{idRegistro}")
    public Salida getByRegistroId(@PathVariable Long idRegistro) {
        // Llama al servicio para buscar la Salida por el ID del Registro
        return salidaService.findByRegistro_Id(idRegistro);
    }
}
//     public ResponseEntity<ApiResponseDto<T>> findById(@PathVariable Long id) {
//         try {
//             // Obtiene la entidad por ID desde el servicio
//             T entity = service.findById(id);
//             // Devuelve 200 OK con la entidad encontrada
//             return ResponseEntity.ok(
//                 new ApiResponseDto<>("Registro encontrado", entity, true)
//             );
//         } catch (Exception e) {
//             // En caso de excepción, devuelve 500 con detalle del error
//             return ResponseEntity.internalServerError()
//                 .body(new ApiResponseDto<>(e.getMessage(), null, false));
//         }
//     }