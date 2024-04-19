package co.edu.uniquindio.ProyectoFinal.DTO;

import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRegistro;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record RegistroUsuarioDTO(
        @NotBlank @Length(max = 100) String identificacion,
         @NotBlank @Length(max = 100) String nombre,
         String fotoPerfil,
        @NotBlank @Length(max = 40) String username,
        @NotBlank @Length(min = 5) String password,
         @NotBlank @Email String email,
         List<String> listTelefonos
) {
}
