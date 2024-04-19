package co.edu.uniquindio.ProyectoFinal.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


public record ValidacionDTO(
        @NotBlank String campo,
        @NotBlank String error) {
}