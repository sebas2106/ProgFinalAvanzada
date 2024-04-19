package co.edu.uniquindio.ProyectoFinal.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@ToString
@Document("ciudades")
public class Ciudad {
    private String codDepartamento;
    private String codCiudad;
    private String nombre;
}
