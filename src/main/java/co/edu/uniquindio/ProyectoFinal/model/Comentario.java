package co.edu.uniquindio.ProyectoFinal.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Comentario {
    private String codComentario;
    private String codUsuario;
    private String comentario;
    private String respuesta;
}
