package co.edu.uniquindio.ProyectoFinal.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
public class Horario {

    private DayOfWeek dia;
    private LocalTime horaInicial;
    private LocalTime horaFinal;

    public Horario() {
    }
}
