package co.edu.uniquindio.ProyectoFinal.Repositories;

import co.edu.uniquindio.ProyectoFinal.model.Comentario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComentarioRepo {
    Optional <Comentario>findByCodComentario(String id);
}
