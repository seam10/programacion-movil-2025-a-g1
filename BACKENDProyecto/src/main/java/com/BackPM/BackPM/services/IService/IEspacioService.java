package com.BackPM.BackPM.services.IService;


import com.BackPM.BackPM.models.Espacio;

import java.util.List;

public interface IEspacioService extends IBaseService<Espacio> {

    // Método específico para obtener los espacios disponibles
    List<Espacio> obtenerDisponibles();
}

