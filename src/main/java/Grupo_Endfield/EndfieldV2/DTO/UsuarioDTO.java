package Grupo_Endfield.EndfieldV2.DTO;

import jakarta.validation.constraints.*;


import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
public class UsuarioDTO {

    @NotNull
    private Integer idVisitante;

    @NotBlank
    @Size(max = 100)
    private String username;

    @NotBlank
    @Size(min = 8, max = 255)
    private String password;

}