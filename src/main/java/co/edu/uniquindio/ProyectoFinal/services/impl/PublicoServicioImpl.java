package co.edu.uniquindio.ProyectoFinal.services.impl;

import co.edu.uniquindio.ProyectoFinal.DTO.BuscarNegocioDTO;
import co.edu.uniquindio.ProyectoFinal.DTO.ListarComentariosDTO;
import co.edu.uniquindio.ProyectoFinal.DTO.ListarTiposNegocioDTO;
import co.edu.uniquindio.ProyectoFinal.Repositories.NegocioRepo;
import co.edu.uniquindio.ProyectoFinal.model.Comentario;
import co.edu.uniquindio.ProyectoFinal.model.Negocio;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.IPublicoServicio;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PublicoServicioImpl implements IPublicoServicio {

    private NegocioRepo negocioRepository;


    @Override
    public void listarTiposDeNegocio(ListarTiposNegocioDTO listarTiposNegocioDTO) throws Exception {

    }

    @Override
    public List<Negocio> buscarNegocio(BuscarNegocioDTO buscarNegocioDTO) throws Exception {
        Optional<List<Negocio>> listNegocioOptional = negocioRepository.findByNombreContainingIgnoreCase(buscarNegocioDTO.palabraClave());
        List<Negocio> listaObtenida = listNegocioOptional.get();
        return listaObtenida;
    }

    @Override
    public List<Comentario> listarComentariosNegocio(ListarComentariosDTO listarComentariosDTO) throws Exception {
        Optional<Negocio> negocioOptional = negocioRepository.findById(listarComentariosDTO.codNegocio());
        if (negocioOptional.isEmpty()) {
            throw new Exception("No se ha encontrado el negocio");
        }


        return null;
    }

    @Override
    public Map subirImagen(MultipartFile imagen) throws Exception {
        return null;
    }

    @Override
    public Map eliminarImagen(String idImagen) throws Exception {
        return null;
    }
}
