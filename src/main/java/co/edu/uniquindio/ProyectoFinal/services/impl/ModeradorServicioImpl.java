package co.edu.uniquindio.ProyectoFinal.services.impl;

import co.edu.uniquindio.ProyectoFinal.DTO.*;
import co.edu.uniquindio.ProyectoFinal.Repositories.HistorialRevisionRepo;
import co.edu.uniquindio.ProyectoFinal.Repositories.NegocioRepo;
import co.edu.uniquindio.ProyectoFinal.Repositories.UsuarioRepos;
import co.edu.uniquindio.ProyectoFinal.model.HistorialRevision;
import co.edu.uniquindio.ProyectoFinal.model.Negocio;
import co.edu.uniquindio.ProyectoFinal.model.Usuario;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRegistro;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRevision;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.IEmailServicio;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.IModeradorServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ModeradorServicioImpl implements IModeradorServicio {

    private final UsuarioRepos usuarioRepo;
    private final NegocioRepo negocioRepo;
    private final HistorialRevisionRepo historialRepo;
    private final IEmailServicio servicioCorreo;

    public ModeradorServicioImpl(UsuarioRepos usuarioRepo, NegocioRepo negocioRepo, HistorialRevisionRepo historialRepo, IEmailServicio servicioCorreo) {
        this.usuarioRepo = usuarioRepo;
        this.negocioRepo = negocioRepo;
        this.historialRepo = historialRepo;

        this.servicioCorreo = servicioCorreo;
    }


    @Override
    public void ponerEstadoNegocio(DarEstadoNegocioDTO estado) throws Exception {
        if (!existeIDNegocio(estado.idNegocio())) {
            throw new Exception("El negocio no se encuentra registrado");
        }

        Optional<Negocio> opcionalNegocio = negocioRepo.findById(estado.idNegocio());
        Negocio negocio = new Negocio();
        negocio = opcionalNegocio.get();
        negocio.setEstadoRevision(estado.estado());
        if (negocio.getEstadoRevision() == EstadoRevision.APROBADO) {
            negocio.setEstadoReg(EstadoRegistro.ACTIVO);
        } else {
            negocio.setEstadoReg(EstadoRegistro.INACTIVO);
        }
        Negocio negocioActualizado = negocioRepo.save(negocio);

        String idCreador = negocioActualizado.getCodCreador();

        HistorialRevision nuevoRegistro = new HistorialRevision();
        nuevoRegistro.setCodNegocio(negocio.getCodNegocio());
        nuevoRegistro.setCodModerador("ModeradorGenerico");
        nuevoRegistro.setFecha(LocalDateTime.now());
        nuevoRegistro.setEstadoRevision(estado.estado());
        nuevoRegistro.setDescripcion("hola mundo");

        HistorialRevision revision = historialRepo.save(nuevoRegistro);
        if (revision == null) {
            throw new Exception("No se ha guardado la revisión");
        }
        notificarNuevoEstadoNegcio(idCreador, estado.estado(), negocio.getNombre());

    }

    private void notificarNuevoEstadoNegcio(String idCreador, EstadoRevision estado, String nombreNegocio) throws Exception {
        Optional<Usuario> usuarioOptional = usuarioRepo.findByIdentificacion(idCreador);
        if (usuarioOptional.isEmpty()) {
            throw new Exception("El usuario a enviar el correo no se ha encontrado");
        }
        Usuario encontrado = usuarioOptional.get();
        String asunto = "Notificación-nuevo estado Negocio: " + nombreNegocio;
        String mensaje = "El estado de tu negocio " + nombreNegocio + " ha sido actualizado a: " + estado;
        EmailDTO correoDTO = new EmailDTO(asunto, encontrado.getEmail(), mensaje);

        servicioCorreo.enviarCorreo(correoDTO);

    }

    @Override
    public List<Negocio> filtrarNegocioEstado(ObtenerNegocioEstadoRevDTO obtenerNegocioEstadoDTO) throws Exception {
        Optional<List<Negocio>> negociosListOptional = negocioRepo.findByEstadoRevision(obtenerNegocioEstadoDTO.esadoRevision());
        if (negociosListOptional.isEmpty()) {
            throw new Exception("No se ha encontrado ningun negocio en este estado de revision");
        }
        List<Negocio> listaEncontrada = negociosListOptional.get();
        return listaEncontrada;
    }

    @Override
    public Usuario obtenerModerador(ObtenerModeradorIdentiDTO identModerad) throws Exception {
        Optional<Usuario> moderadorOptional = usuarioRepo.findByIdentificacion(identModerad.identModerador());
        if (moderadorOptional.isEmpty()) {
            throw new Exception("No se ha encontrado moderador con identificación: " + identModerad.identModerador());
        }
        Usuario encontrado = moderadorOptional.get();

        return encontrado;
    }

    @Override
    public List<HistorialRevision> obtenerHistorialRevisionesModerador(ObtenerHistorialRevisionModerador obtenerHistorial) throws Exception {
        Optional<List<HistorialRevision>> historialrevisionesMod = historialRepo.findHistorialRevisionByCodModerador(obtenerHistorial.idModerador());
        if (historialrevisionesMod.isEmpty()){
            throw new Exception("No se han encontradorevisiones realizadas por esste moderador");
        }
        return null;
    }

    private boolean existeIDNegocio(String cod) {
        return negocioRepo.findById(cod).isPresent();
    }

}
