package co.edu.uniquindio.ProyectoFinal.DTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ComentarLugarDTO(
        @NotBlank String codUsuario,
        @NotBlank String codNegocio,
        @NotBlank @Length(max=2000) String comentario) {
}
