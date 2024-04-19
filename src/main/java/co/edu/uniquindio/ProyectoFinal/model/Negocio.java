package co.edu.uniquindio.ProyectoFinal.model;


import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoActual;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRegistro;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRevision;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoNegocio;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document("negocios")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Negocio implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String codNegocio;
    private String codCreador;
    private String nombre;
    private String descripcion;
    private Ubicacion ubicacion;
    private TipoNegocio tipoNegocio;
    private EstadoActual estado;
    private EstadoRevision estadoRevision;
    private EstadoRegistro estadoReg;
    private List<Horario> listHorario;
    private List<String>listImagenes;
    private List<Calificacion>listCalificaciones;
    private List<Comentario>listComentarios;
    private List<String>listTelefonos;
    @Builder

    public Negocio(String codNegocio, String codCreador, String nombre, String descripcion, Ubicacion ubicacion, TipoNegocio tipoNegocio, EstadoActual estado, EstadoRevision estadoRevision, EstadoRegistro estadoReg, List<Horario> listHorario, List<String> listImagenes, List<Calificacion> listCalificaciones, List<Comentario> listComentarios, List<String> listTelefonos) {
        this.codNegocio = codNegocio;
        this.codCreador = codCreador;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.tipoNegocio = tipoNegocio;
        this.estado = estado;
        this.estadoRevision = estadoRevision;
        this.estadoReg = estadoReg;
        this.listHorario = listHorario;
        this.listImagenes = listImagenes;
        this.listCalificaciones = listCalificaciones;
        this.listComentarios = listComentarios;
        this.listTelefonos = listTelefonos;
    }
}
