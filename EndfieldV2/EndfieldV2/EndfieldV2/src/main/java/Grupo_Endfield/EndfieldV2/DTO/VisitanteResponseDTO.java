package Grupo_Endfield.EndfieldV2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitanteResponseDTO {

    private Integer idVisitante;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String rut;
    private String dv;
    private Integer edad;
    private String sexo;
    private String correo;
    private List<VehiculoResumenDTO> vehiculos;
}