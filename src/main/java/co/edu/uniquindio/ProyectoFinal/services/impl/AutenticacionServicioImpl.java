package co.edu.uniquindio.ProyectoFinal.services.impl;
import co.edu.uniquindio.ProyectoFinal.DTO.CambioPasswordDTO;
import co.edu.uniquindio.ProyectoFinal.DTO.EnviarLinkRecuPassDTO;
import co.edu.uniquindio.ProyectoFinal.DTO.LoginDTO;
import co.edu.uniquindio.ProyectoFinal.DTO.TokenDTO;
import co.edu.uniquindio.ProyectoFinal.Repositories.UsuarioRepos;

import co.edu.uniquindio.ProyectoFinal.model.Ubicacion;
import co.edu.uniquindio.ProyectoFinal.model.Usuario;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRegistro;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoUsuario;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.IAutenticacionServicio;
import co.edu.uniquindio.ProyectoFinal.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements IAutenticacionServicio {
    private final UsuarioRepos usuarioRepo;
    private final JWTUtils jwtUtils;
    @Override
    public TokenDTO iniciarSesionUsuario(LoginDTO loginDTO) throws Exception {
        Optional<Usuario> usuarioOptional = usuarioRepo.findByEmail(loginDTO.email());
        if (usuarioOptional.isEmpty()) {
            throw new Exception("El correo no se encuentra registrado");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Usuario usuario = usuarioOptional.get();
        if(usuario.getTipoUsuario()== TipoUsuario.MODERADOR){
            throw new Exception("El usuario no tieme permisos para acceder por este modulo");
        }
        if (usuario.getEstadoRegistro()== EstadoRegistro.INACTIVO){
            throw new Exception("El usuario debe estar Activo para realizar cambios");
        }
        String passwordEncriptada = passwordEncoder.encode(loginDTO.password());
        if( !passwordEncoder.matches(loginDTO.password(), usuario.getPassword())) {
            throw new Exception("La contraseña es incorrecta");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", "CLIENTE");
        map.put("nombre", usuario.getNombre());
        map.put("id", usuario.getCodigo());


        return new TokenDTO( jwtUtils.generarToken(usuario.getEmail(), map) );
    }

    @Override
    public TokenDTO iniciarSesionModerador(LoginDTO loginDTO) throws Exception {
        Optional<Usuario> usuarioOptional = usuarioRepo.findByEmail(loginDTO.email());
        if (usuarioOptional.isEmpty()) {
            throw new Exception("El correo no se encuentra registrado");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Usuario usuario = usuarioOptional.get();

        if(!(usuario.getTipoUsuario()== TipoUsuario.MODERADOR)){
            throw new Exception("El usuario no tieme permisos para acceder");
        }
        if (usuario.getEstadoRegistro()== EstadoRegistro.INACTIVO){
            throw new Exception("El usuario debe estar Activo para realizar cambios");
        }
        if( !passwordEncoder.matches(loginDTO.password(), usuario.getPassword()) ) {
            throw new Exception("La contraseña es incorrecta");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", "MODERADOR");
        map.put("nombre", usuario.getNombre());
        map.put("id", usuario.getCodigo());


        return new TokenDTO( jwtUtils.generarToken(usuario.getEmail(), map) );
    }

    @Override
    public TokenDTO cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {





        return null;
    }

    @Override
    public TokenDTO enviarLinkRecuperacion(EnviarLinkRecuPassDTO enviarLinkRecuPassDTO) throws Exception {
        Optional<Usuario>usuarioOptional=usuarioRepo.findByEmail(enviarLinkRecuPassDTO.correoRecuperación());
        if (usuarioOptional.isEmpty()){
            throw new Exception("No se encontro el usuario");
        }
       Usuario encontrado=usuarioOptional.get();


        return null;
    }
}
