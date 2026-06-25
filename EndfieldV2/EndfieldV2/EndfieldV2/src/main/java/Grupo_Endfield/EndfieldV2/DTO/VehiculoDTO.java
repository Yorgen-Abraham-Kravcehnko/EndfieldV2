package Grupo_Endfield.EndfieldV2.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoDTO {

    @NotNull(message = "El id del visitante es obligatorio")
    private Integer idVisitante;

    @NotBlank(message = "La patente es obligatoria")
    private String patente;

    @Size(max = 100, message = "La marca no puede superar 100 caracteres")
    private String marca;

    @Size(max = 100, message = "El modelo no puede superar 100 caracteres")
    private String modelo;

    @Size(max = 50, message = "El color no puede superar 50 caracteres")
    private String color;

    @NotBlank(message = "El tipo de vehículo es obligatorio")
    @Pattern(
        regexp = "^(AUTOMOVIL|SUV|PICKUP|MOTOCICLETA)$",
        message = "El tipo debe ser AUTOMOVIL, SUV, PICKUP o MOTOCICLETA"
    )
    private String tipoVehiculo;
}