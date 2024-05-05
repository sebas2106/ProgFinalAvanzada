package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.DTO.DarEstadoNegocioDTO;
import co.edu.uniquindio.ProyectoFinal.DTO.ObtenerHistorialRevisionModerador;
import co.edu.uniquindio.ProyectoFinal.DTO.ObtenerModeradorIdentiDTO;
import co.edu.uniquindio.ProyectoFinal.DTO.ObtenerNegocioEstadoRevDTO;
import co.edu.uniquindio.ProyectoFinal.model.HistorialRevision;
import co.edu.uniquindio.ProyectoFinal.model.Negocio;
import co.edu.uniquindio.ProyectoFinal.model.Usuario;

import java.util.List;

public interface IModeradorServicio {

    void ponerEstadoNegocio(DarEstadoNegocioDTO estado)throws Exception;
    List<Negocio> filtrarNegocioEstado(ObtenerNegocioEstadoRevDTO obtenerNegocioEstadoDTO)throws Exception;
    Usuario obtenerModerador(ObtenerModeradorIdentiDTO identModerad)throws Exception;

    List<HistorialRevision>obtenerHistorialRevisionesModerador(ObtenerHistorialRevisionModerador obtenerHistorial)throws Exception;
}
