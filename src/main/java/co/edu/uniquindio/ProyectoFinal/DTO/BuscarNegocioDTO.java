package co.edu.uniquindio.ProyectoFinal.DTO;

import co.edu.uniquindio.ProyectoFinal.model.enums.TipoNegocio;
import jakarta.validation.constraints.NotBlank;

public record BuscarNegocioDTO(

        @NotBlank String palabraClave
        ) {
}
