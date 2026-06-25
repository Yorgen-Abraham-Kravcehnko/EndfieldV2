package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.Modelo.TipoServicio;
import Grupo_Endfield.EndfieldV2.Repositorio.TipoServicioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TipoServicioServicio {

    @Autowired
    private TipoServicioRepositorio tipoServicioRepositorio;

    public List<TipoServicio> obtenerTodos() {
        return tipoServicioRepositorio.findAll();
    }

    public Optional<TipoServicio> obtenerPorId(Integer id) {
        return tipoServicioRepositorio.findById(id);
    }

    public TipoServicio guardar(TipoServicio tipoServicio) {
        return tipoServicioRepositorio.save(tipoServicio);
    }

    public void eliminar(Integer id) {
        tipoServicioRepositorio.deleteById(id);
    }
}