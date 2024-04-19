package co.edu.uniquindio.ProyectoFinal.DTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CalificarLugarDTO(
        @NotBlank                  String idLugar,
        @NotBlank                  String codUsuario,
        @NotBlank @Length(max = 5) int numEstrellas) {
}
