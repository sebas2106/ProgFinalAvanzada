package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.DTO.BuscarNegocioDTO;
import co.edu.uniquindio.ProyectoFinal.DTO.ListarComentariosDTO;
import co.edu.uniquindio.ProyectoFinal.DTO.ListarTiposNegocioDTO;
import co.edu.uniquindio.ProyectoFinal.model.Comentario;
import co.edu.uniquindio.ProyectoFinal.model.Negocio;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IPublicoServicio {

    List<Negocio> listarTiposDeNegocio(ListarTiposNegocioDTO listarTiposNegocioDTO) throws Exception;

    List<Negocio> buscarNegocio(BuscarNegocioDTO buscarNegocioDTO) throws Exception;

    List<Comentario> listarComentariosNegocio(ListarComentariosDTO listarComentariosDTO)throws Exception;

    Map subirImagen(MultipartFile imagen) throws Exception;

    Map eliminarImagen(String idImagen) throws Exception;
}
