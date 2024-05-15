package co.edu.uniquindio.ProyectoFinal.DTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ResponderComentarioDTO(
        @NotBlank String idNegocio,

        @NotBlank String idComentario,
        @NotBlank String idUsuarioRespuesta,
        @NotBlank @Length(max = 2000) String respuesta
) {
}
