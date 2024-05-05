package co.edu.uniquindio.ProyectoFinal.DTO;

import jakarta.validation.constraints.NotBlank;

public record ObtenerModeradorIdentiDTO(

        @NotBlank String identModerador
) {
}
