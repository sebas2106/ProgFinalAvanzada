package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IImagenesServicio {
    Map subirImagen(MultipartFile imagen) throws Exception;

    Map eliminarImagen(String idImagen) throws Exception;
}
