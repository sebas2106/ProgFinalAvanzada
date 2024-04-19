package co.edu.uniquindio.ProyectoFinal.DTO;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}
