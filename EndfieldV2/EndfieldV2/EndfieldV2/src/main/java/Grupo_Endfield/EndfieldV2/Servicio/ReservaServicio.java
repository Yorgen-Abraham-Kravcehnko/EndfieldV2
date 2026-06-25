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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaServicio {

    private static final Logger log = LoggerFactory.getLogger(ReservaServicioServicio.class);

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
        log.info("Obteniendo todas las reservas");
        return reservaRepositorio.findAll().stream()
            .map(this::convertirAResponse)
            .collect(Collectors.toList());
    }

    public Optional<ReservaResponseDTO> obtenerPorId(Integer id) {
        log.info("Buscando reserva ID: {}", id);
        return reservaRepositorio.findById(id)
            .map(this::convertirAResponse);
    }

    public ReservaResponseDTO guardar(ReservaDTO dto) {
        log.info("Iniciando creación de reserva para visitante ID: {}", dto.getIdVisitante());

        Visitante visitante = visitanteRepositorio.findById(dto.getIdVisitante())
            .orElseThrow(() -> {
                log.warn("Visitante ID: {} no encontrado", dto.getIdVisitante());
                return new RuntimeException("Visitante no encontrado.");
            });

        Parque parque = parqueRepositorio.findById(dto.getIdParque())
            .orElseThrow(() -> {
                log.warn("Parque ID: {} no encontrado", dto.getIdParque());
                return new RuntimeException("Parque no encontrado.");
            });

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
            log.warn("Visitante ID: {} ya tiene {} reservas activas", 
                visitante.getIdVisitante(), reservasActivas);
            throw new RuntimeException("El visitante ya tiene 2 reservas activas.");
        }

        // Regla 3
        long diasHastaInicio = ChronoUnit.DAYS.between(LocalDate.now(), dto.getFechaInicio());
        if (diasHastaInicio > 60) {
            log.warn("Fecha de inicio supera 60 días: {} días", diasHastaInicio);
            throw new RuntimeException("La fecha de inicio no puede superar los 60 días desde hoy.");
        }

        // Regla 4
        if (!parque.getAbiertoTemporada()) {
            log.warn("Parque {} está cerrado por temporada", parque.getNombre());
            throw new RuntimeException("El parque " + parque.getNombre() + " está cerrado por temporada.");
        }

        ReservaResponseDTO response = convertirAResponse(reservaRepositorio.save(reserva));
        log.info("Reserva creada exitosamente con ID: {}", response.getIdReserva());
        return response;
    }

    public ReservaResponseDTO actualizar(Integer id, ReservaDTO dto) {
        log.info("Actualizando reserva ID: {}", id);

        Reserva reserva = reservaRepositorio.findById(id)
            .orElseThrow(() -> {
                log.warn("Reserva ID: {} no encontrada", id);
                return new RuntimeException("Reserva no encontrada.");
            });

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

        ReservaResponseDTO response = convertirAResponse(reservaRepositorio.save(reserva));
        log.info("Reserva ID: {} actualizada exitosamente", id);
        return response;
    }

    public void eliminar(Integer id) {
        log.info("Eliminando reserva ID: {}", id);
        reservaRepositorio.deleteById(id);
        log.info("Reserva ID: {} eliminada exitosamente", id);
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