package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.Modelo.Region;
import Grupo_Endfield.EndfieldV2.Repositorio.RegionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RegionServicio {

    @Autowired
    private RegionRepositorio regionRepositorio;

    public List<Region> obtenerTodos() {
        return regionRepositorio.findAll();
    }

    public Optional<Region> obtenerPorId(Integer id) {
        return regionRepositorio.findById(id);
    }

    public Region guardar(Region region) {
        return regionRepositorio.save(region);
    }

    public void eliminar(Integer id) {
        regionRepositorio.deleteById(id);
    }
    public Region actualizar(Integer id, Region region) {
    Region existente = regionRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("Región no encontrada."));
    existente.setNumeroRegion(region.getNumeroRegion());
    existente.setNombreRegion(region.getNombreRegion());
    return regionRepositorio.save(existente);
}
}