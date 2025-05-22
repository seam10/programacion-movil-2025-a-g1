package com.BackPM.BackPM.services.ServiceImpl;
// Define el paquete donde se ubica la implementación de servicios para las entidades

import com.BackPM.BackPM.models.Espacio;
// Importa la entidad Espacio que maneja este servicio

import com.BackPM.BackPM.repositories.IBaseRepository;
// Importa la interfaz genérica de repositorio necesaria en ABaseService

import com.BackPM.BackPM.repositories.IEspacioRepository;
// Importa el repositorio específico de Espacio para consultas personalizadas

import com.BackPM.BackPM.services.IService.IEspacioService;
// Importa la interfaz de servicio que define métodos particulares de Espacio

import org.springframework.stereotype.Service;
// Importa la anotación @Service para marcar esta clase como componente de servicio

import java.util.List;
// Importa la interfaz List para trabajar con colecciones de Espacio


@Service
// Marca la clase como un bean de servicio gestionado por Spring
public class EspacioService extends ABaseService<Espacio> implements IEspacioService {

    // Repositorio específico para persistencia y consultas de Espacio
    private final IEspacioRepository espacioRepository;

    /**
     * Constructor que inyecta el repositorio de Espacio.
     * @param espacioRepository instancia proporcionada por Spring
     */
    public EspacioService(IEspacioRepository espacioRepository) {
        this.espacioRepository = espacioRepository;
    }

    @Override
    protected IBaseRepository<Espacio, Long> getRepository() {
        // Proporciona a la superclase ABaseService el repositorio genérico a usar
        return espacioRepository;
    }

    /**
     * Método personalizado para obtener solo los espacios disponibles.
     * @return lista de espacios cuyo estado es DISPONIBLE
     */
    public List<Espacio> obtenerDisponibles() {
        // Llama al método definido en IEspacioRepository con consulta JPQL
        return espacioRepository.findDisponibles();
    }

    @Override
    public void resetearTodos() {
        // Recupera todos los espacios (sin filtrar)
        List<Espacio> espacios = espacioRepository.findAll();
        // Itera sobre cada espacio y cambia su estado a DISPONIBLE
        for (Espacio espacio : espacios) {
            espacio.setEstado(Espacio.EstadoEspacio.DISPONIBLE);
        }
        // Guarda en lote todos los cambios realizados
        espacioRepository.saveAll(espacios);
    }
}
