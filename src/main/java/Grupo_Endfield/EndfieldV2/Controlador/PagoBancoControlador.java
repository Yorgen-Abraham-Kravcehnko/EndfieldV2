package Grupo_Endfield.EndfieldV2.Controlador;

import Grupo_Endfield.EndfieldV2.DTO.PagoBancoDTO;
import Grupo_Endfield.EndfieldV2.DTO.PagoBancoResponseDTO;

import Grupo_Endfield.EndfieldV2.Servicio.PagoBancoServicio;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pagos-banco")
public class PagoBancoControlador {

    @Autowired
    private PagoBancoServicio pagoBancoServicio;

    @GetMapping
    public ResponseEntity<List<PagoBancoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(pagoBancoServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoBancoResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return pagoBancoServicio.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid PagoBancoDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(pagoBancoServicio.guardar(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, 
                                        @Valid @RequestBody PagoBancoDTO dto) {
        try {
            return ResponseEntity.ok(pagoBancoServicio.actualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            pagoBancoServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}