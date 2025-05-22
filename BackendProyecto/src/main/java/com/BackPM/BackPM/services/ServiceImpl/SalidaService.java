package com.BackPM.BackPM.services.ServiceImpl;
// Define el paquete donde reside la implementación de servicios para la entidad Salida

import com.BackPM.BackPM.models.Salida;
// Importa la clase de modelo Salida, que representa la entidad de salida de vehículos

import com.BackPM.BackPM.repositories.ISalidaRepository;
// Importa la interfaz de repositorio ISalidaRepository para acceder a datos de Salida

import com.BackPM.BackPM.services.IService.ISalidaService;
// Importa la interfaz de servicio ISalidaService que define métodos específicos para Salida

import org.springframework.stereotype.Service;
// Importa la anotación @Service de Spring para marcar esta clase como un bean de servicio

@Service
// Indica a Spring que esta clase es un componente de servicio gestionado automáticamente
public class SalidaService extends ABaseService<Salida> implements ISalidaService {
    // Clase de servicio que extiende la lógica genérica de ABaseService para la entidad Salida
    // e implementa la interfaz ISalidaService con métodos específicos

    private final ISalidaRepository salidaRepository;
    // Repositorio inyectado para realizar operaciones CRUD y consultas personalizadas de Salida

    /**
     * Constructor que inyecta el repositorio de Salida.
     * @param salidaRepository instancia de ISalidaRepository proporcionada por Spring
     */
    public SalidaService(ISalidaRepository salidaRepository) {
        this.salidaRepository = salidaRepository;
        // Asigna el repositorio a la propiedad de la clase para usarlo en los métodos
    }

    @Override
    protected ISalidaRepository getRepository() {
        // Proporciona a la superclase ABaseService el repositorio específico de Salida
        return salidaRepository;
    }

    @Override
    public Salida findByRegistro_Id(Long idRegistro) {
        // Método específico que busca una Salida por el ID de su Registro asociado
        // Delegamos la búsqueda al método generado automáticamente por Spring Data
        return salidaRepository.findByRegistro_Id(idRegistro);
    }
}
