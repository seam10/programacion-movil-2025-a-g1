package com.BackPM.BackPM.services.ServiceImpl;  
// Define el paquete donde reside la implementación de servicios para la entidad Tarifa

import com.BackPM.BackPM.models.Tarifa;  
// Importa la clase de modelo Tarifa, que representa la entidad de tarifas

import com.BackPM.BackPM.repositories.ITarifaRepository;  
// Importa la interfaz de repositorio ITarifaRepository para acceder a datos de Tarifa

import com.BackPM.BackPM.services.IService.ITarifaService;  
// Importa la interfaz de servicio ITarifaService que define métodos específicos para Tarifa

import org.springframework.stereotype.Service;  
// Importa la anotación @Service de Spring para marcar esta clase como un bean de servicio

@Service  
// Indica a Spring que esta clase es un componente de servicio gestionado automáticamente
public class TarifaService extends ABaseService<Tarifa> implements ITarifaService {
    // Clase de servicio que extiende la lógica genérica de ABaseService para Tarifa
    // e implementa la interfaz ITarifaService con métodos específicos

    private final ITarifaRepository tarifaRepository;  
    // Repositorio inyectado para realizar operaciones CRUD y consultas personalizadas de Tarifa

    /**
     * Constructor que recibe por inyección el repositorio de Tarifa.
     *
     * @param tarifaRepository instancia de ITarifaRepository proporcionada por Spring
     */
    public TarifaService(ITarifaRepository tarifaRepository) {
        this.tarifaRepository = tarifaRepository;  
        // Asigna el repositorio al campo de la clase para usarlo en los métodos
    }

    @Override
    protected ITarifaRepository getRepository() {
        // Método abstracto de ABaseService que devuelve el repositorio genérico
        return tarifaRepository;  
        // Proporciona a la superclase ABaseService el repositorio específico de Tarifa
    }

    @Override
    public Tarifa obtenerPorTipoVehiculo(String tipoVehiculo) {
        // Método específico que busca una Tarifa según el tipo de vehículo
        // Delegamos la búsqueda al método generado automáticamente por Spring Data
        return tarifaRepository.findByTipoVehiculo(tipoVehiculo);
    }
}
// Implementa la lógica de negocio para obtener tarifas según el tipo de vehículo
// y otras operaciones relacionadas con la entidad Tarifa