package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.Modelo.Parque;
import Grupo_Endfield.EndfieldV2.Repositorio.ParqueRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ParqueServicio {

    @Autowired
    private ParqueRepositorio parqueRepositorio;

    public List<Parque> obtenerTodos() {
        return parqueRepositorio.findAll();
    }

    public Optional<Parque> obtenerPorId(Integer id) {
        return parqueRepositorio.findById(id);
    }

    public Parque guardar(Parque parque) {
        return parqueRepositorio.save(parque);
    }

    public void eliminar(Integer id) {
        parqueRepositorio.deleteById(id);
    }

    public Parque actualizar(Integer id, Parque parque) {
    Parque existente = parqueRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("Parque no encontrado."));
    existente.setNombre(parque.getNombre());
    existente.setKm2(parque.getKm2());
    existente.setMaxVisitantes(parque.getMaxVisitantes());
    existente.setCapEstacionamiento(parque.getCapEstacionamiento());
    existente.setAbiertoTemporada(parque.getAbiertoTemporada());
    existente.setTemporadas(parque.getTemporadas());
    existente.setTipoParque(parque.getTipoParque());
    return parqueRepositorio.save(existente);
}
}   