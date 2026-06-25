package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.DTO.ReservaServicioDTO;
import Grupo_Endfield.EndfieldV2.DTO.ReservaServicioResponseDTO;
import Grupo_Endfield.EndfieldV2.Modelo.Reserva;
import Grupo_Endfield.EndfieldV2.Modelo.ReservaServicio;
import Grupo_Endfield.EndfieldV2.Modelo.ReservaServicioId;
import Grupo_Endfield.EndfieldV2.Modelo.Servicio;
import Grupo_Endfield.EndfieldV2.Modelo.TipoServicioParqueId;
import Grupo_Endfield.EndfieldV2.Repositorio.ReservaRepositorio;
import Grupo_Endfield.EndfieldV2.Repositorio.ReservaServicioRepositorio;
import Grupo_Endfield.EndfieldV2.Repositorio.ServicioRepositorio;
import Grupo_Endfield.EndfieldV2.Repositorio.TipoServicioParqueRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaServicioServicio {

    @Autowired
    private ReservaServicioRepositorio reservaServicioRepositorio;

    @Autowired
    private ServicioRepositorio servicioRepositorio;

    @Autowired
    private TipoServicioParqueRepositorio tipoServicioParqueRepositorio;

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    public List<ReservaServicioResponseDTO> obtenerTodos() {
        return reservaServicioRepositorio.findAll().stream()
            .map(this::convertirAResponse)
            .collect(Collectors.toList());
    }

    public Optional<ReservaServicioResponseDTO> obtenerPorId(ReservaServicioId id) {
        return reservaServicioRepositorio.findById(id)
            .map(this::convertirAResponse);
    }

    public ReservaServicioResponseDTO guardar(ReservaServicioDTO dto) {

        Reserva reserva = reservaRepositorio.findById(dto.getIdReserva())
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada."));

        Servicio servicio = servicioRepositorio.findById(dto.getIdServicio())
            .orElseThrow(() -> new RuntimeException("Servicio no encontrado."));

        Integer idTipoParque = reserva.getParque().getTipoParque() != null
            ? reserva.getParque().getTipoParque().getIdTipoParque()
            : null;

        // REGLA 7
        if (idTipoParque == null) {
            throw new RuntimeException(
                "El parque no tiene tipo definido. No se puede validar compatibilidad del servicio.");
        }

        TipoServicioParqueId tipoServicioParqueId = new TipoServicioParqueId(
            servicio.getTipoServicio().getIdTipoServicio(), idTipoParque);
        if (!tipoServicioParqueRepositorio.existsById(tipoServicioParqueId)) {
            throw new RuntimeException("El servicio no es compatible con el tipo de parque.");
        }

        // REGLA 6
        long cabanasPorReserva = reservaServicioRepositorio
            .countByReserva_IdReservaAndServicio_TipoServicio_NombreIgnoreCase(
                dto.getIdReserva(), "Cabaña");
        if (cabanasPorReserva >= 2) {
            throw new RuntimeException("No puede agregar más de 2 cabañas por reservación.");
        }

        // REGLA 2
        LocalDate fechaInicio = reserva.getFechaInicio();
        LocalDate fechaTermino = reserva.getFechaTermino();
        boolean cabanaOcupada = !reservaServicioRepositorio
            .findCabanasSolapadas(servicio.getIdServicio(), fechaInicio, fechaTermino)
            .isEmpty();
        if (cabanaOcupada) {
            throw new RuntimeException("La cabaña ya está ocupada en las fechas seleccionadas.");
        }

        // REGLA 5
        boolean esCabana = servicio.getTipoServicio().getNombre().equalsIgnoreCase("Cabaña");
        if (esCabana) {
            int acompanantes = reserva.getCantidadAcompanantes();
            int capacidadMaxima = servicio.getCapacidadMaxima() != null
                ? servicio.getCapacidadMaxima() : 8;
            if (acompanantes >= capacidadMaxima) {
                throw new RuntimeException(
                    "La cabaña tiene capacidad máxima de " + capacidadMaxima + " personas.");
            }
        }

        ReservaServicio reservaServicio = new ReservaServicio();
        reservaServicio.setReserva(reserva);
        reservaServicio.setServicio(servicio);
        reservaServicio.setCantidad(dto.getCantidad());
        reservaServicio.setFechaServicio(dto.getFechaServicio());

        return convertirAResponse(reservaServicioRepositorio.save(reservaServicio));
    }

    public void eliminar(ReservaServicioId id) {
        reservaServicioRepositorio.deleteById(id);
    }

    private ReservaServicioResponseDTO convertirAResponse(ReservaServicio rs) {
        ReservaServicioResponseDTO dto = new ReservaServicioResponseDTO();
        dto.setIdReserva(rs.getReserva().getIdReserva());
        dto.setIdServicio(rs.getServicio().getIdServicio());
        dto.setNombreServicio(rs.getServicio().getTipoServicio().getNombre());
        dto.setTipoServicio(rs.getServicio().getTipoServicio().getCategoriaServicio().getNombre());
        dto.setCantidad(rs.getCantidad());
        dto.setFechaServicio(rs.getFechaServicio());
        return dto;
    }
}