package co.edu.uniquindio.ProyectoFinal.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@ToString
public class Comentario {
    @Id
    private String codComentario;
    private String codUsuario;
    private String comentario;
    private String respuesta;
}
