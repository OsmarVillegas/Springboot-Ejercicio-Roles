package com.mascotas.sda.dto;


import java.time.LocalDate;

import com.mascotas.sda.persistencia.entity.Convocatoria.TipoConvocatoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConvocatoriaDto {
    
    @NotBlank(message = "El valor titulo es null")
    @Size(min = 1, max = 40, message = "El campo debe tener entre 1 y 40 caracteres")
    private String titulo;

    @Size(min = 20, max = 90, message = "El campo debe tener entre 20 y 90 caracteres")
    private String descripcion;

    @NotNull(message="El valor tipo de convocatoria es null")
    private TipoConvocatoria tipoConvocatoria;

    @PastOrPresent(message = "La fecha debe ser igual o anterior a la fecha actual")
    private LocalDate fechaRegistro;
}
