package Grupo_Endfield.EndfieldV2.Controlador;

import Grupo_Endfield.EndfieldV2.Modelo.TipoParque;
import Grupo_Endfield.EndfieldV2.Servicio.TipoParqueServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tipos-parque")
public class TipoParqueControlador {

    @Autowired
    private TipoParqueServicio tipoParqueServicio;

    @GetMapping
    public ResponseEntity<List<TipoParque>> obtenerTodos() {
        return ResponseEntity.ok(tipoParqueServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoParque> obtenerPorId(@PathVariable Integer id) {
        return tipoParqueServicio.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoParque> guardar(@RequestBody TipoParque tipoParque) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(tipoParqueServicio.guardar(tipoParque));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tipoParqueServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}