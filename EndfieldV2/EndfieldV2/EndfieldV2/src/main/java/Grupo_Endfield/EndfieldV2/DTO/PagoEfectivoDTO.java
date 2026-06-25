package Grupo_Endfield.EndfieldV2.DTO;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoEfectivoDTO {

    @NotNull
    private Integer idPago;

    @NotBlank
    @Size(max = 50)
    private String tipoMoneda;

    @Size(max = 100)
    private String numBoleta;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal monto;

    // Getters y Setters
}