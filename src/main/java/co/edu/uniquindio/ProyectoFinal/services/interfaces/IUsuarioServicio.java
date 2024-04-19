package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.DTO.*;

import java.io.IOException;

public interface IUsuarioServicio {

    String registrarse(RegistroUsuarioDTO registroUsuarioDTO)throws Exception;

    String registrarseModerador(RegistroUsuarioDTO registroUsuarioDTO) throws Exception;

    String actualizarUsuario(ActualizarUsuarioDTO actuaizarUsuarioDTO)throws Exception;
    void borrarUsuario(String id)throws Exception;
    void iniciarSesion(InicioSesionDTO inicioSesionDTO)throws Exception;
    void enviarLinkRecuperacion(String email)throws Exception;
    String guardarFavorito(AddFavoriteDTO addFav)throws Exception;
    String removeFavorito(QuitarLugarFavDTO q)throws Exception;
    void recuperarContrasena(RecuperacionPasswordDTO rP)throws Exception;
    void cambiarContrasena(CambioPasswordDTO cP)throws Exception;

    DetalleUsuarioDTO obtenerUsuario(String identificacion) throws IOException;
}
