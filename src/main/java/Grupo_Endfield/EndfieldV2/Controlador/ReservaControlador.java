package Grupo_Endfield.EndfieldV2.Controlador;

import Grupo_Endfield.EndfieldV2.DTO.ReservaDTO;
import Grupo_Endfield.EndfieldV2.DTO.ReservaResponseDTO;

import Grupo_Endfield.EndfieldV2.Servicio.ReservaServicio;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaControlador {

    @Autowired
    private ReservaServicio reservaServicio;

    
    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(reservaServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return reservaServicio.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody ReservaDTO reserva) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservaServicio.guardar(reserva));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody ReservaDTO dto) {
        try {
            return ResponseEntity.ok(reservaServicio.actualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            reservaServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}