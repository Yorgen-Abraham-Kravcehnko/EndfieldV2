package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.Modelo.UsuarioRol;
import Grupo_Endfield.EndfieldV2.Modelo.UsuarioRolId;
import Grupo_Endfield.EndfieldV2.Repositorio.UsuarioRolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioRolServicio {

    @Autowired
    private UsuarioRolRepositorio usuarioRolRepositorio;

    public List<UsuarioRol> obtenerTodos() {
        return usuarioRolRepositorio.findAll();
    }

    public Optional<UsuarioRol> obtenerPorId(UsuarioRolId id) {
        return usuarioRolRepositorio.findById(id);
    }

    public UsuarioRol guardar(UsuarioRol usuarioRol) {
        return usuarioRolRepositorio.save(usuarioRol);
    }

    public void eliminar(UsuarioRolId id) {
        usuarioRolRepositorio.deleteById(id);
    }
}