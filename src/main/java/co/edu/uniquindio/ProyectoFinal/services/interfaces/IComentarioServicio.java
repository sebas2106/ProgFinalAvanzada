package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.DTO.ComentarLugarDTO;
import co.edu.uniquindio.ProyectoFinal.DTO.ObtenerComentariosLugarDTO;
import co.edu.uniquindio.ProyectoFinal.DTO.ResponderComentarioDTO;

public interface IComentarioServicio {

    void crearComentario(ComentarLugarDTO cL)throws Exception;
    void listarComentarios(ObtenerComentariosLugarDTO oC)throws Exception;
    void responderComentario(ResponderComentarioDTO rC)throws Exception;
}
