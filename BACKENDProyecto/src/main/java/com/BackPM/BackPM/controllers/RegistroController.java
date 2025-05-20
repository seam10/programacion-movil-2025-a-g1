package com.BackPM.BackPM.controllers;


import com.BackPM.BackPM.models.Espacio;
import com.BackPM.BackPM.models.Registro;
import com.BackPM.BackPM.repositories.IEspacioRepository;
import com.BackPM.BackPM.services.IService.IRegistroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("/api/registros")
public class RegistroController extends ABaseController<Registro, IRegistroService>{

    private final IEspacioRepository espacioRepository;

    public RegistroController(IRegistroService registroService, IEspacioRepository espacioRepository) {
        super(registroService, "Registro");
        this.espacioRepository = espacioRepository;
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarRegistro(@RequestBody Registro registro) {
        try {
            Long idEspacio = registro.getEspacio().getId();
            Espacio espacioExistente = espacioRepository.findById(idEspacio)
                    .orElseThrow(() -> new RuntimeException("Espacio no encontrado"));

            // Verifica que el espacio esté disponible
            if (!espacioExistente.estaDisponible()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("El espacio ya está ocupado o reservado");
            }

            // Marca el espacio como OCUPADO
            espacioExistente.setEstado(Espacio.EstadoEspacio.OCUPADO);
            espacioRepository.save(espacioExistente);

            // Guarda el registro con el espacio ya actualizado
            registro.setEspacio(espacioExistente);
            Registro registroGuardado = service.save(registro);

            return ResponseEntity.ok(registroGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el registro: " + e.getMessage());
        }
    }




}


