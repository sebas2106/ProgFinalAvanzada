package co.edu.uniquindio.ProyectoFinal.DTO;

import jakarta.validation.constraints.NotBlank;

public record ObtenerHistorialRevisionModerador(
        @NotBlank String idModerador
) {
}
