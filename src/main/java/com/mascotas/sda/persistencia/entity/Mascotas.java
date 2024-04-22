package com.mascotas.sda.persistencia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "mascotas")
public class Mascotas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private Float precio;

    @Enumerated(EnumType.STRING)
    private MascotaEstatus estatus;

    @ManyToOne
    @JoinColumn(name = "tipomascota_id")
    private TipoMascota tipoMascota;

    public static enum MascotaEstatus {
        EN_EXISTENCIA, AGOTADO
    };

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public MascotaEstatus getEstatus() {
        return this.estatus;
    }

    public void setEstatus(MascotaEstatus estatus) {
        this.estatus = estatus;
    }
    
    public TipoMascota getTipoMascota() {
        return this.tipoMascota;
    }

    public void setTipoMascota(TipoMascota tipoMascota) {
        this.tipoMascota = tipoMascota;
    }
}
