package co.edu.uniquindio.ProyectoFinal.services.impl;

import co.edu.uniquindio.ProyectoFinal.DTO.*;
import co.edu.uniquindio.ProyectoFinal.Repositories.NegocioRepo;
import co.edu.uniquindio.ProyectoFinal.Repositories.UsuarioRepos;
import co.edu.uniquindio.ProyectoFinal.model.Negocio;
import co.edu.uniquindio.ProyectoFinal.model.Usuario;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRegistro;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoUsuario;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.IUsuarioServicio;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServicioImpl implements IUsuarioServicio {

    private final UsuarioRepos usuarioRepo;
    private final NegocioRepo negocioRepo;

    public UsuarioServicioImpl(UsuarioRepos usuarioRepo, NegocioRepo negocioRepo) {
        this.usuarioRepo = usuarioRepo;
        this.negocioRepo = negocioRepo;
    }

    @Override
    public String registrarse(RegistroUsuarioDTO registroUsuarioDTO) throws Exception {
        //Se crea el objeto Cliente
        Usuario user = new Usuario();
        if (existeIdentificacion(registroUsuarioDTO.identificacion())) {
            throw new Exception("El correo ya se encuentra registrado");
        }
        if (existeEmail(registroUsuarioDTO.email())) {
            throw new Exception("El correo ya se encuentra registrado");
        }
        if (existeNickname(registroUsuarioDTO.username())) {
            throw new Exception("El nickname ya se encuentra registrado por otro usuario");
        }
        //Se le asignan sus campos
        user.setIdentificacion(registroUsuarioDTO.identificacion());
        user.setNombre(registroUsuarioDTO.nombre());
        user.setUsername(registroUsuarioDTO.username());
        //encripta la contraseña antes de guardar
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(registroUsuarioDTO.password());
        user.setPassword(passwordEncriptada);

        user.setPassword(passwordEncriptada);
        user.setEmail(registroUsuarioDTO.email());

        //estos datos estan por defecto al registrarse
        user.setEstadoRegistro(EstadoRegistro.ACTIVO);
        user.setTipoUsuario(TipoUsuario.CLIENTE);
        //Se guarda en la base de datos y obtenemos el objeto registrado
        Usuario userGuardado = usuarioRepo.save(user);
        //Retornamos el id (código) del cliente registrado
        return userGuardado.getCodigo();
    }
    @Override
    public String registrarseModerador(RegistroUsuarioDTO registroUsuarioDTO) throws Exception {
        //Se crea el objeto Cliente
        Usuario user = new Usuario();
        if (existeIdentificacion(registroUsuarioDTO.identificacion())) {
            throw new Exception("El correo ya se encuentra registrado");
        }
        if (existeEmail(registroUsuarioDTO.email())) {
            throw new Exception("El correo ya se encuentra registrado");
        }
        if (existeNickname(registroUsuarioDTO.username())) {
            throw new Exception("El nickname ya se encuentra registrado por otro usuario");
        }
        //Se le asignan sus campos
        user.setIdentificacion(registroUsuarioDTO.identificacion());
        user.setNombre(registroUsuarioDTO.nombre());
        user.setUsername(registroUsuarioDTO.username());
        //encripta la contraseña antes de guardar
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(registroUsuarioDTO.password());
        user.setPassword(passwordEncriptada);

        user.setPassword(passwordEncriptada);
        user.setEmail(registroUsuarioDTO.email());

        //estos datos estan por defecto al registrarse
        user.setEstadoRegistro(EstadoRegistro.ACTIVO);
        user.setTipoUsuario(TipoUsuario.MODERADOR);
        //Se guarda en la base de datos y obtenemos el objeto registrado
        Usuario userGuardado = usuarioRepo.save(user);
        //Retornamos el id (código) del cliente registrado
        return userGuardado.getCodigo();
    }
    private boolean existeIdentificacion(String identificacion) {
        return usuarioRepo.findByIdentificacion(identificacion).isPresent();
    }

    private boolean existeNickname(String username) {
        return usuarioRepo.findByUsername(username).isPresent();
    }

    private boolean existeEmail(String email) {
        return usuarioRepo.findByEmail(email).isPresent();
    }

    @Override
    public String actualizarUsuario(ActualizarUsuarioDTO actuaizarUsuarioDTO) throws Exception {
        //Buscamos el usuario que se quiere actualizar
        Optional<Usuario> optionalUser = usuarioRepo.findByIdentificacion(actuaizarUsuarioDTO.identificacion());
        //Si no se encontró el usuario, lanzamos una excepción
        if (optionalUser.isEmpty()) {
            throw new Exception("No se encontró el usuario a actualizar");
        }
        //Obtenemos el Usuario que se quiere actualizar y le asignamos los nuevos valores (el nickname no se puede cambiar)
        Usuario user = optionalUser.get();
        user.setNombre(actuaizarUsuarioDTO.nombre());
        user.setFotoPerfil(actuaizarUsuarioDTO.fotoPerfil());
        user.setEmail(actuaizarUsuarioDTO.email());
        user.setListTelefonos(actuaizarUsuarioDTO.listTelefonos());
        //Como el objeto usuario ya tiene un id, el save() no crea un nuevo registro sino que actualiza el que ya existe
        usuarioRepo.save(user);
        return user.getIdentificacion();
    }

    @Override
    public void borrarUsuario(String id) throws Exception {
        //Buscamos el usuario que se quiere eliminar
        Optional<Usuario> optionalCliente = usuarioRepo.findByIdentificacion(id); //el id no es la identificacion si no el codigo
        //Si no se encontró el usuario, lanzamos una excepción
        if (optionalCliente.isEmpty()) {
            throw new Exception("No se encontró el Usuario a eliminar");
        }

        //Obtenemos el usuario que se quiere eliminar y le asignamos el estado inactivo
        Usuario user = optionalCliente.get();
        user.setEstadoRegistro(EstadoRegistro.INACTIVO);
        //Como el objeto usuario ya tiene un id, el save() no crea un nuevo registro sino que actualiza el que ya existe
        usuarioRepo.save(user);
    }

    @Override
    public void iniciarSesion(InicioSesionDTO inicioSesionDTO) throws Exception {

    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {

    }

    @Override
    public String guardarFavorito(AddFavoriteDTO addFav) throws Exception {

        Optional<Usuario> usuarioOptional = usuarioRepo.findByIdentificacion(addFav.identificacionUserActual());
        //adicionamos el id de negocios favoritos al la lista del usuario
        if (usuarioOptional.isEmpty()) {
            throw new Exception("No se encontró el Usuario");
        }
        Optional<Negocio> negocioOptional = negocioRepo.findById(addFav.idLugarSeleccionado());
        if (negocioOptional.isEmpty()){
            throw new Exception("No Existe esa ID de negocio");
        }
        Usuario usuario = usuarioOptional.get();
        if (usuario.getListNegocioFavorito()==null|| usuario.getListNegocioFavorito().isEmpty()) {
            List<String> lista = new ArrayList<>();
            usuario.setListNegocioFavorito(lista);
        }
        if(validarNegocioEnUser(addFav.idLugarSeleccionado(),usuario.getListNegocioFavorito())){
            throw new Exception("No se puede adicionar un negocio mas de una vez");
        }
        usuario.getListNegocioFavorito().add(addFav.idLugarSeleccionado());
        //guardamos
        usuarioRepo.save(usuario);

        return ""+addFav.idLugarSeleccionado();

    }

    private boolean validarNegocioEnUser(String idNegocio,List<String> listNegocioFavorito) {
        for(String n:listNegocioFavorito){
            if(!n.equalsIgnoreCase("")){
                if (n.equalsIgnoreCase(idNegocio)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String removeFavorito(QuitarLugarFavDTO q) throws Exception {
        Optional<Usuario> usuarioOptional = usuarioRepo.findByIdentificacion(q.identificacionUserActual());
        //adicionamos el id de negocios favoritos al la lista del usuario
        if (usuarioOptional.isEmpty()) {
            throw new Exception("No se encontró el Usuario");
        }
        Optional<Negocio> negocioOptional = negocioRepo.findById(q.idLugarSeleccionado());
        if (negocioOptional.isEmpty()){
            throw new Exception("No Existe esa ID de negocio");
        }
        Usuario usuario = usuarioOptional.get();
        if (usuario.getListNegocioFavorito().isEmpty()) {
            throw new Exception("Lista de favoritos esta vacia.");
        }
        if(!validarNegocioEnUser(q.idLugarSeleccionado(),usuario.getListNegocioFavorito())){
            throw new Exception("No se encuentra el negocio de id"+q.idLugarSeleccionado());
        }
        usuario.getListNegocioFavorito().remove(q.idLugarSeleccionado());
        //guardamos
        usuarioRepo.save(usuario);

        return ""+q.idLugarSeleccionado();
    }

    @Override
    public void recuperarContrasena(RecuperacionPasswordDTO rP) throws Exception {

    }

    @Override
    public DetalleUsuarioDTO obtenerUsuario(String identificacion) throws IOException {
        Optional<Usuario> usuarioOptional = usuarioRepo.findByIdentificacion(identificacion);
        if (usuarioOptional.isEmpty()) {
            throw new IOException("No se encontro el usuario con la identificación:" + identificacion);
        }
        Usuario user = usuarioOptional.get();
        DetalleUsuarioDTO nuevoDetalleUsuario = DetalleUsuarioDTO.crearDetalleUsuario(
                user.getIdentificacion(),
                user.getNombre(),
                user.getFotoPerfil(),
                user.getUsername(),
                user.getEmail(),
                user.getListTelefonos()
        );

        return nuevoDetalleUsuario;
    }

    @Override
    public List<DetalleNegocioDTO> obtenerNegociosFav(String identificacionProp) throws Exception {
        Optional<Usuario> usuarioOptional = usuarioRepo.findByIdentificacion(identificacionProp);
        if (usuarioOptional.isEmpty()) {
            throw new IOException("No se encontro el usuario con la identificación:" + identificacionProp);
        }

       // Negocio negocio = negocioOptional.get();
        return null;
    }
    @Override
    public void cambiarContrasena(CambioPasswordDTO cP) throws Exception {

    }
}
