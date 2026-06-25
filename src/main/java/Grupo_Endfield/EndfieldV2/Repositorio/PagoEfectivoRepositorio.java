package Grupo_Endfield.EndfieldV2.Repositorio;

import Grupo_Endfield.EndfieldV2.Modelo.PagoEfectivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoEfectivoRepositorio extends JpaRepository<PagoEfectivo, Integer> {
}