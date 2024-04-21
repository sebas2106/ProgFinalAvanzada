package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.DTO.DarEstadoNegocioDTO;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRegistro;

public interface IModeradorServicio {

    void ponerEstadoNegocio(DarEstadoNegocioDTO estado)throws Exception;
}
