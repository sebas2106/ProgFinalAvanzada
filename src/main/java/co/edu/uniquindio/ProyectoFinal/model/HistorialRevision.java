package co.edu.uniquindio.ProyectoFinal.model;

import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRevision;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalTime;

@Document("historialRevision")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HistorialRevision implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String codHistorial;
    private LocalTime fecha;
    private String codNegocio;
    private EstadoRevision estadoRevision;
    private String descripcion;
    private String codModerador;
}
