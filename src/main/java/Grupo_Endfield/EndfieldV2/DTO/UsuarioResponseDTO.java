package Grupo_Endfield.EndfieldV2.DTO;



import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Integer idUsuario;
    private String username;
    private Boolean activo;
    private String nombreVisitante;
    private String rutVisitante;

    // Getters y Setters
}