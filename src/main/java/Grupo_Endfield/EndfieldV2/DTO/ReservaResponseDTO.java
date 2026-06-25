package Grupo_Endfield.EndfieldV2.DTO;


import lombok.Data;
import java.time.LocalDate;


import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaResponseDTO {

    private Integer idReserva;
    private String estadoReserva;
    private Integer cantidadAcompanantes;
    private LocalDate fechaInicio;
    private LocalDate fechaTermino;
    private String tipoVisita;
    private String nombreVisitante;
    private String rutVisitante;
    private String nombreParque;

    
}