package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.DTO.PagoEfectivoDTO;
import Grupo_Endfield.EndfieldV2.DTO.PagoEfectivoResponseDTO;
import Grupo_Endfield.EndfieldV2.Modelo.Pago;
import Grupo_Endfield.EndfieldV2.Modelo.PagoEfectivo;
import Grupo_Endfield.EndfieldV2.Repositorio.PagoEfectivoRepositorio;
import Grupo_Endfield.EndfieldV2.Repositorio.PagoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagoEfectivoServicio {

    @Autowired
    private PagoEfectivoRepositorio pagoEfectivoRepositorio;

    @Autowired
    private PagoRepositorio pagoRepositorio;

    public List<PagoEfectivoResponseDTO> obtenerTodos() {
        return pagoEfectivoRepositorio.findAll().stream()
            .map(this::convertirAResponse)
            .collect(Collectors.toList());
    }

    public Optional<PagoEfectivoResponseDTO> obtenerPorId(Integer id) {
        return pagoEfectivoRepositorio.findById(id)
            .map(this::convertirAResponse);
    }

    public PagoEfectivoResponseDTO guardar(PagoEfectivoDTO dto) {
        Pago pago = pagoRepositorio.findById(dto.getIdPago())
            .orElseThrow(() -> new RuntimeException("Pago no encontrado."));

        PagoEfectivo pagoEfectivo = new PagoEfectivo();
        pagoEfectivo.setPago(pago);
        pagoEfectivo.setTipoMoneda(dto.getTipoMoneda());
        pagoEfectivo.setNumBoleta(dto.getNumBoleta());
        pagoEfectivo.setMonto(dto.getMonto());

        return convertirAResponse(pagoEfectivoRepositorio.save(pagoEfectivo));
    }

    public PagoEfectivoResponseDTO actualizar(Integer id, PagoEfectivoDTO dto) {
        PagoEfectivo pagoEfectivo = pagoEfectivoRepositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("PagoEfectivo no encontrado."));
        Pago pago = pagoRepositorio.findById(dto.getIdPago())
            .orElseThrow(() -> new RuntimeException("Pago no encontrado."));

        pagoEfectivo.setPago(pago);
        pagoEfectivo.setTipoMoneda(dto.getTipoMoneda());
        pagoEfectivo.setNumBoleta(dto.getNumBoleta());
        pagoEfectivo.setMonto(dto.getMonto());

        return convertirAResponse(pagoEfectivoRepositorio.save(pagoEfectivo));
    }

    public void eliminar(Integer id) {
        pagoEfectivoRepositorio.deleteById(id);
    }

    private PagoEfectivoResponseDTO convertirAResponse(PagoEfectivo pe) {
        PagoEfectivoResponseDTO dto = new PagoEfectivoResponseDTO();
        dto.setIdPagoEfectivo(pe.getIdPagoEfectivo());
        dto.setIdPago(pe.getPago().getIdPago());
        dto.setTipoMoneda(pe.getTipoMoneda());
        dto.setNumBoleta(pe.getNumBoleta());
        dto.setMonto(pe.getMonto());
        return dto;
    }
}