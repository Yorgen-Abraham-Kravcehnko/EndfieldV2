package Grupo_Endfield.EndfieldV2.Repositorio;

import Grupo_Endfield.EndfieldV2.Modelo.UsuarioRol;
import Grupo_Endfield.EndfieldV2.Modelo.UsuarioRolId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRolRepositorio extends JpaRepository<UsuarioRol, UsuarioRolId> {
}