package co.edu.uniquindio.ProyectoFinal.services.impl;

import co.edu.uniquindio.ProyectoFinal.DTO.DarEstadoNegocioDTO;
import co.edu.uniquindio.ProyectoFinal.Repositories.NegocioRepo;
import co.edu.uniquindio.ProyectoFinal.Repositories.UsuarioRepos;
import co.edu.uniquindio.ProyectoFinal.model.Negocio;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRegistro;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRevision;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.IModeradorServicio;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.IUsuarioServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ModeradorServicioImpl implements IModeradorServicio {

    private final UsuarioRepos usuarioRepo;
    private final NegocioRepo negocioRepo;

    public ModeradorServicioImpl(UsuarioRepos usuarioRepo, NegocioRepo negocioRepo) {
        this.usuarioRepo = usuarioRepo;
        this.negocioRepo = negocioRepo;
    }


    @Override
    public void ponerEstadoNegocio(DarEstadoNegocioDTO estado) throws Exception {
        if (!existeIDNegocio(estado.idNegocio())) {
            throw new Exception("El correo ya se encuentra registrado");
        }
        Optional<Negocio> opcionalNegocio = negocioRepo.findById(estado.idNegocio());
        Negocio negocio = new Negocio();
        negocio = opcionalNegocio.get();
        negocio.setEstadoRevision(estado.estado());
        if (negocio.getEstadoRevision()==EstadoRevision.APROBADO){
           negocio.setEstadoReg(EstadoRegistro.ACTIVO);
        }else {
            negocio.setEstadoReg(EstadoRegistro.INACTIVO);
        }
        Negocio negocioActualizado = negocioRepo.save(negocio);

    }
    private boolean existeIDNegocio(String cod) {
        return negocioRepo.findById(cod).isPresent();
    }

}
