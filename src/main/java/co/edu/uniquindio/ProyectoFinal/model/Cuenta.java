package co.edu.uniquindio.ProyectoFinal.model;

import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoCuenta;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class Cuenta {
    private String nombreCuenta;
    private EstadoCuenta estadoCuenta;
    private List<String> listNegociosAsociados;

}
