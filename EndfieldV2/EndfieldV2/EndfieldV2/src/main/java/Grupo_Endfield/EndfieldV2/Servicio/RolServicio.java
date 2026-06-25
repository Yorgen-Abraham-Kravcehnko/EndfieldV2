package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.Modelo.Rol;
import Grupo_Endfield.EndfieldV2.Repositorio.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RolServicio {

    @Autowired
    private RolRepositorio rolRepositorio;

    public List<Rol> obtenerTodos() {
        return rolRepositorio.findAll();
    }

    public Optional<Rol> obtenerPorId(Integer id) {
        return rolRepositorio.findById(id);
    }

    public Rol guardar(Rol rol) {
        return rolRepositorio.save(rol);
    }

    public void eliminar(Integer id) {
        rolRepositorio.deleteById(id);
    }
}
