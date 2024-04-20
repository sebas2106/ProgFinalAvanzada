package co.edu.uniquindio.ProyectoFinal.DTO;

import co.edu.uniquindio.ProyectoFinal.model.Ubicacion;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoNegocio;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record DetalleNegocioDTO(
        @NotBlank  String codNegocio,
        @NotBlank @Length(max = 100) String codCreador,
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank String descripcion,
        @NotBlank TipoNegocio tipoNegocio,
        Ubicacion ubicacion
) {
    public DetalleNegocioDTO(String codNegocio, String codCreador, String nombre, String descripcion, TipoNegocio tipoNegocio, Ubicacion ubicacion) {
        this.codNegocio = codNegocio;
        this.codCreador = codCreador;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoNegocio = tipoNegocio;
        this.ubicacion = ubicacion;
    }
}
