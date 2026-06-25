package Grupo_Endfield.EndfieldV2.DTO;

import jakarta.validation.constraints.*;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
public class PagoDTO {

    @NotNull
    private Integer idReserva;

    @NotBlank
    @Size(max = 50)
    private String tipoPago;

    // Getters y Setters
}