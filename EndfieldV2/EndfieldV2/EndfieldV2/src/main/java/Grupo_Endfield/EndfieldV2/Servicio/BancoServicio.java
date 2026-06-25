package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.Modelo.Banco;
import Grupo_Endfield.EndfieldV2.Repositorio.BancoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BancoServicio {

    @Autowired
    private BancoRepositorio bancoRepositorio;

    public List<Banco> obtenerTodos() {
        return bancoRepositorio.findAll();
    }

    public Optional<Banco> obtenerPorId(Integer id) {
        return bancoRepositorio.findById(id);
    }

    public Banco guardar(Banco banco) {
        return bancoRepositorio.save(banco);
    }

    public void eliminar(Integer id) {
        bancoRepositorio.deleteById(id);
    }

    public Banco actualizar(Integer id, Banco banco) {
    Banco existente = bancoRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("Banco no encontrado."));
    existente.setNombreBanco(banco.getNombreBanco());
    existente.setTipoCuenta(banco.getTipoCuenta());
    return bancoRepositorio.save(existente);
}
}