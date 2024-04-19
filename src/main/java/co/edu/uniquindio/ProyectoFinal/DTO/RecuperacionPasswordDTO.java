package co.edu.uniquindio.ProyectoFinal.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RecuperacionPasswordDTO(
        @NotBlank @Email String correo
) {
}
