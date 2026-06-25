package Grupo_Endfield.EndfieldV2.Repositorio;

import Grupo_Endfield.EndfieldV2.Modelo.ReservaServicio;
import Grupo_Endfield.EndfieldV2.Modelo.ReservaServicioId;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




@Repository
public interface ReservaServicioRepositorio extends JpaRepository<ReservaServicio, ReservaServicioId> {

    @Query("SELECT rs FROM ReservaServicio rs WHERE rs.servicio.idServicio = :idServicio AND rs.reserva.estadoReserva != 'CANCELADA' AND rs.reserva.fechaInicio <= :fechaTermino AND rs.reserva.fechaTermino >= :fechaInicio")
    List<ReservaServicio> findCabanasSolapadas(
    @Param("idServicio") Integer idServicio,
    @Param("fechaInicio") LocalDate fechaInicio,
    @Param("fechaTermino") LocalDate fechaTermino);

    long countByReserva_IdReservaAndServicio_TipoServicio_NombreIgnoreCase(
    Integer idReserva, String nombre);

    
}

