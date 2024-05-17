package co.edu.uniquindio.ProyectoFinal.DTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CambioPasswordDTO(
        @NotBlank @Length(min = 5) String passwordNew,
        @NotBlank String token
) {
}
