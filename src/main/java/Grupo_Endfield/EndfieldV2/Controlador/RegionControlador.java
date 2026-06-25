package Grupo_Endfield.EndfieldV2.Controlador;

import Grupo_Endfield.EndfieldV2.Modelo.Region;
import Grupo_Endfield.EndfieldV2.Servicio.RegionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/regiones")
public class RegionControlador {

    @Autowired
    private RegionServicio regionServicio;

    @GetMapping
    public ResponseEntity<List<Region>> obtenerTodos() {
        return ResponseEntity.ok(regionServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> obtenerPorId(@PathVariable Integer id) {
        return regionServicio.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Region> guardar(@RequestBody Region region) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(regionServicio.guardar(region));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        regionServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}