package co.edu.uniquindio.ProyectoFinal.DTO;

import co.edu.uniquindio.ProyectoFinal.model.enums.TipoNegocio;

public record BuscarLugarNomTipDistDTO(
        String nombre,
        TipoNegocio tipoNegocio,
        int distancia
) {
}
