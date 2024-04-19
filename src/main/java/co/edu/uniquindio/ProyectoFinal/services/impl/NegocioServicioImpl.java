package co.edu.uniquindio.ProyectoFinal.services.impl;

import co.edu.uniquindio.ProyectoFinal.DTO.*;
import co.edu.uniquindio.ProyectoFinal.Repositories.NegocioRepo;
import co.edu.uniquindio.ProyectoFinal.Repositories.UsuarioRepos;
import co.edu.uniquindio.ProyectoFinal.model.Negocio;
import co.edu.uniquindio.ProyectoFinal.model.Usuario;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoActual;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRegistro;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRevision;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.INegocioServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NegocioServicioImpl implements INegocioServicio {

    private final NegocioRepo negocioRepo;

    public NegocioServicioImpl(NegocioRepo negocioRepo) {
        this.negocioRepo = negocioRepo;
    }

    @Override
    public String crearNegocio(CrearLugarDTO cL) throws Exception {
        //Se crea el objeto negocio
        Negocio negocio = new Negocio();

        //Se le asignan sus campos
        negocio.setCodCreador(cL.codCreador());
        negocio.setNombre(cL.nombre());
        negocio.setDescripcion(cL.descripcion());
        negocio.setTipoNegocio(cL.tipoNegocio());
        negocio.setListHorario(cL.listHorarios());
        negocio.setEstado(EstadoActual.CERRADO);
        negocio.setEstadoReg(EstadoRegistro.INACTIVO);
        negocio.setEstadoRevision(EstadoRevision.PENDIENTE);
        negocio.setListImagenes(cL.listImages());
        negocio.setListTelefonos(cL.listTelefonos());
        negocio.setUbicacion(cL.ubicacion());
        //Se guarda en la base de datos y obtenemos el objeto registrado
        Negocio negocioGuardado = negocioRepo.save(negocio);
        //Retornamos el id (código) del negocio registrado
        return negocioGuardado.getCodNegocio();
    }

    @Override
    public String actualizarNegocio(ActualizarLugarDTO aL) throws Exception {
        if (!existeIDNegocio(aL.cod())) {
            throw new Exception("El correo ya se encuentra registrado");
        }
        Optional<Negocio> opcionalNegocio = negocioRepo.findById(aL.cod());
        Negocio negocio = new Negocio();
        negocio = opcionalNegocio.get();

        negocio.setNombre(aL.nombre());
        negocio.setDescripcion(aL.descripcion());
        negocio.setTipoNegocio(aL.tipoNegocio());

        //se vuelven a poner estos estados para la nueva revision
        negocio.setEstado(EstadoActual.CERRADO);
        negocio.setEstadoReg(EstadoRegistro.INACTIVO);
        negocio.setEstadoRevision(EstadoRevision.PENDIENTE);

        //Se guarda en la base de datos y obtenemos el objeto registrado
        Negocio negocioActualizado = negocioRepo.save(negocio);
        //Retornamos el id (código) del cliente registrado
        return negocioActualizado.getCodNegocio();
    }

    private boolean existeIDNegocio(String cod) {
        return negocioRepo.findById(cod).isPresent();
    }

    @Override
    public String eliminarNegocio(EliminarLugarDTO eL) throws Exception {
        //Buscamos el Negocio que se quiere eliminar
        Optional<Negocio> optionalNegocio = negocioRepo.findById(eL.id()); //el id no es la identificacion si no el codigo
        //Si no se encontró el usuario, lanzamos una excepción
        if (optionalNegocio.isEmpty()) {
            throw new Exception("No se encontró el Negocio a eliminar");
        }
        //Obtenemos el usuario que se quiere eliminar y le asignamos el estado inactivo
        Negocio negocio = optionalNegocio.get();
        negocio.setEstadoReg(EstadoRegistro.INACTIVO);
        //Como el objeto usuario ya tiene un id, el save() no crea un nuevo registro sino que actualiza el que ya existe
        negocioRepo.save(negocio);
        return "" + negocio.getNombre();
    }

    @Override
    public Negocio obtenerNegocio(ObtenerNegocioDTO oN) throws Exception {
        Optional<Negocio> negocioOptional = negocioRepo.findById(oN.idNegocio());
        if (negocioOptional.isEmpty()) {
            throw new IOException("No se encontro el negocio con la identificación:" + oN.idNegocio());
        }
        Negocio negocio = negocioOptional.get();
        return negocio;
    }

    @Override
    public void listarNegociosUsuario(String idUsuario) throws Exception {

    }

    @Override
    public List<Negocio> buscarNegociosNomb(BuscarLugarNomTipDistDTO bL) throws Exception {
        Optional<List<Negocio>> negocioOptional = negocioRepo.findByNombre(bL.nombre());
        if (negocioOptional.isEmpty()) {
            throw new IOException("No se encontro el negocio con la identificación:" + bL.nombre());
        }
        List<Negocio> negocios = negocioOptional.get();
        return negocios;
    }

    @Override
    public void filtrarPorEstado(EstadoRegistro estado) throws Exception {

    }

    @Override
    public void listarNegociosPropietario(ListarLugaresPropietarioDTO listar) throws Exception {

    }

    @Override
    public void solicitarRuta(SolicitarRutaDTO s) throws Exception {

    }


}
