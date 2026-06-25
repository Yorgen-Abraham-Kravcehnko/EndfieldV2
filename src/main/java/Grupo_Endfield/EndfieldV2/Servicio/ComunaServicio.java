package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.Modelo.Comuna;
import Grupo_Endfield.EndfieldV2.Repositorio.ComunaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ComunaServicio {

    @Autowired
    private ComunaRepositorio comunaRepositorio;

    public List<Comuna> obtenerTodos() {
        return comunaRepositorio.findAll();
    }

    public Optional<Comuna> obtenerPorId(Integer id) {
        return comunaRepositorio.findById(id);
    }

    public Comuna guardar(Comuna comuna) {
        return comunaRepositorio.save(comuna);
    }

    public void eliminar(Integer id) {
        comunaRepositorio.deleteById(id);
    }
}