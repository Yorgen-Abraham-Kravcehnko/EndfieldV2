package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.Modelo.PerfilVisitante;
import Grupo_Endfield.EndfieldV2.Repositorio.PerfilVisitanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilVisitanteServicio {

    @Autowired
    private PerfilVisitanteRepositorio perfilVisitanteRepositorio;

    public List<PerfilVisitante> obtenerTodos() {
        return perfilVisitanteRepositorio.findAll();
    }

    public Optional<PerfilVisitante> obtenerPorId(Integer id) {
        return perfilVisitanteRepositorio.findById(id);
    }

    public PerfilVisitante guardar(PerfilVisitante perfilVisitante) {
        return perfilVisitanteRepositorio.save(perfilVisitante);
    }

    public void eliminar(Integer id) {
        perfilVisitanteRepositorio.deleteById(id);
    }
}