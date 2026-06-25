package Grupo_Endfield.EndfieldV2.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoResponseDTO {

    private Integer idPago;
    private Integer idReserva;
    private String tipoPago;
    private BigDecimal montoTotal;
    private LocalDate fechaPago;
    private String estadoPago;
    private String nombreParque;
    private String nombreVisitante;

    // Getters y Setters
}