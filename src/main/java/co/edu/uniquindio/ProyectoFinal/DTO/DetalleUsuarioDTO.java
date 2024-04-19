package co.edu.uniquindio.ProyectoFinal.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

public record DetalleUsuarioDTO(
        @NotBlank @Length(max = 100) String identificacion,
        @NotBlank @Length(max = 100) String nombre,
        String fotoPerfil,
        @NotBlank @Length(max = 40) String username,
        @NotBlank @Email String email,
        List<String> listTelefonos
        
) {
    // Método estático de fábrica para crear instancias de DetalleUsuarioDTO con datos
    public static DetalleUsuarioDTO crearDetalleUsuario(
            String identificacion,
            String nombre,
            String fotoPerfil,
            String username,
            String email,
            List<String> listTelefonos) {
        return new DetalleUsuarioDTO(identificacion, nombre, fotoPerfil, username, email, listTelefonos);
    }
}
