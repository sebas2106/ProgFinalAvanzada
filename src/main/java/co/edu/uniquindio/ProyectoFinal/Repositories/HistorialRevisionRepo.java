package co.edu.uniquindio.ProyectoFinal.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialRevisionRepo extends MongoRepository<HistorialRevisionRepo, String> {
}
