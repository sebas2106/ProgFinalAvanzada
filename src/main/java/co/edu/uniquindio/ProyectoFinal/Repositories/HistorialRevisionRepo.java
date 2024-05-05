package co.edu.uniquindio.ProyectoFinal.Repositories;

import co.edu.uniquindio.ProyectoFinal.model.HistorialRevision;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistorialRevisionRepo extends MongoRepository<HistorialRevision, String> {

Optional<List<HistorialRevision>>findHistorialRevisionByCodModerador(String idMooderador);
}
