package co.edu.uniquindio.ProyectoFinal.services.impl;

import co.edu.uniquindio.ProyectoFinal.DTO.ComentarLugarDTO;
import co.edu.uniquindio.ProyectoFinal.DTO.ObtenerComentariosLugarDTO;
import co.edu.uniquindio.ProyectoFinal.DTO.ResponderComentarioDTO;
import co.edu.uniquindio.ProyectoFinal.Repositories.ComentarioRepo;
import co.edu.uniquindio.ProyectoFinal.Repositories.NegocioRepo;
import co.edu.uniquindio.ProyectoFinal.Repositories.UsuarioRepos;
import co.edu.uniquindio.ProyectoFinal.model.Comentario;
import co.edu.uniquindio.ProyectoFinal.model.Negocio;
import co.edu.uniquindio.ProyectoFinal.model.Usuario;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.IComentarioServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IComentarioServicioImpl implements IComentarioServicio {

    private ComentarioRepo comentarioRepo;
    private NegocioRepo negocioRepo;
    private UsuarioRepos usuarioRepo;

    public IComentarioServicioImpl(ComentarioRepo comentarioRepo, NegocioRepo negocioRepo) {
        this.comentarioRepo = comentarioRepo;
        this.negocioRepo = negocioRepo;
    }

    @Override
    public void crearComentario(ComentarLugarDTO cL) throws Exception {


        Optional<Negocio> negocioOptional = negocioRepo.findById(cL.codNegocio());
        if(negocioOptional.isEmpty()){
            throw new Exception("El negocio seleccionado no existe");
        }
        //Optional<Comentario> comentarioOptional = comentarioRepo.findByCodComentario(cL.comentario());
        Optional<Usuario> usuarioOptional = usuarioRepo.findByIdentificacion(cL.codUsuario());
        if(usuarioOptional.isEmpty()){
            throw new Exception("El usuario seleccionado no existe");
        }
        Usuario usuarioEncontrado=usuarioOptional.get();


        //  Valida si es la primera vez agregando un comentario y si es esta ocación inicializa la lista
        Negocio negocioEncontrado=negocioOptional.get();

        if (negocioEncontrado.getListComentarios().isEmpty()){
            List <Comentario>list=new ArrayList<>();
            negocioEncontrado.setListComentarios(list);
        }


        Comentario nuevoComentario=new Comentario();
        nuevoComentario.setCodUsuario(""+usuarioEncontrado.getIdentificacion());
        nuevoComentario.setComentario(""+cL.comentario());
        negocioEncontrado.getListComentarios().add(nuevoComentario);

        Negocio nuevoNegocio=negocioRepo.save(negocioEncontrado);
    }



    @Override
    public void listarComentarios(ObtenerComentariosLugarDTO oC) throws Exception {

    }

    @Override
    public void responderComentario(ResponderComentarioDTO rC) throws Exception {

    }

    @Override
    public void obtenerComentario(ResponderComentarioDTO rC) throws Exception {

    }
}
