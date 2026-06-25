package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.Modelo.DireccionVisitante;
import Grupo_Endfield.EndfieldV2.Repositorio.DireccionVisitanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DireccionVisitanteServicio {

    @Autowired
    private DireccionVisitanteRepositorio direccionVisitanteRepositorio;

    public List<DireccionVisitante> obtenerTodos() {
        return direccionVisitanteRepositorio.findAll();
    }

    public Optional<DireccionVisitante> obtenerPorId(Integer id) {
        return direccionVisitanteRepositorio.findById(id);
    }

    public DireccionVisitante guardar(DireccionVisitante direccionVisitante) {
        return direccionVisitanteRepositorio.save(direccionVisitante);
    }

    public void eliminar(Integer id) {
        direccionVisitanteRepositorio.deleteById(id);
    }

    public DireccionVisitante actualizar(Integer id, DireccionVisitante direccionVisitante) {
    DireccionVisitante existente = direccionVisitanteRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("Dirección de visitante no encontrada."));
    existente.setCalle(direccionVisitante.getCalle());
    existente.setNumero(direccionVisitante.getNumero());
    existente.setReferencia(direccionVisitante.getReferencia());
    existente.setComuna(direccionVisitante.getComuna());
    return direccionVisitanteRepositorio.save(existente);
}
}