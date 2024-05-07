package co.edu.uniquindio.ProyectoFinal.DTO;

import co.edu.uniquindio.ProyectoFinal.model.Calificacion;
import co.edu.uniquindio.ProyectoFinal.model.Comentario;
import co.edu.uniquindio.ProyectoFinal.model.Horario;
import co.edu.uniquindio.ProyectoFinal.model.Ubicacion;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoNegocio;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CrearLugarDTO(
        @NotBlank @Length(max = 100) String codCreador,
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank String descripcion,
         TipoNegocio tipoNegocio,
        Ubicacion ubicacion,
        List<Horario>listHorarios,

        List<String>listImages,

        List<String> listTelefonos

) {
}
