package Grupo_Endfield.EndfieldV2.Controlador;

import Grupo_Endfield.EndfieldV2.Modelo.DireccionVisitante;
import Grupo_Endfield.EndfieldV2.Servicio.DireccionVisitanteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/direcciones-visitante")
public class DireccionVisitanteControlador {

    @Autowired
    private DireccionVisitanteServicio direccionVisitanteServicio;

    @GetMapping
    public ResponseEntity<List<DireccionVisitante>> obtenerTodos() {
        return ResponseEntity.ok(direccionVisitanteServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionVisitante> obtenerPorId(@PathVariable Integer id) {
        return direccionVisitanteServicio.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DireccionVisitante> guardar(@RequestBody DireccionVisitante direccionVisitante) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(direccionVisitanteServicio.guardar(direccionVisitante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        direccionVisitanteServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}