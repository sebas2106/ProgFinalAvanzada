package co.edu.uniquindio.ProyectoFinal.services.impl;

import co.edu.uniquindio.ProyectoFinal.DTO.*;
import co.edu.uniquindio.ProyectoFinal.Repositories.NegocioRepo;
import co.edu.uniquindio.ProyectoFinal.Repositories.UsuarioRepos;
import co.edu.uniquindio.ProyectoFinal.model.Horario;
import co.edu.uniquindio.ProyectoFinal.model.Negocio;
import co.edu.uniquindio.ProyectoFinal.model.Usuario;
import co.edu.uniquindio.ProyectoFinal.model.enums.*;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.INegocioServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NegocioServicioImpl implements INegocioServicio {

    private final NegocioRepo negocioRepo;
    private final UsuarioRepos usuarioRepo;



    public NegocioServicioImpl(NegocioRepo negocioRepo, UsuarioRepos usuarioRepo) {
        this.negocioRepo = negocioRepo;
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public String crearNegocio(CrearLugarDTO cL) throws Exception {
        //Se crea el objeto negocio
        Negocio negocio = new Negocio();
        Optional<Usuario>usuarioOptional=usuarioRepo.findByIdentificacion(cL.codCreador());
        if (usuarioOptional.isEmpty()){
            throw new Exception("No se encuentra el usuario creador");
        }
        Usuario encontrado=usuarioOptional.get();
        if(encontrado.getCuentaUsuario()==null){
            throw new Exception("El usuario debe tener creada una cuenta");
        }
        if(encontrado.getCuentaUsuario().getEstadoCuenta()==EstadoCuenta.INACTIVA){
            throw new Exception("La cuenta esta: "+encontrado.getCuentaUsuario().getEstadoCuenta());
        }

        //Se le asignan sus campos
        negocio.setCodCreador(cL.codCreador());

        negocio.setNombre(cL.nombre());
        negocio.setDescripcion(cL.descripcion());
        negocio.setTipoNegocio(cL.tipoNegocio());
        negocio.setEstado(EstadoActual.CERRADO);
        negocio.setEstadoReg(EstadoRegistro.INACTIVO);
        negocio.setEstadoRevision(EstadoRevision.PENDIENTE);
        negocio.setListImagenes(cL.listImages());
        negocio.setListTelefonos(cL.listTelefonos());
        negocio.setUbicacion(cL.ubicacion());
        negocio.setListHorario(cL.listHorarios());
        //Se guarda en la base de datos y obtenemos el objeto registrado
        Negocio negocioGuardado = negocioRepo.save(negocio);
        //se guarda el codigo en la cuenta del usuario creador
        encontrado.getCuentaUsuario().getListNegociosAsociados().add(negocioGuardado.getCodNegocio());
       if (negocioGuardado==null){
           throw new Exception("No se ha podido guardar el negocio");
       }
        Usuario usuarioGuardado=usuarioRepo.save(encontrado);
        if (usuarioGuardado==null){
            throw new Exception("No se ha podido guardar el registro en usuario");
        }
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

        negocio.setUbicacion(aL.ubicacion());
        negocio.setListImagenes(aL.listImages());
        negocio.setListTelefonos(aL.listTelefonos());
        negocio.setListHorario(aL.listHorarios());
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
    public Negocio obtenerNegocio(String id) throws Exception {
        Optional<Negocio> negocioOptional = negocioRepo.findById(id);
        if (negocioOptional.isEmpty()) {
            throw new IOException("No se encontro el negocio con la identificación:" + id);
        }
        Negocio negocio = negocioOptional.get();

        LocalTime horaActual = LocalTime.now();
        DayOfWeek diaActual = LocalDate.now().getDayOfWeek();

        negocio.setEstado(estaAbierto(negocio.getListHorario(),diaActual,horaActual));

        return negocio;
    }
    public EstadoActual estaAbierto(List<Horario>horarios,DayOfWeek dia, LocalTime hora) {
        for (Horario horario : horarios) {
            if (horario.getDia().equals(dia) &&
                    hora.isAfter(horario.getHoraInicial()) &&
                    hora.isBefore(horario.getHoraFinal())) {
                return EstadoActual.ABIERTO;
            }
        }
        return EstadoActual.CERRADO;
    }

    @Override
    public List<Negocio> buscarNegociosNomb(String nombre) throws Exception {
        Optional<List<Negocio>> negocioOptional = negocioRepo.findByNombre(nombre);
        if (negocioOptional.isEmpty()) {
            throw new IOException("No se encontro el negocio con la identificación:" + nombre);
        }
        List<Negocio> negocios = negocioOptional.get();
        return negocios;
    }

    @Override
    public List<Negocio> filtrarPorEstado(EstadoRegistro estado) throws Exception {
        Optional<List<Negocio>> negocioOptional = negocioRepo.findByEstadoReg(estado);
        List<Negocio> negociosEncontrados=new ArrayList<>();
       if (!negocioOptional.isEmpty()){
           negociosEncontrados=negocioOptional.get();
       }
   return negociosEncontrados;
    }

    @Override
    public List<Negocio> filtrarPorTipoNegocio(TipoNegocio tipoNegocio) throws Exception {
        Optional<List<Negocio>> negocioOptional = negocioRepo.findByTipoNegocio(tipoNegocio);
        List<Negocio> negociosEncontrados=new ArrayList<>();
        if (!negocioOptional.isEmpty()){
            negociosEncontrados=negocioOptional.get();
        }
        return negociosEncontrados;
    }

    @Override
    public List<Negocio> listarNegociosPropietario(String identificacion) throws Exception {
        Optional<Usuario> usuarioOptional = usuarioRepo.findByIdentificacion(identificacion);
        if (usuarioOptional.isEmpty()){
            throw new Exception("No se encontraron usuarios con este Identificación");
        }
        Optional<List<Negocio>> negocioOptional = negocioRepo.findByCodCreador(identificacion);
        List<Negocio> negociosEncontrados=new ArrayList<>();
        if (!negocioOptional.isEmpty()){
            negociosEncontrados=negocioOptional.get();
        }
        return negociosEncontrados;
    }



    @Override
    public void solicitarRuta(SolicitarRutaDTO s) throws Exception {
//se realiza cuando se trabaje el mapeado
    }


}
