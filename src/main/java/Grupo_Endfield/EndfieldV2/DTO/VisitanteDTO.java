package Grupo_Endfield.EndfieldV2.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitanteDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
    private String nombre;

    @NotBlank(message = "El primer apellido es obligatorio")
    @Size(max = 100, message = "El primer apellido no puede superar 100 caracteres")
    private String primerApellido;

    @Size(max = 100, message = "El segundo apellido no puede superar 100 caracteres")
    private String segundoApellido;

    @NotBlank(message = "El RUT es obligatorio")
    @Pattern(regexp = "^[0-9]{7,8}$", message = "El RUT debe contener entre 7 y 8 dígitos")
    private String rut;

    @NotBlank(message = "El dígito verificador es obligatorio")
    @Size(min = 1, max = 1, message = "El dígito verificador debe ser 1 caracter")
    private String dv;

    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 120, message = "La edad no puede superar 120 años")
    private Integer edad;

    @Size(max = 20, message = "El sexo no puede superar 20 caracteres")
    private String sexo;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato válido")
    @Size(max = 150, message = "El correo no puede superar 150 caracteres")
    private String correo;
}