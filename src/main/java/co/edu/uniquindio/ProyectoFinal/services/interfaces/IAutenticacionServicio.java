package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.DTO.CambioPasswordDTO;
import co.edu.uniquindio.ProyectoFinal.DTO.EnviarLinkRecuPassDTO;
import co.edu.uniquindio.ProyectoFinal.DTO.LoginDTO;
import co.edu.uniquindio.ProyectoFinal.DTO.TokenDTO;

public interface IAutenticacionServicio {
    TokenDTO iniciarSesionUsuario(LoginDTO loginDTO) throws Exception;

    TokenDTO iniciarSesionModerador(LoginDTO loginDTO) throws Exception;

    TokenDTO cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception;

    TokenDTO enviarLinkRecuperacion(EnviarLinkRecuPassDTO enviarLinkRecuPassDTO) throws Exception;
}
