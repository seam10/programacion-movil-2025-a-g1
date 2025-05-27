package com.BackPM.BackPM.services.ServiceImpl;
// Define el paquete donde reside la implementación de servicios específicos

import com.BackPM.BackPM.models.Registro;
// Importa la entidad Registro, que es el tipo gestionado por este servicio

import com.BackPM.BackPM.repositories.IRegistroRepository;
// Importa el repositorio específico para Registro, que extiende IBaseRepository

import com.BackPM.BackPM.services.IService.IRegistroService;
// Importa la interfaz de servicio que define métodos adicionales para Registro

import org.springframework.stereotype.Service;
// Importa la anotación @Service para marcar esta clase como componente de servicio de Spring

@Service
// Marca la clase como un bean de servicio, detectado automáticamente por Spring
public class RegistroService extends ABaseService<Registro> implements IRegistroService {
    // Clase de servicio que extiende la lógica genérica de ABaseService para Registro
    // e implementa la interfaz personalizada IRegistroService

    private final IRegistroRepository registroRepository;
    // Repositorio inyectado para operaciones de persistencia específicas de Registro

    /**
     * Constructor que inyecta el repositorio de Registro.
     * @param registroRepository instancia proporcionada por Spring
     */
    public RegistroService(IRegistroRepository registroRepository) {
        this.registroRepository = registroRepository;
        // Asigna el repositorio a la propiedad de la clase para usarlo en los métodos
    }

    @Override
    protected IRegistroRepository getRepository() {
        // Proporciona el repositorio genérico a la superclase ABaseService
        return registroRepository;
    }

    @Override
    public Registro obtenerPorIdEspacio(Long idEspacio) {
        // Método personalizado para buscar un Registro por el ID de su espacio asociado
        return registroRepository.findByEspacioId(idEspacio);
    }

    @Override
    public Registro guardarRegistro(Registro registro) {
        // Método personalizado que encapsula la lógica de guardado de un nuevo Registro
        // Aquí se podrían agregar validaciones adicionales antes de persistir
        return registroRepository.save(registro);
        // Guarda el registro en la base de datos y devuelve la entidad persistida
    }

}
