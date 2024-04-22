package com.mascotas.sda.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mascotas.sda.persistencia.entity.Convocatoria;
import com.mascotas.sda.dto.ConvocatoriaDto;
import com.mascotas.sda.exception.LocalNotFoundException;
import com.mascotas.sda.exception.ObjectNotFoundException;


public interface ConvocatoriaService {

    Page<Convocatoria> findAll(Pageable Pegable);

    Optional<Convocatoria> findOneById(Integer convocatoriaId) throws LocalNotFoundException;

    Convocatoria createOne(ConvocatoriaDto convocatoriaDto);

    Convocatoria updateOneById(ConvocatoriaDto convocatoriaDto, Integer convocatoriaId) throws ObjectNotFoundException;

    Convocatoria deleteMascotas(Integer convocatoriaId) throws LocalNotFoundException;
}
