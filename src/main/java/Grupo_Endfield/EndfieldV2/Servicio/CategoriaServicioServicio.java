package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.Modelo.Banco;
import Grupo_Endfield.EndfieldV2.Modelo.CategoriaServicio;
import Grupo_Endfield.EndfieldV2.Repositorio.CategoriaServicioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServicioServicio {

    @Autowired
    private CategoriaServicioRepositorio categoriaServicioRepositorio;

    public List<CategoriaServicio> obtenerTodos() {
        return categoriaServicioRepositorio.findAll();
    }

    public Optional<CategoriaServicio> obtenerPorId(Integer id) {
        return categoriaServicioRepositorio.findById(id);
    }

    public CategoriaServicio guardar(CategoriaServicio categoriaServicio) {
        return categoriaServicioRepositorio.save(categoriaServicio);
    }

    public void eliminar(Integer id) {
        categoriaServicioRepositorio.deleteById(id);
    }

    public CategoriaServicio actualizar(Integer id, CategoriaServicio banco) {
    CategoriaServicio existente = categoriaServicioRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("Categoria de servicio no encontrado."));
    existente.setNombreBanco(banco.getNombreBanco());
    existente.setTipoCuenta(banco.getTipoCuenta());
    return categoriaServicioRepositorio.save(existente);
}
}