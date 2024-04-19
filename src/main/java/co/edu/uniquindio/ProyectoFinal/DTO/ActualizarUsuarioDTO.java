package co.edu.uniquindio.ProyectoFinal.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record ActualizarUsuarioDTO(
        @NotBlank String identificacion,
                                   @NotBlank @Length(max = 100) String nombre,
                                   String fotoPerfil,
                                   @NotBlank @Length(max = 40) String username,
                                   @NotBlank @Email String email,
        List<String> listTelefonos

//falta ciudad
) {
}
