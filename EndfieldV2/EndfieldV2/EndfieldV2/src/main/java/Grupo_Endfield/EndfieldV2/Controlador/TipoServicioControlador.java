package Grupo_Endfield.EndfieldV2.Controlador;

import Grupo_Endfield.EndfieldV2.Modelo.TipoServicio;
import Grupo_Endfield.EndfieldV2.Servicio.TipoServicioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tipos-servicio")
public class TipoServicioControlador {

    @Autowired
    private TipoServicioServicio tipoServicioServicio;

    @GetMapping
    public ResponseEntity<List<TipoServicio>> obtenerTodos() {
        return ResponseEntity.ok(tipoServicioServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoServicio> obtenerPorId(@PathVariable Integer id) {
        return tipoServicioServicio.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoServicio> guardar(@RequestBody TipoServicio tipoServicio) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(tipoServicioServicio.guardar(tipoServicio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tipoServicioServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}