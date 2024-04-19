package co.edu.uniquindio.ProyectoFinal.DTO;

import jakarta.validation.constraints.NotBlank;

public record AddFavoriteDTO(
        @NotBlank String identificacionUserActual,
        @NotBlank String idLugarSeleccionado
) {
}
