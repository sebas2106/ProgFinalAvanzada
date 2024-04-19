package co.edu.uniquindio.ProyectoFinal.Repositories;

import co.edu.uniquindio.ProyectoFinal.model.Usuario;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoUsuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepos extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByIdentificacion(String identificacion);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByFotoPerfil(String foto);
    Optional<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);
    Optional<Usuario> findByUsername(String username);

    void deleteByIdentificacion(String number);
}
