package co.edu.uniquindio.ProyectoFinal.DTO;

import jakarta.validation.constraints.NotBlank;

public record EliminarCuentaDTO(

        @NotBlank String idUsuario
) {
}
