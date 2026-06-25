package Grupo_Endfield.EndfieldV2.Controlador;

import Grupo_Endfield.EndfieldV2.DTO.VisitanteDTO;
import Grupo_Endfield.EndfieldV2.DTO.VisitanteResponseDTO;
import Grupo_Endfield.EndfieldV2.Servicio.VisitanteServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/visitantes")
public class VisitanteControlador {

    @Autowired
    private VisitanteServicio visitanteServicio;

    @GetMapping
    public ResponseEntity<List<VisitanteResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(visitanteServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitanteResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return visitanteServicio.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody VisitanteDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(visitanteServicio.guardar(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, 
                                        @Valid @RequestBody VisitanteDTO dto) {
        try {
            return ResponseEntity.ok(visitanteServicio.actualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            visitanteServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}