package com.mascotas.sda.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mascotas.sda.dto.TipoMascotaDto;
import com.mascotas.sda.exception.LocalNotFoundException;
import com.mascotas.sda.persistencia.entity.TipoMascota;

public interface TipoMascotaService {
    
    Page<TipoMascota> findAll(Pageable Pegable);

    Optional<TipoMascota> findOneById(Integer tipoMascotaId) throws LocalNotFoundException;

    TipoMascota createOne(TipoMascotaDto tipoMascotaId);

    TipoMascota updateOneById(TipoMascotaDto tipoMascotaDto, Integer tipoMascotaId);

}