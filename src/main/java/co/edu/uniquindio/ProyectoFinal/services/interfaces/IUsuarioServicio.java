package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.DTO.*;
import co.edu.uniquindio.ProyectoFinal.model.Cuenta;
import co.edu.uniquindio.ProyectoFinal.model.Negocio;

import java.io.IOException;
import java.util.List;

public interface IUsuarioServicio {

    String registrarse(RegistroUsuarioDTO registroUsuarioDTO) throws Exception;

    String registrarseModerador(RegistroUsuarioDTO registroUsuarioDTO) throws Exception;

    String actualizarUsuario(ActualizarUsuarioDTO actuaizarUsuarioDTO) throws Exception;

    void borrarUsuario(String id) throws Exception;

    void iniciarSesion(InicioSesionDTO inicioSesionDTO) throws Exception;

    void enviarLinkRecuperacion(String email) throws Exception;

    String guardarFavorito(AddFavoriteDTO addFav) throws Exception;
    String removeFavorito(QuitarLugarFavDTO q) throws Exception;
    List<Negocio> listarFavoritos(String identificacion)throws Exception;
    void recuperarContrasena(RecuperacionPasswordDTO rP) throws Exception;
    void cambiarContrasena(CambioPasswordDTO cP) throws Exception;
    Cuenta crerCuenta(CrearCuentaDTO crearCuentaDTO) throws Exception;
    Cuenta actualizarCuenta(ActualizarCuentaDTO actualizarCuenta) throws Exception;
    void eliminarCuenta(EliminarCuentaDTO eliminarCuenta) throws Exception;
    DetalleUsuarioDTO obtenerUsuario(String identificacion) throws Exception;
    String crearNegocio(CrearLugarDTO cL)throws Exception;
    String actualizarNegocio(ActualizarLugarDTO aL)throws Exception;
    String eliminarNegocio(EliminarLugarDTO eL)throws Exception;
    Negocio obtenerNegocio(String id)throws Exception;
    List<Negocio> listarNegociosPropietario(String identificacion)throws Exception;

    boolean comentarNegocio(ComentarLugarDTO comentarLugarDTO)throws Exception;

    boolean responderComentarioNegocio(ResponderComentarioDTO responderComentarioLugarDTO)throws Exception;


}
