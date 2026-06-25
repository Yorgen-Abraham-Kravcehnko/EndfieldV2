package Grupo_Endfield.EndfieldV2.DTO;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
public class PagoBancoDTO {

    @NotNull
    private Integer idPago;

    @NotNull
    private Integer idBanco;

    @Size(max = 100)
    private String numBoleta;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal monto;

    // Getters y Setters
}