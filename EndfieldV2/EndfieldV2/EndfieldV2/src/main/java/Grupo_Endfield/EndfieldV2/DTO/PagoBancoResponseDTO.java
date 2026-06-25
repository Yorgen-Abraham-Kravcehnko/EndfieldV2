package Grupo_Endfield.EndfieldV2.DTO;

import java.math.BigDecimal;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoBancoResponseDTO {

    private Integer idPagoBanco;
    private Integer idPago;
    private String nombreBanco;
    private String tipoCuenta;
    private String numBoleta;
    private BigDecimal monto;

    // Getters y Setters
}