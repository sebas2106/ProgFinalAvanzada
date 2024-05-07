package co.edu.uniquindio.ProyectoFinal.DTO;

import jakarta.validation.constraints.NotBlank;

public record CrearCuentaDTO(

        @NotBlank String idUsuario,
        @NotBlank String nombre

) {
}
