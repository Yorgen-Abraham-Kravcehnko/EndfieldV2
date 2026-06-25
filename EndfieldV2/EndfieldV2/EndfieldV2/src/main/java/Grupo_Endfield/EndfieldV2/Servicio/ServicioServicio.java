package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.Modelo.Servicio;
import Grupo_Endfield.EndfieldV2.Repositorio.ServicioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioServicio {

    @Autowired
    private ServicioRepositorio servicioRepositorio;

    public List<Servicio> obtenerTodos() {
        return servicioRepositorio.findAll();
    }

    public Optional<Servicio> obtenerPorId(Integer id) {
        return servicioRepositorio.findById(id);
    }

    public Servicio guardar(Servicio servicio) {
        return servicioRepositorio.save(servicio);
    }

    public void eliminar(Integer id) {
        servicioRepositorio.deleteById(id);
    }

    public Servicio actualizar(Integer id, Servicio servicio) {
    Servicio existente = servicioRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("Servicio no encontrado."));
    existente.setCantidadDisponible(servicio.getCantidadDisponible());
    existente.setMonto(servicio.getMonto());
    existente.setCapacidadMaxima(servicio.getCapacidadMaxima());
    existente.setTipoServicio(servicio.getTipoServicio());
    return servicioRepositorio.save(existente);
}
}