package com.BackPM.BackPM.controllers;

// Importación de la clase de modelo Espacio
import com.BackPM.BackPM.models.Espacio;
// Importación de la interfaz de servicio específica para Espacio
import com.BackPM.BackPM.services.IService.IEspacioService;
// Importaciones de anotaciones para controladores REST y CORS
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8100")
// Permite solicitudes CORS desde el origen http://localhost:8100 (tu frontend Ionic)
@RestController
// Marca la clase como controlador REST, combina @Controller y @ResponseBody
@RequestMapping("/api/espacios")
// Prefijo común para todas las rutas de este controlador: /api/espacios
public class EspacioController extends ABaseController<Espacio, IEspacioService> {

    // Servicio específico para manejar la lógica de negocio de Espacio
    private final IEspacioService espacioService;

    /**
     * Constructor que inyecta el servicio y llama al constructor de la clase padre.
     * @param espacioService instancia de IEspacioService proporcionada por Spring
     */
    public EspacioController(IEspacioService espacioService) {
        super(espacioService, "Espacio"); 
        // Llama al constructor de ABaseController con el servicio y el nombre de la entidad
        this.espacioService = espacioService;
    }

    /**
     * Endpoint para obtener la lista de espacios disponibles.
     * Mapea GET /api/espacios/disponibles
     * @return lista de objetos Espacio con estado DISPONIBLE
     */
    @GetMapping("/disponibles")
    public List<Espacio> getDisponibles() {
        return espacioService.obtenerDisponibles();
    }

    /**
     * Endpoint para resetear todos los espacios, marcándolos como DISPONIBLES.
     * Mapea PUT /api/espacios/reset
     * @return mensaje de confirmación
     */
    @PutMapping("/reset")
    public String resetearEspacios() {
        espacioService.resetearTodos();
        return "Todos los espacios fueron marcados como DISPONIBLES.";
    }
}
//     * Mapea GET /{id} a este método.
//     * @param id ID del registro a buscar en la base de datos de Espacio
//     * @return objeto Espacio con el ID proporcionado