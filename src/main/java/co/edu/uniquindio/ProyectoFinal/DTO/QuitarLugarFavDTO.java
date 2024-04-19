package co.edu.uniquindio.ProyectoFinal.DTO;

import jakarta.validation.constraints.NotBlank;

public record QuitarLugarFavDTO(@NotBlank String identificacionUserActual,
                                @NotBlank String idLugarSeleccionado) {
}
