package co.edu.uniquindio.ProyectoFinal.Repositories;
import co.edu.uniquindio.ProyectoFinal.model.Negocio;
import co.edu.uniquindio.ProyectoFinal.model.Usuario;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoNegocio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NegocioRepo extends MongoRepository<Negocio, String> {

    Optional<List<Negocio>> findByTipoNegocio(TipoNegocio tipo);

    Optional<List<Negocio>> findByNombre(String nombre);
}