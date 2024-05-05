package co.edu.uniquindio.ProyectoFinal.DTO;

import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRevision;
import jakarta.validation.constraints.NotNull;

public record ObtenerNegocioEstadoRevDTO(
        @NotNull EstadoRevision esadoRevision
) {
}
