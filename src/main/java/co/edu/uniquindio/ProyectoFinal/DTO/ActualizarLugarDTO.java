package co.edu.uniquindio.ProyectoFinal.DTO;

import co.edu.uniquindio.ProyectoFinal.model.Calificacion;
import co.edu.uniquindio.ProyectoFinal.model.Comentario;
import co.edu.uniquindio.ProyectoFinal.model.Horario;
import co.edu.uniquindio.ProyectoFinal.model.Ubicacion;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoNegocio;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record ActualizarLugarDTO(
        @NotBlank  String cod,
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(max = 2000) String descripcion,
         TipoNegocio tipoNegocio,
        Ubicacion ubicacion,

        List<String>listImages,
        List<Horario>listHorarios,

        List<String> listTelefonos

) {
}
