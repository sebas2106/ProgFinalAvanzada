package co.edu.uniquindio.ProyectoFinal.test;


import co.edu.uniquindio.ProyectoFinal.Repositories.UsuarioRepos;
import co.edu.uniquindio.ProyectoFinal.model.Usuario;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRegistro;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoUsuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class UsuarioTest {

    @Autowired
    private UsuarioRepos usuarioRepos;


    @Test
    public void registrarUsuarioTest() {
        //Creamos el cliente con sus propiedades
        Usuario usuario = Usuario.builder()
                .identificacion("1005094005")
                .nombre("Sebas Ramos")
                .username("qpasa0621")
                .password("Oculta54")
                .estadoRegistro(EstadoRegistro.ACTIVO)
                .tipoUsuario(TipoUsuario.MODERADOR)
                .fotoPerfil("")
                .email("pepito@email.com")
                .listNegocioFavorito(null)
                .listTelefonos(List.of("12121", "232323")).build();
        //Guardamos el cliente
        Usuario registro = usuarioRepos.save(usuario);
        //Verificamos que se haya guardado validando que no sea null
        Assertions.assertNotNull(registro);
    }
    @Test
    public void actualizarUsuarioTest(){
    //Obtenemos el cliente con el id XXXXXXX
        Usuario usuario = usuarioRepos.findByIdentificacion("1005094005").orElseThrow();
    //Modificar el email del cliente
        usuario.setEmail("nuevoemail@email.com");
    //Guardamos el cliente
        usuarioRepos.save( usuario );
    //Obtenemos el cliente con el id XXXXXXX nuevamente
        Usuario usuarioActualizado = usuarioRepos.findByIdentificacion("1005094005").orElseThrow();;
    //Verificamos que el email se haya actualizado
        Assertions.assertEquals("nuevoemail@email.com", usuarioActualizado.getEmail());
    }

    @Test
    public void listarClienteTest(){
        //Obtenemos la lista de todos los clientes (por ahora solo tenemos 1)
        List<Usuario> usuarios = usuarioRepos.findAll();
        //Imprimimos los clientes, se hace uso de una función lambda
        usuarios.forEach(System.out::println);
        //Verificamos que solo exista un cliente
        Assertions.assertEquals(1, usuarios.size());
    }
    @Test
    public void eliminarUsuarioTest(){
        //Borramos el cliente con el id XXXXXXX
        usuarioRepos.deleteByIdentificacion("1005094005");
        //Obtenemos el cliente con el id XXXXXXX
        Usuario usuario = usuarioRepos.findByIdentificacion("1005094005").orElse(null);
        //Verificamos que el cliente no exista
        Assertions.assertNull(usuario);
    }
    @Test
    public void addNegocioFavTest(){
        //Obtenemos el cliente con el id XXXXXXX
        Usuario usuario = usuarioRepos.findByIdentificacion("1005094005").orElseThrow();
        //adicionamos el id de negocios favoritos al la lista del usuario
        List<String>lista=new ArrayList<>();
        usuario.setListNegocioFavorito(lista);
        usuario.getListNegocioFavorito().add("662136eb85ef7534d1b00943");
        //guardamos
        usuarioRepos.save( usuario );
        //Obtenemos el cliente con el id XXXXXXX
        Usuario usuarioConFav = usuarioRepos.findByIdentificacion("1005094005").orElse(null);
       boolean encontrado=false;
       encontrado=validarFav(usuarioConFav,"662136eb85ef7534d1b00943");
        //Verificamos que el cliente no exista
        Assertions.assertEquals(true, encontrado);
    }

    private boolean validarFav(Usuario usuarioConFav, String s) {

        for(String u: usuarioConFav.getListNegocioFavorito()){
            if (!u.equalsIgnoreCase("")){
                if (u.equalsIgnoreCase(s)){
                    return true;
                }
            }
        }
        return false;
    }
    @Test
    public void removeNegocioFavTest(){
        //Obtenemos el cliente con el id XXXXXXX
        Usuario usuario = usuarioRepos.findByIdentificacion("1005094005").orElseThrow();
        //adicionamos el id de negocios favoritos al la lista del usuario
        List<String>lista=new ArrayList<>();
        usuario.setListNegocioFavorito(lista);
        usuario.getListNegocioFavorito().remove("662136eb85ef7534d1b00943");
        //guardamos
        usuarioRepos.save( usuario );
        //Obtenemos el cliente con el id XXXXXXX
        Usuario usuarioConFav = usuarioRepos.findByIdentificacion("1005094005").orElse(null);
        boolean encontrado=false;
        encontrado=validarFav(usuarioConFav,"662136eb85ef7534d1b00943");
        //Verificamos que el negocio no exista
        Assertions.assertEquals(false, encontrado);
    }

}
