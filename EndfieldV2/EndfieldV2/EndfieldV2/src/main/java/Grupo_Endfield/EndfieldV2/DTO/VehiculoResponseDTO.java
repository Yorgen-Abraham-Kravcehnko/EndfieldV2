package Grupo_Endfield.EndfieldV2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoResponseDTO {

    private Integer idVehiculo;
    private Integer idVisitante;
    private String nombreVisitante;
    private String patente;
    private String marca;
    private String modelo;
    private String color;
    private String tipoVehiculo;
}