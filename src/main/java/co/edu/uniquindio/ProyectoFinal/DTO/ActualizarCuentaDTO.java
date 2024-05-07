package co.edu.uniquindio.ProyectoFinal.DTO;

import jakarta.validation.constraints.NotBlank;

public record ActualizarCuentaDTO(

        @NotBlank String idUsuario,
        @NotBlank String nuevoNombre
) {
}
