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
    private Cuenta cuentaUsuario;
    private EstadoRegistro estadoRegistro;
    private TipoUsuario tipoUsuario;
    private List<String> listNegocioFavorito;
    private List<String>listTelefonos;

    //esta variable solo guarda 10 palabras para que en base a estas
    // realice una consulta de los negocios que almenos contengan 1 de estas
    private List<String>ultimasBusquedas;

    @Builder
    public Usuario(String identificacion, String nombre, String fotoPerfil, String username, String password, String email, EstadoRegistro estadoRegistro, TipoUsuario tipoUsuario, List<String> listNegocioFavorito, List<String> listTelefonos,List<String>ultimasBusquedas) {
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
        this.ultimasBusquedas = ultimasBusquedas;
    }

}
