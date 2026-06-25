package Grupo_Endfield.EndfieldV2.Controlador;

import Grupo_Endfield.EndfieldV2.DTO.VehiculoDTO;
import Grupo_Endfield.EndfieldV2.DTO.VehiculoResponseDTO;
import Grupo_Endfield.EndfieldV2.Servicio.VehiculoServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoControlador {

    @Autowired
    private VehiculoServicio vehiculoServicio;

    @GetMapping
    public ResponseEntity<List<VehiculoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(vehiculoServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return vehiculoServicio.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody VehiculoDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(vehiculoServicio.guardar(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody VehiculoDTO dto) {
        try {
            return ResponseEntity.ok(vehiculoServicio.actualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            vehiculoServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}