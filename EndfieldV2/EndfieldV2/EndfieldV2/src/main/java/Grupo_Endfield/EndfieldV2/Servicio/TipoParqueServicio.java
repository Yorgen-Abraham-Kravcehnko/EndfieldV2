package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.Modelo.TipoParque;
import Grupo_Endfield.EndfieldV2.Repositorio.TipoParqueRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TipoParqueServicio {

    @Autowired
    private TipoParqueRepositorio tipoParqueRepositorio;

    public List<TipoParque> obtenerTodos() {
        return tipoParqueRepositorio.findAll();
    }

    public Optional<TipoParque> obtenerPorId(Integer id) {
        return tipoParqueRepositorio.findById(id);
    }

    public TipoParque guardar(TipoParque tipoParque) {
        return tipoParqueRepositorio.save(tipoParque);
    }

    public void eliminar(Integer id) {
        tipoParqueRepositorio.deleteById(id);
    }

    public TipoParque actualizar(Integer id, TipoParque tipoParque) {
    TipoParque existente = tipoParqueRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("Tipo de parque no encontrado."));
    existente.setNombre(tipoParque.getNombre());
    existente.setDescripcion(tipoParque.getDescripcion());
    return tipoParqueRepositorio.save(existente);
}
}