package co.edu.uniquindio.ProyectoFinal.controladores;

import co.edu.uniquindio.ProyectoFinal.DTO.*;
import co.edu.uniquindio.ProyectoFinal.model.Negocio;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRegistro;
import co.edu.uniquindio.ProyectoFinal.model.enums.TipoNegocio;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.INegocioServicio;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.IUsuarioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/negocios")
@RequiredArgsConstructor
public class NegocioControlador {

    private final INegocioServicio negocioServicio;

    @PostMapping("/registrar-negocio")
    public ResponseEntity<MensajeDTO<String>> crearNegocio(@Valid @RequestBody CrearLugarDTO crearLugar)throws Exception{
        negocioServicio.crearNegocio(crearLugar);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio registrado correctamente")
        );
    }
    @PostMapping("/editar-negocio")
    public ResponseEntity<MensajeDTO<String>> editarNegocio(@Valid @RequestBody ActualizarLugarDTO actualizarLugar)throws Exception{
        negocioServicio.actualizarNegocio(actualizarLugar);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio actualizado correctamente")
        );
    }
    @PostMapping("/borrar-negocio")
    public ResponseEntity<MensajeDTO<String>> eliminarNegocio(@Valid @RequestBody EliminarLugarDTO eliminarLugar)throws Exception{
        negocioServicio.eliminarNegocio(eliminarLugar);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio Eliminado correctamente")
        );
    }

    @GetMapping("/obtener-Negocio-Id/{id}")
    public ResponseEntity<MensajeDTO<Negocio>> obtenerNegocio(@PathVariable String id) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                negocioServicio.obtenerNegocio(id) ) );
    }
    @GetMapping("/obtener-Negocio-Nombre/{nombre}")
    public ResponseEntity<MensajeDTO<List<Negocio>>> buscarNegociosNomb(@PathVariable String nombre) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                negocioServicio.buscarNegociosNomb(nombre) ) );
    }

    @GetMapping("/obtener-Negocio-EstadoNegocio/{estadoReg}")
    public ResponseEntity<MensajeDTO<List<Negocio>>> filtrarPorEstado(@PathVariable EstadoRegistro estadoReg) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                negocioServicio.filtrarPorEstado(estadoReg) ) );
    }

    @GetMapping("/obtener-Negocio-TipoNegocio/{tipoNegocio}")
    public ResponseEntity<MensajeDTO<List<Negocio>>> filtrarPorTipoNegocio(@PathVariable TipoNegocio tipoNegocio) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                negocioServicio.filtrarPorTipoNegocio(tipoNegocio) ) );
    }
    @GetMapping("/obtener-Negocio-propietario/{identificacion}")
    public ResponseEntity<MensajeDTO<List<Negocio>>> listarNegociosPropietario(@PathVariable String identificacion) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                negocioServicio.listarNegociosPropietario(identificacion) ) );
    }
}
