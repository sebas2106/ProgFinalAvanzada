package co.edu.uniquindio.ProyectoFinal.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Direccion {
    private String Barrio;
    private String Direccion;
    private String codigoPostal;

}
