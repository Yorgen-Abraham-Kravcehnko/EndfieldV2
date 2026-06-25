package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.Modelo.DireccionParque;
import Grupo_Endfield.EndfieldV2.Repositorio.DireccionParqueRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DireccionParqueServicio {

    @Autowired
    private DireccionParqueRepositorio direccionParqueRepositorio;

    public List<DireccionParque> obtenerTodos() {
        return direccionParqueRepositorio.findAll();
    }

    public Optional<DireccionParque> obtenerPorId(Integer id) {
        return direccionParqueRepositorio.findById(id);
    }

    public DireccionParque guardar(DireccionParque direccionParque) {
        return direccionParqueRepositorio.save(direccionParque);
    }

    public void eliminar(Integer id) {
        direccionParqueRepositorio.deleteById(id);
    }

    public DireccionParque actualizar(Integer id, DireccionParque direccionParque) {
    DireccionParque existente = direccionParqueRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("Dirección de parque no encontrada."));
    existente.setCalle(direccionParque.getCalle());
    existente.setNumero(direccionParque.getNumero());
    existente.setReferencia(direccionParque.getReferencia());
    existente.setComuna(direccionParque.getComuna());
    return direccionParqueRepositorio.save(existente);
}
}