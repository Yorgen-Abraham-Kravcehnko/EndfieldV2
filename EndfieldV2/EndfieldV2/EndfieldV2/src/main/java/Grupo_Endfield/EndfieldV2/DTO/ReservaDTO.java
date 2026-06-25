package Grupo_Endfield.EndfieldV2.DTO;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
public class ReservaDTO {

    @NotNull
    private Integer idVisitante;

    @NotNull
    private Integer idParque;

    @NotNull
    @Min(0)
    private Integer cantidadAcompanantes;

    @NotNull
    @FutureOrPresent
    private LocalDate fechaInicio;

    @NotNull
    @Future
    private LocalDate fechaTermino;

    @NotBlank
    @Size(max = 20)
    private String tipoVisita;

   
}