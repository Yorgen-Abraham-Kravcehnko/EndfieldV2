package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.DTO.PagoDTO;
import Grupo_Endfield.EndfieldV2.DTO.PagoResponseDTO;
import Grupo_Endfield.EndfieldV2.Modelo.Pago;
import Grupo_Endfield.EndfieldV2.Modelo.Reserva;
import Grupo_Endfield.EndfieldV2.Repositorio.PagoRepositorio;
import Grupo_Endfield.EndfieldV2.Repositorio.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagoServicio {

    @Autowired
    private PagoRepositorio pagoRepositorio;

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    public List<PagoResponseDTO> obtenerTodos() {
        return pagoRepositorio.findAll().stream()
            .map(this::convertirAResponse)
            .collect(Collectors.toList());
    }

    public Optional<PagoResponseDTO> obtenerPorId(Integer id) {
        return pagoRepositorio.findById(id)
            .map(this::convertirAResponse);
    }

    public PagoResponseDTO guardar(PagoDTO dto) {
        Reserva reserva = reservaRepositorio.findById(dto.getIdReserva())
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada."));

        Pago pago = new Pago();
        pago.setReserva(reserva);
        pago.setTipoPago(dto.getTipoPago());
        pago.setFechaPago(LocalDate.now());
        pago.setEstadoPago("PENDIENTE");
        pago.setMontoTotal(BigDecimal.ZERO);

        return convertirAResponse(pagoRepositorio.save(pago));
    }

    public PagoResponseDTO actualizar(Integer id, PagoDTO dto) {
        Pago pago = pagoRepositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Pago no encontrado."));

        Reserva reserva = reservaRepositorio.findById(dto.getIdReserva())
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada."));

        pago.setReserva(reserva);
        pago.setTipoPago(dto.getTipoPago());

        return convertirAResponse(pagoRepositorio.save(pago));
    }

    public void eliminar(Integer id) {
        pagoRepositorio.deleteById(id);
    }

    private PagoResponseDTO convertirAResponse(Pago p) {
        PagoResponseDTO dto = new PagoResponseDTO();
        dto.setIdPago(p.getIdPago());
        dto.setIdReserva(p.getReserva().getIdReserva());
        dto.setTipoPago(p.getTipoPago());
        dto.setMontoTotal(p.getMontoTotal());
        dto.setFechaPago(p.getFechaPago());
        dto.setEstadoPago(p.getEstadoPago());
        dto.setNombreParque(p.getReserva().getParque().getNombre());
        dto.setNombreVisitante(p.getReserva().getVisitante().getNombre());
        return dto;
    }
}