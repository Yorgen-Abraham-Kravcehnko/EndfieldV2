package Grupo_Endfield.EndfieldV2.DTO;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaServicioDTO {


    @NotNull
    private Integer idReserva;

    @NotNull
    private Integer idServicio;

    @NotNull
    @Min(1)
    private Integer cantidad;

    @NotNull
    @FutureOrPresent
    private LocalDate fechaServicio;

    // Getters y Setters
}