package co.edu.uniquindio.ProyectoFinal.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
public class Horario {

    private String dia;
    private LocalTime horaInicial;
    private LocalTime horaFinal;

    public Horario() {
    }
}
