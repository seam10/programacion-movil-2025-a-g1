package com.BackPM.BackPM.controllers;

// Importaciones de las clases de modelo y repositorio necesarias
import com.BackPM.BackPM.models.Espacio;
import com.BackPM.BackPM.models.Registro;
import com.BackPM.BackPM.repositories.IEspacioRepository;
// Importación de la interfaz de servicio específica para Registro
import com.BackPM.BackPM.services.IService.IRegistroService;
// Importaciones de clases para construir respuestas HTTP y anotaciones REST
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8100")  
// Permite solicitudes CORS desde http://localhost:8100 (tu frontend Ionic)
@RestController  
// Marca la clase como controlador REST, combinando @Controller y @ResponseBody
@RequestMapping("/api/registros")  
// Prefijo común para todas las rutas de este controlador: /api/registros
public class RegistroController extends ABaseController<Registro, IRegistroService> {

    // Repositorio de Espacio para validar y actualizar el estado de los espacios
    private final IEspacioRepository espacioRepository;

    /**
     * Constructor que inyecta el servicio de Registro y el repositorio de Espacio.
     * @param registroService instancia de IRegistroService proporcionada por Spring
     * @param espacioRepository instancia de IEspacioRepository proporcionada por Spring
     */
    public RegistroController(IRegistroService registroService,
                              IEspacioRepository espacioRepository) {
        super(registroService, "Registro");  
        // Llama al constructor de ABaseController con el servicio y el nombre de la entidad
        this.espacioRepository = espacioRepository;
    }

    /**
     * Endpoint para crear y guardar un nuevo registro, asegurando que el espacio esté libre.
     * Mapea POST /api/registros/guardar
     * @param registro objeto JSON con los datos del registro y referencia al espacio
     * @return ResponseEntity con el registro guardado o un error adecuado
     */
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarRegistro(@RequestBody Registro registro) {
        try {
            // Obtiene el ID del espacio enviado en el cuerpo de la petición
            Long idEspacio = registro.getEspacio().getId();
            // Busca el espacio en la base de datos o lanza excepción si no existe
            Espacio espacioExistente = espacioRepository.findById(idEspacio)
                    .orElseThrow(() -> new RuntimeException("Espacio no encontrado"));

            // Verifica que el espacio efectivamente esté disponible
            if (!espacioExistente.estaDisponible()) {
                // Retorna 409 Conflict si el espacio ya está ocupado o reservado
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("El espacio ya está ocupado o reservado");
            }

            // Cambia el estado del espacio a OCUPADO
            espacioExistente.setEstado(Espacio.EstadoEspacio.OCUPADO);
            // Guarda el cambio de estado del espacio en la base de datos
            espacioRepository.save(espacioExistente);

            // Asigna el espacio actualizado al registro
            registro.setEspacio(espacioExistente);
            // Guarda el registro usando el servicio genérico (inserción lógica)
            Registro registroGuardado = service.save(registro);

            // Retorna 200 OK con el objeto Registro ya persistido
            return ResponseEntity.ok(registroGuardado);
        } catch (Exception e) {
            // En caso de error, retorna 500 Internal Server Error con mensaje
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el registro: " + e.getMessage());
        }
    }

}
//     return ResponseEntity.ok(
//         new ApiResponseDto<>("Registro guardado", registroGuardado, true)
//     );