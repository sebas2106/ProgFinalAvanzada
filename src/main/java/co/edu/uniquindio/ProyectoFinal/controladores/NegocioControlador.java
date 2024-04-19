package co.edu.uniquindio.ProyectoFinal.controladores;

import co.edu.uniquindio.ProyectoFinal.DTO.*;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.INegocioServicio;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.IUsuarioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/negocios")
@RequiredArgsConstructor
public class NegocioControlador {

    private final INegocioServicio negocioServicio;

    @PostMapping("/registrar-negocio")
    public ResponseEntity<MensajeDTO<String>> crearNegocio(@Valid @RequestBody CrearLugarDTO crearLugar)throws Exception{
        negocioServicio.crearNegocio(crearLugar);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Usuario registrado correctamente")
        );
    }
    @PostMapping("/editar-negocio")
    public ResponseEntity<MensajeDTO<String>> editarNegocio(@Valid @RequestBody ActualizarLugarDTO actualizarLugar)throws Exception{
        negocioServicio.actualizarNegocio(actualizarLugar);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Usuario registrado correctamente")
        );
    }
    @PostMapping("/borrar-negocio")
    public ResponseEntity<MensajeDTO<String>> eliminarNegocio(@Valid @RequestBody EliminarLugarDTO eliminarLugar)throws Exception{
        negocioServicio.eliminarNegocio(eliminarLugar);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Usuario registrado correctamente")
        );
    }
}
