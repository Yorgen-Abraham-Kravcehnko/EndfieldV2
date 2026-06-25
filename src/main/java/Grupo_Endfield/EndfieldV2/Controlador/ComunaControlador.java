package Grupo_Endfield.EndfieldV2.Controlador;

import Grupo_Endfield.EndfieldV2.Modelo.Comuna;
import Grupo_Endfield.EndfieldV2.Servicio.ComunaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comunas")
public class ComunaControlador {

    @Autowired
    private ComunaServicio comunaServicio;

    @GetMapping
    public ResponseEntity<List<Comuna>> obtenerTodos() {
        return ResponseEntity.ok(comunaServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comuna> obtenerPorId(@PathVariable Integer id) {
        return comunaServicio.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Comuna> guardar(@RequestBody Comuna comuna) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(comunaServicio.guardar(comuna));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        comunaServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}