package com.mascotas.sda.persistencia.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "convocatoria")
public class Convocatoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cveConvocatoria;

    @Size(min = 1, max = 40, message = "El campo debe tener entre 1 y 40 caracteres")
    private String titulo;

    @Size(min = 20, max = 90, message = "El campo debe tener entre 20 y 90 caracteres")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private TipoConvocatoria tipoConvocatoria;

    @Temporal(TemporalType.DATE)
    @PastOrPresent(message = "La fecha debe ser igual o anterior a la fecha actual")
    private LocalDate fechaRegistro;

    public static enum TipoConvocatoria {
        INTERNA, EXTERNA, GENERAL
    }

}
