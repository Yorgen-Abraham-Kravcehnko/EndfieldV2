package Grupo_Endfield.EndfieldV2.DTO;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoEfectivoResponseDTO {

    private Integer idPagoEfectivo;
    private Integer idPago;
    private String tipoMoneda;
    private String numBoleta;
    private BigDecimal monto;
}