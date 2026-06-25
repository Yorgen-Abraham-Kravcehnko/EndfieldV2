package Grupo_Endfield.EndfieldV2.Controlador;


import Grupo_Endfield.EndfieldV2.Modelo.Banco;
import Grupo_Endfield.EndfieldV2.Modelo.CategoriaServicio;
import Grupo_Endfield.EndfieldV2.Servicio.CategoriaServicioServicio;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias-servicio")
public class CategoriaServicioControlador {

    @Autowired
    private CategoriaServicioServicio categoriaServicioServicio;

    @GetMapping
    public ResponseEntity<List<CategoriaServicio>> obtenerTodos() {
        return ResponseEntity.ok(categoriaServicioServicio.obtenerTodos());
    }

    @PostMapping
public ResponseEntity<?> guardar(@RequestBody CategoriaServicio categoriaServicio) {
    try {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(categoriaServicioServicio.guardar(CategoriaServicio));
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

@PutMapping("/{id}")
public ResponseEntity<?> actualizar(@PathVariable Integer id,
                                     @Valid @RequestBody CategoriaServicio categoriaServicio) {
    try {
        return ResponseEntity.ok(categoriaServicioServicio.actualizar(id, categoriaServicio));
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

@DeleteMapping("/{id}")
public ResponseEntity<?> eliminar(@PathVariable Integer id) {
    try {
        categoriaServicioServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
}