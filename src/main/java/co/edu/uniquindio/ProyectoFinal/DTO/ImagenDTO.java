package co.edu.uniquindio.ProyectoFinal.DTO;

import jakarta.validation.constraints.NotBlank;

public record ImagenDTO(

        @NotBlank String id,
        @NotBlank String url
) {
}