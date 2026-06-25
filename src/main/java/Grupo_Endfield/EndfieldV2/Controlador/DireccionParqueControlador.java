package Grupo_Endfield.EndfieldV2.Controlador;

import Grupo_Endfield.EndfieldV2.Modelo.DireccionParque;
import Grupo_Endfield.EndfieldV2.Servicio.DireccionParqueServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/direcciones-parque")
public class DireccionParqueControlador {

    @Autowired
    private DireccionParqueServicio direccionParqueServicio;

    @GetMapping
    public ResponseEntity<List<DireccionParque>> obtenerTodos() {
        return ResponseEntity.ok(direccionParqueServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionParque> obtenerPorId(@PathVariable Integer id) {
        return direccionParqueServicio.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DireccionParque> guardar(@RequestBody DireccionParque direccionParque) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(direccionParqueServicio.guardar(direccionParque));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        direccionParqueServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}