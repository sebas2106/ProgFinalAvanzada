package co.edu.uniquindio.ProyectoFinal.DTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ResponderComentarioDTO(
       @NotBlank                    String idComentario,
        @NotBlank @Length(max=2000) String respuesta
) {
}
