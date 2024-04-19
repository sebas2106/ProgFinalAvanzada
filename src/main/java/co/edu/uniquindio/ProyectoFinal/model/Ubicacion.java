package co.edu.uniquindio.ProyectoFinal.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Ubicacion {
    private String codDepartamento;
    private String codCiudad;
    private Direccion direcion;
    private String longitud;
    private String latitud;
}
