package Grupo_Endfield.EndfieldV2.Controlador;

import Grupo_Endfield.EndfieldV2.DTO.ReservaServicioDTO;
import Grupo_Endfield.EndfieldV2.DTO.ReservaServicioResponseDTO;
import Grupo_Endfield.EndfieldV2.Modelo.ReservaServicioId;
import Grupo_Endfield.EndfieldV2.Servicio.ReservaServicioServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservas-servicio")
public class ReservaServicioControlador {

    @Autowired
    private ReservaServicioServicio reservaServicioServicio;

    @GetMapping
    public ResponseEntity<List<ReservaServicioResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(reservaServicioServicio.obtenerTodos());
    }

    @GetMapping("/{idReserva}/{idServicio}")
    public ResponseEntity<ReservaServicioResponseDTO> obtenerPorId(
            @PathVariable Integer idReserva, @PathVariable Integer idServicio) {
        ReservaServicioId id = new ReservaServicioId(idReserva, idServicio);
        return reservaServicioServicio.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody ReservaServicioDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservaServicioServicio.guardar(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{idReserva}/{idServicio}")
    public ResponseEntity<?> eliminar(
            @PathVariable Integer idReserva, @PathVariable Integer idServicio) {
        try {
            ReservaServicioId id = new ReservaServicioId(idReserva, idServicio);
            reservaServicioServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}