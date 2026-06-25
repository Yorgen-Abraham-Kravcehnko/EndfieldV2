package Grupo_Endfield.EndfieldV2.Controlador;

import Grupo_Endfield.EndfieldV2.DTO.PagoEfectivoDTO;
import Grupo_Endfield.EndfieldV2.DTO.PagoEfectivoResponseDTO;

import Grupo_Endfield.EndfieldV2.Servicio.PagoEfectivoServicio;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pagos-efectivo")
public class PagoEfectivoControlador {

    @Autowired
    private PagoEfectivoServicio pagoEfectivoServicio;

    @GetMapping
    public ResponseEntity<List<PagoEfectivoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(pagoEfectivoServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoEfectivoResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return pagoEfectivoServicio.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid PagoEfectivoDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(pagoEfectivoServicio.guardar(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, 
                                        @Valid @RequestBody PagoEfectivoDTO dto) {
        try {
            return ResponseEntity.ok(pagoEfectivoServicio.actualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            pagoEfectivoServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}