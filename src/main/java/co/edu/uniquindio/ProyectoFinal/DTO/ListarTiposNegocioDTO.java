package co.edu.uniquindio.ProyectoFinal.DTO;

import co.edu.uniquindio.ProyectoFinal.model.enums.TipoNegocio;
import lombok.NonNull;

public record ListarTiposNegocioDTO(
        @NonNull TipoNegocio tipoBuscado

        ) {
}
