package co.edu.uniquindio.ProyectoFinal.controladores;

import co.edu.uniquindio.ProyectoFinal.DTO.ActualizarLugarDTO;
import co.edu.uniquindio.ProyectoFinal.DTO.DarEstadoNegocioDTO;
import co.edu.uniquindio.ProyectoFinal.DTO.MensajeDTO;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.IModeradorServicio;
import co.edu.uniquindio.ProyectoFinal.services.interfaces.INegocioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
