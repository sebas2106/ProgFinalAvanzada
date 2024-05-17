package co.edu.uniquindio.ProyectoFinal.controladores;

import co.edu.uniquindio.ProyectoFinal.DTO.*;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.IAutenticacionServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionControlador {
    private final IAutenticacionServicio autenticacionServicio;
    @PostMapping("/login-usuario")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesionUsuario(@Valid @RequestBody
                                                                     LoginDTO loginDTO) throws Exception {
        TokenDTO tokenDTO = autenticacionServicio.iniciarSesionUsuario(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }
    @PostMapping("/login-moderador")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesionModerador(@Valid @RequestBody
                                                                     LoginDTO loginDTO) throws Exception {
        TokenDTO tokenDTO = autenticacionServicio.iniciarSesionModerador(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }

    @PostMapping("/changePassword")
    public ResponseEntity<MensajeDTO> cambiarPassword(@Valid @RequestBody
                                                                    CambioPasswordDTO cambioPasswordDTO) throws Exception {
       autenticacionServicio.cambiarPassword(cambioPasswordDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se ha Realizado el cambio")
        );
    }

    @PostMapping("/enviarLinkRecuperacion")
    public ResponseEntity<MensajeDTO> enviarLinkRecuperacion(@Valid @RequestBody EnviarLinkRecuPassDTO enviarLinkRecuPassDTO) throws Exception {
        autenticacionServicio.enviarLinkRecuperacion(enviarLinkRecuPassDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se ha enviado el correo de recuperación")
        );
    }
}
