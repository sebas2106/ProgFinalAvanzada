package co.edu.uniquindio.ProyectoFinal.services.interfaces;

import co.edu.uniquindio.ProyectoFinal.DTO.EmailDTO;

public interface IEmailServicio {
    void enviarCorreo(EmailDTO emailDTO) throws Exception;
}
