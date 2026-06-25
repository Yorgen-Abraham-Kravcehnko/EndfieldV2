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
}   