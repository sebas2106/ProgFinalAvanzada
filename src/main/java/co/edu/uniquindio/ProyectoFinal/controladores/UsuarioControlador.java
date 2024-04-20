package co.edu.uniquindio.ProyectoFinal.controladores;

import co.edu.uniquindio.ProyectoFinal.DTO.*;
import co.edu.uniquindio.ProyectoFinal.model.Negocio;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.IUsuarioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioControlador {
    private final IUsuarioServicio usuarioServicio;


    @PostMapping("/registrar-cliente")
    public ResponseEntity<MensajeDTO<String>> registrarUsuario(@Valid @RequestBody RegistroUsuarioDTO registroUsuarioDTO)throws Exception{
        usuarioServicio.registrarse(registroUsuarioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Usuario registrado correctamente")
        );
    }
    @PostMapping("/registrar-moderador")
    public ResponseEntity<MensajeDTO<String>> registrarseModerador(@Valid @RequestBody RegistroUsuarioDTO registroUsuarioDTO)throws Exception{
        usuarioServicio.registrarseModerador(registroUsuarioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Usuario registrado correctamente")
        );
    }
    @PutMapping("/actualizar-usuario")
    public ResponseEntity<MensajeDTO<String>> actualizarUsuario(@Valid @RequestBody ActualizarUsuarioDTO actualizarUsuarioDTO)throws Exception{
        usuarioServicio.actualizarUsuario(actualizarUsuarioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Usuario actualizado correctamente") );
    }
    @DeleteMapping("/borrar-usuario/{identificacion}")
    public ResponseEntity<MensajeDTO<String>> borrarUsuario(@PathVariable String identificacion)throws
            Exception{
        usuarioServicio.borrarUsuario(identificacion);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Usuario eliminado correctamente")
        );
    }
    @GetMapping("/obtener-usuario/{identificacion}")
    public ResponseEntity<MensajeDTO<DetalleUsuarioDTO>> obtenerCliente(@PathVariable String identificacion) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                usuarioServicio.obtenerUsuario(identificacion) ) );
    }

    @PutMapping("/addNegocioFav-usuario")
    public ResponseEntity<MensajeDTO<String>> guardarFavorito(@Valid @RequestBody AddFavoriteDTO favorito)throws Exception{
        usuarioServicio.guardarFavorito(favorito);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se agregó exitosamente") );
    }
    @DeleteMapping("/removeNegocioFav-usuario")
    public ResponseEntity<MensajeDTO<String>> removeFavorito(@Valid @RequestBody QuitarLugarFavDTO datos)throws
            Exception{
        usuarioServicio.removeFavorito(datos);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se removió exitosamente")
        );
    }

    @GetMapping("/obtener-NegociosFav/{identificacionProp}")
    public ResponseEntity<MensajeDTO<List<DetalleNegocioDTO>>> obtenerNegociosFav(@PathVariable String identificacionProp) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                usuarioServicio.obtenerNegociosFav(identificacionProp) ) );
    }
}
