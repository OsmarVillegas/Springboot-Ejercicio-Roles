package com.mascotas.sda.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegistrado implements Serializable {
    
    private Long id;
    private String username;
    private String name;
    private String role;
    private String jwt;
}
