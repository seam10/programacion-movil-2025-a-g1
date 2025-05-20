package com.BackPM.BackPM.services.IService;

import com.BackPM.BackPM.models.Salida;

public interface ISalidaService extends IBaseService<Salida> {
    Salida findByRegistro_Id(Long idRegistro);
}
