package Grupo_Endfield.EndfieldV2.Controlador;

import Grupo_Endfield.EndfieldV2.Modelo.Parque;
import Grupo_Endfield.EndfieldV2.Servicio.ParqueServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/parques")
public class ParqueControlador {

    @Autowired
    private ParqueServicio parqueServicio;

    @GetMapping
    public ResponseEntity<List<Parque>> obtenerTodos() {
        return ResponseEntity.ok(parqueServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parque> obtenerPorId(@PathVariable Integer id) {
        return parqueServicio.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Parque> guardar(@RequestBody Parque parque) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(parqueServicio.guardar(parque));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        parqueServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}