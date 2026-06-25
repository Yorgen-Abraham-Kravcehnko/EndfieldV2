package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.DTO.ReservaDTO;
import Grupo_Endfield.EndfieldV2.DTO.ReservaResponseDTO;
import Grupo_Endfield.EndfieldV2.Modelo.Parque;
import Grupo_Endfield.EndfieldV2.Modelo.Reserva;
import Grupo_Endfield.EndfieldV2.Modelo.Visitante;
import Grupo_Endfield.EndfieldV2.Repositorio.ParqueRepositorio;
import Grupo_Endfield.EndfieldV2.Repositorio.ReservaRepositorio;
import Grupo_Endfield.EndfieldV2.Repositorio.ReservaServicioRepositorio;
import Grupo_Endfield.EndfieldV2.Repositorio.ServicioRepositorio;
import Grupo_Endfield.EndfieldV2.Repositorio.TipoServicioParqueRepositorio;
import Grupo_Endfield.EndfieldV2.Repositorio.VisitanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaServicio {

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    @Autowired
    private ReservaServicioRepositorio reservaServicioRepositorio;

    @Autowired
    private ServicioRepositorio servicioRepositorio;

    @Autowired
    private TipoServicioParqueRepositorio tipoServicioParqueRepositorio;

    @Autowired
    private VisitanteRepositorio visitanteRepositorio;

    @Autowired
    private ParqueRepositorio parqueRepositorio;

    public List<ReservaResponseDTO> obtenerTodos() {
        return reservaRepositorio.findAll().stream()
            .map(this::convertirAResponse)
            .collect(Collectors.toList());
    }

    public Optional<ReservaResponseDTO> obtenerPorId(Integer id) {
        return reservaRepositorio.findById(id)
            .map(this::convertirAResponse);
    }

    public ReservaResponseDTO guardar(ReservaDTO dto) {
        Visitante visitante = visitanteRepositorio.findById(dto.getIdVisitante())
            .orElseThrow(() -> new RuntimeException("Visitante no encontrado."));
        Parque parque = parqueRepositorio.findById(dto.getIdParque())
            .orElseThrow(() -> new RuntimeException("Parque no encontrado."));

        Reserva reserva = new Reserva();
        reserva.setVisitante(visitante);
        reserva.setParque(parque);
        reserva.setFechaInicio(dto.getFechaInicio());
        reserva.setFechaTermino(dto.getFechaTermino());
        reserva.setCantidadAcompanantes(dto.getCantidadAcompanantes());
        reserva.setTipoVisita(dto.getTipoVisita());
        reserva.setEstadoReserva("PENDIENTE");

        // Regla 1
        long reservasActivas = reservaRepositorio
            .countByVisitante_IdVisitanteAndEstadoReservaIn(
                visitante.getIdVisitante(), List.of("PENDIENTE", "CONFIRMADA"));
        if (reservasActivas >= 2) {
            throw new RuntimeException("El visitante ya tiene 2 reservas activas.");
        }

        // Regla 3
        long diasHastaInicio = ChronoUnit.DAYS.between(LocalDate.now(), dto.getFechaInicio());
        if (diasHastaInicio > 60) {
            throw new RuntimeException("La fecha de inicio no puede superar los 60 días desde hoy.");
        }

        // Regla 4
        if (!parque.getAbiertoTemporada()) {
            throw new RuntimeException("El parque " + parque.getNombre() + " está cerrado por temporada.");
        }

        return convertirAResponse(reservaRepositorio.save(reserva));
    }

    public ReservaResponseDTO actualizar(Integer id, ReservaDTO dto) {
        Reserva reserva = reservaRepositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada."));

        Visitante visitante = visitanteRepositorio.findById(dto.getIdVisitante())
            .orElseThrow(() -> new RuntimeException("Visitante no encontrado."));
        Parque parque = parqueRepositorio.findById(dto.getIdParque())
            .orElseThrow(() -> new RuntimeException("Parque no encontrado."));

        reserva.setVisitante(visitante);
        reserva.setParque(parque);
        reserva.setFechaInicio(dto.getFechaInicio());
        reserva.setFechaTermino(dto.getFechaTermino());
        reserva.setCantidadAcompanantes(dto.getCantidadAcompanantes());
        reserva.setTipoVisita(dto.getTipoVisita());

        return convertirAResponse(reservaRepositorio.save(reserva));
    }

    public void eliminar(Integer id) {
        reservaRepositorio.deleteById(id);
    }

    private ReservaResponseDTO convertirAResponse(Reserva r) {
        ReservaResponseDTO dto = new ReservaResponseDTO();
        dto.setIdReserva(r.getIdReserva());
        dto.setEstadoReserva(r.getEstadoReserva());
        dto.setCantidadAcompanantes(r.getCantidadAcompanantes());
        dto.setFechaInicio(r.getFechaInicio());
        dto.setFechaTermino(r.getFechaTermino());
        dto.setTipoVisita(r.getTipoVisita());
        dto.setNombreVisitante(r.getVisitante().getNombre());
        dto.setRutVisitante(r.getVisitante().getRut());
        dto.setNombreParque(r.getParque().getNombre());
        return dto;
    }
}