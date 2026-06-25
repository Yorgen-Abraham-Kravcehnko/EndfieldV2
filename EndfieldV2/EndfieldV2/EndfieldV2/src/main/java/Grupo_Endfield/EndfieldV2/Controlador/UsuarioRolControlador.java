package Grupo_Endfield.EndfieldV2.Controlador;

import Grupo_Endfield.EndfieldV2.Modelo.UsuarioRol;
import Grupo_Endfield.EndfieldV2.Modelo.UsuarioRolId;
import Grupo_Endfield.EndfieldV2.Servicio.UsuarioRolServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios-rol")
public class UsuarioRolControlador {

    @Autowired
    private UsuarioRolServicio usuarioRolServicio;

    @GetMapping
    public ResponseEntity<List<UsuarioRol>> obtenerTodos() {
        return ResponseEntity.ok(usuarioRolServicio.obtenerTodos());
    }

    @GetMapping("/{idUsuario}/{idRol}")
    public ResponseEntity<UsuarioRol> obtenerPorId(
            @PathVariable Integer idUsuario, @PathVariable Integer idRol) {
        UsuarioRolId id = new UsuarioRolId(idUsuario, idRol);
        return usuarioRolServicio.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody UsuarioRol usuarioRol) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioRolServicio.guardar(usuarioRol));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{idUsuario}/{idRol}")
    public ResponseEntity<?> eliminar(
            @PathVariable Integer idUsuario, @PathVariable Integer idRol) {
        try {
            UsuarioRolId id = new UsuarioRolId(idUsuario, idRol);
            usuarioRolServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}