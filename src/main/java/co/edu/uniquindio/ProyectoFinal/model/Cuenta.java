package co.edu.uniquindio.ProyectoFinal.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class Cuenta {
    private String nombreCuenta;
    private List<String> listNegociosAsociados;

}
