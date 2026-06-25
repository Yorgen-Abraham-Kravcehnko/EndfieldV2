package Grupo_Endfield.EndfieldV2.Repositorio;

import Grupo_Endfield.EndfieldV2.Modelo.TipoServicioParque;
import Grupo_Endfield.EndfieldV2.Modelo.TipoServicioParqueId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoServicioParqueRepositorio extends JpaRepository<TipoServicioParque, TipoServicioParqueId> {
}