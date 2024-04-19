package co.edu.uniquindio.ProyectoFinal.DTO;

import jakarta.validation.constraints.NotBlank;

public record ObtenerNegocioDTO(
        @NotBlank String idNegocio) {
}
