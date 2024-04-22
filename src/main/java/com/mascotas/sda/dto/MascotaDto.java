package com.mascotas.sda.dto;

import com.mascotas.sda.persistencia.entity.Mascotas.MascotaEstatus;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MascotaDto {
    
    @NotBlank
    private String nombre;

    @DecimalMin(value = "0.01")
    private Float precio;

    @Min(value = 1)
    private Integer tipomascota_id;

    @NotNull
    private MascotaEstatus estatus;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecio() {
        return this.precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getTipomascota_id() {
        return this.tipomascota_id;
    }

    public void setTipomascota_id(Integer tipomascota_id) {
        this.tipomascota_id = tipomascota_id;
    }

    public MascotaEstatus getEstatus() {
        return this.estatus;
    }

    public void setEstatus(MascotaEstatus estatus) {
        this.estatus = estatus;
    }

}
