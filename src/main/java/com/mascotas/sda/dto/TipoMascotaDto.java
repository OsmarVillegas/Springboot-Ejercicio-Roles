package com.mascotas.sda.dto;

import jakarta.validation.constraints.NotBlank;

public class TipoMascotaDto {
    
    @NotBlank
    private String nombre;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
