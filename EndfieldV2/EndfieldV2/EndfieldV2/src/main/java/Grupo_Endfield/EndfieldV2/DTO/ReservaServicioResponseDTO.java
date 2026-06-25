package Grupo_Endfield.EndfieldV2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaServicioResponseDTO {

    private Integer idReserva;
    private Integer idServicio;
    private String nombreServicio;
    private String tipoServicio;
    private Integer cantidad;
    private LocalDate fechaServicio;
}