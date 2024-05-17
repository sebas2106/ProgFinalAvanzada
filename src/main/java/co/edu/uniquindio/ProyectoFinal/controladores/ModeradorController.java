package co.edu.uniquindio.ProyectoFinal.controladores;

import co.edu.uniquindio.ProyectoFinal.DTO.*;
import co.edu.uniquindio.ProyectoFinal.model.HistorialRevision;
import co.edu.uniquindio.ProyectoFinal.model.Negocio;
import co.edu.uniquindio.ProyectoFinal.model.Usuario;
import co.edu.uniquindio.ProyectoFinal.model.enums.EstadoRevision;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.IModeradorServicio;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.INegocioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moderador")
@RequiredArgsConstructor
public class ModeradorController {
    private final IModeradorServicio moderadorServicio;
    @PostMapping("/DarEstado-Negocio")
    public ResponseEntity<MensajeDTO<String>>    ponerEstadoNegocio
            (@Valid @RequestBody DarEstadoNegocioDTO estado)throws Exception{
        moderadorServicio.ponerEstadoNegocio(estado);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se ha dado un estado al negocio")
        );
    }
    @GetMapping("/filtrarNegocioEstado/{estado}")
    public ResponseEntity<MensajeDTO<List<Negocio>>> obtenerNegocio(@PathVariable ObtenerNegocioEstadoRevDTO obtenerNegocioEstadoDTO) throws Exception{
        EstadoRevision estado=obtenerNegocioEstadoDTO.estadoRevision();
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                moderadorServicio.filtrarNegocioEstado(obtenerNegocioEstadoDTO) ) );
    }
    @GetMapping("/obtenerModerador/{identificacion}")
    public ResponseEntity<MensajeDTO<Usuario>> obtenerModerador(@PathVariable ObtenerModeradorIdentiDTO identModerad) throws Exception{
        String identificacion=identModerad.identModerador();
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                moderadorServicio.obtenerModerador(identModerad) ) );
    }

    @GetMapping("/obtenerHistorialRevisionesModerador")
    public ResponseEntity<MensajeDTO<List<HistorialRevision>>> obtenerHistorialRevisionesModerador(@Valid @RequestBody ObtenerHistorialRevisionModerador obtenerHistorial) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                moderadorServicio.obtenerHistorialRevisionesModerador(obtenerHistorial) ) );
    }
    @GetMapping("/obtenerRevisionesByEstado/{estadoRevision}")
    public ResponseEntity<MensajeDTO<List<HistorialRevision>>> obtenerRevisionesByEstado(@PathVariable EstadoRevision estadoRevision) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                moderadorServicio.obtenerRevisionesByEstado() ) );
    }
}
