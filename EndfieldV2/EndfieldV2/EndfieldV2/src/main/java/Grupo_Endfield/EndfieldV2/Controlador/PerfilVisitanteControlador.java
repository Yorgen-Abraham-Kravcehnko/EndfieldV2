package Grupo_Endfield.EndfieldV2.Controlador;

import Grupo_Endfield.EndfieldV2.Modelo.PerfilVisitante;
import Grupo_Endfield.EndfieldV2.Servicio.PerfilVisitanteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/perfiles-visitante")
public class PerfilVisitanteControlador {

    @Autowired
    private PerfilVisitanteServicio perfilVisitanteServicio;

    @GetMapping
    public ResponseEntity<List<PerfilVisitante>> obtenerTodos() {
        return ResponseEntity.ok(perfilVisitanteServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilVisitante> obtenerPorId(@PathVariable Integer id) {
        return perfilVisitanteServicio.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PerfilVisitante> guardar(@RequestBody PerfilVisitante perfilVisitante) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(perfilVisitanteServicio.guardar(perfilVisitante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        perfilVisitanteServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}