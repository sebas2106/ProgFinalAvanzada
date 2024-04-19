package co.edu.uniquindio.ProyectoFinal.model;

import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRegistro;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoUsuario;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document("usuarios")
@Getter
@Setter

@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String identificacion;
    private String nombre; //actualizable
    private String fotoPerfil;//actualizable
    private String username;
    private String password;
    private String email; //actualizable
    private EstadoRegistro estadoRegistro;
    private TipoUsuario tipoUsuario;
    private List<String> listNegocioFavorito;
    private List<String>listTelefonos;

    @Builder
    public Usuario(String identificacion, String nombre, String fotoPerfil, String username, String password, String email, EstadoRegistro estadoRegistro, TipoUsuario tipoUsuario, List<String> listNegocioFavorito, List<String> listTelefonos) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.fotoPerfil = fotoPerfil;
        this.username = username;
        this.password = password;
        this.email = email;
        this.estadoRegistro = estadoRegistro;
        this.tipoUsuario = tipoUsuario;
        this.listNegocioFavorito = listNegocioFavorito;
        this.listTelefonos = listTelefonos;
    }

}
