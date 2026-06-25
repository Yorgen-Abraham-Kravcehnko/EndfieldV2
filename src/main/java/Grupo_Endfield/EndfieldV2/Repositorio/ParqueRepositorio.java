package Grupo_Endfield.EndfieldV2.Repositorio;

import Grupo_Endfield.EndfieldV2.Modelo.Parque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParqueRepositorio extends JpaRepository<Parque, Integer> {
}