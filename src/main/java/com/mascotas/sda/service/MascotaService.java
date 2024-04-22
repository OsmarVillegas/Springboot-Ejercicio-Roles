package com.mascotas.sda.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mascotas.sda.dto.MascotaDto;
import com.mascotas.sda.exception.LocalNotFoundException;
import com.mascotas.sda.persistencia.entity.Mascotas;

public interface MascotaService {

    Page<Mascotas> findAll(Pageable Pegable);

    Optional<Mascotas> findOneById(Integer mascotaId) throws LocalNotFoundException;

    Mascotas createOne(MascotaDto mascotaDto);

    Mascotas updateOneById(MascotaDto mascotaDto, Integer mascotaId);

    Mascotas disabledOneById(Integer mascotaId);

    Mascotas deleteMascotas(Integer mascotaId)  throws LocalNotFoundException;
}
