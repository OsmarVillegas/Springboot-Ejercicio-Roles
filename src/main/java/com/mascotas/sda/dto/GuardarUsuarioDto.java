package com.mascotas.sda.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuardarUsuarioDto implements Serializable {
    
    @NotBlank
    @Size(min=4, max=40)
    private String nombre;

    @NotBlank
    private String username;

    @NotBlank
    @Size(min=8, max=20)
    private String password;

    @NotBlank
    @Size(min=8, max=20)
    private String repitePassword;


}
