package Grupo_Endfield.EndfieldV2.Controlador;

import Grupo_Endfield.EndfieldV2.Modelo.Banco;
import Grupo_Endfield.EndfieldV2.Servicio.BancoServicio;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bancos")
public class BancoControlador {

    @Autowired
    private BancoServicio bancoServicio;

    @GetMapping
    public ResponseEntity<List<Banco>> obtenerTodos() {
        return ResponseEntity.ok(bancoServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Banco> obtenerPorId(@PathVariable Integer id) {
        return bancoServicio.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
public ResponseEntity<?> guardar(@RequestBody Banco banco) {
    try {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(bancoServicio.guardar(banco));
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

@PutMapping("/{id}")
public ResponseEntity<?> actualizar(@PathVariable Integer id,
                                     @Valid @RequestBody Banco banco) {
    try {
        return ResponseEntity.ok(bancoServicio.actualizar(id, banco));
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

@DeleteMapping("/{id}")
public ResponseEntity<?> eliminar(@PathVariable Integer id) {
    try {
        bancoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
}   