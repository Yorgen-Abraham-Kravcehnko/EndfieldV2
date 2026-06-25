package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.DTO.PagoBancoDTO;
import Grupo_Endfield.EndfieldV2.DTO.PagoBancoResponseDTO;
import Grupo_Endfield.EndfieldV2.Modelo.Banco;
import Grupo_Endfield.EndfieldV2.Modelo.Pago;
import Grupo_Endfield.EndfieldV2.Modelo.PagoBanco;
import Grupo_Endfield.EndfieldV2.Repositorio.BancoRepositorio;
import Grupo_Endfield.EndfieldV2.Repositorio.PagoBancoRepositorio;
import Grupo_Endfield.EndfieldV2.Repositorio.PagoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagoBancoServicio {

    @Autowired
    private PagoBancoRepositorio pagoBancoRepositorio;

    @Autowired
    private PagoRepositorio pagoRepositorio;

    @Autowired
    private BancoRepositorio bancoRepositorio;

    public List<PagoBancoResponseDTO> obtenerTodos() {
        return pagoBancoRepositorio.findAll().stream()
            .map(this::convertirAResponse)
            .collect(Collectors.toList());
    }

    public Optional<PagoBancoResponseDTO> obtenerPorId(Integer id) {
        return pagoBancoRepositorio.findById(id)
            .map(this::convertirAResponse);
    }

    public PagoBancoResponseDTO guardar(PagoBancoDTO dto) {
        Pago pago = pagoRepositorio.findById(dto.getIdPago())
            .orElseThrow(() -> new RuntimeException("Pago no encontrado."));
        Banco banco = bancoRepositorio.findById(dto.getIdBanco())
            .orElseThrow(() -> new RuntimeException("Banco no encontrado."));

        PagoBanco pagoBanco = new PagoBanco();
        pagoBanco.setPago(pago);
        pagoBanco.setBanco(banco);
        pagoBanco.setNumBoleta(dto.getNumBoleta());
        pagoBanco.setMonto(dto.getMonto());

        return convertirAResponse(pagoBancoRepositorio.save(pagoBanco));
    }

    public PagoBancoResponseDTO actualizar(Integer id, PagoBancoDTO dto) {
        PagoBanco pagoBanco = pagoBancoRepositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("PagoBanco no encontrado."));
        Pago pago = pagoRepositorio.findById(dto.getIdPago())
            .orElseThrow(() -> new RuntimeException("Pago no encontrado."));
        Banco banco = bancoRepositorio.findById(dto.getIdBanco())
            .orElseThrow(() -> new RuntimeException("Banco no encontrado."));

        pagoBanco.setPago(pago);
        pagoBanco.setBanco(banco);
        pagoBanco.setNumBoleta(dto.getNumBoleta());
        pagoBanco.setMonto(dto.getMonto());

        return convertirAResponse(pagoBancoRepositorio.save(pagoBanco));
    }

    public void eliminar(Integer id) {
        pagoBancoRepositorio.deleteById(id);
    }

    private PagoBancoResponseDTO convertirAResponse(PagoBanco pb) {
        PagoBancoResponseDTO dto = new PagoBancoResponseDTO();
        dto.setIdPagoBanco(pb.getIdPagoBanco());
        dto.setIdPago(pb.getPago().getIdPago());
        dto.setNombreBanco(pb.getBanco().getNombreBanco());
        dto.setTipoCuenta(pb.getBanco().getTipoCuenta());
        dto.setNumBoleta(pb.getNumBoleta());
        dto.setMonto(pb.getMonto());
        return dto;
    }
}