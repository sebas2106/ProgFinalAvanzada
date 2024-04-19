package co.edu.uniquindio.ProyectoFinal.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
@Document("departamentos")
public class Departamento {
    private String codDepartamento;
    private String nombre;
    //private List<Ciudad> listCiudades;
}
