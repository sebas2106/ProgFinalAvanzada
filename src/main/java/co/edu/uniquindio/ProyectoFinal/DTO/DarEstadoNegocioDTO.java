package co.edu.uniquindio.ProyectoFinal.DTO;

import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRegistro;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRevision;

public record DarEstadoNegocioDTO(

        String idNegocio,
        EstadoRevision estado
) {
}
