package com.mascotas.sda.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mascotas.sda.dto.ConvocatoriaDto;

import com.mascotas.sda.exception.LocalNotFoundException;
import com.mascotas.sda.exception.ObjectNotFoundException;
import com.mascotas.sda.persistencia.entity.Convocatoria;
import com.mascotas.sda.persistencia.repository.ConvocatoriaRepository;

import com.mascotas.sda.service.ConvocatoriaService;

import jakarta.validation.Valid;


@Service
public class ConvocatoriaServicelmpl implements ConvocatoriaService {

    @Autowired
    private ConvocatoriaRepository convocatoriaRepository;

    @Override
    public Page<Convocatoria> findAll(Pageable pageable) {
        return convocatoriaRepository.findAll(pageable);
    }

    @Override
    public Optional<Convocatoria> findOneById(Integer convocatoriaId) throws LocalNotFoundException {
        Optional<Convocatoria> convocatoria = convocatoriaRepository.findById(convocatoriaId);

        if (!convocatoria.isPresent()) {
            throw new LocalNotFoundException("Convocatoria no disponible");
        }

        return convocatoriaRepository.findById(convocatoriaId);
    }

    @Override
    public Convocatoria createOne(@Valid ConvocatoriaDto convocatoriaDto) {
        Convocatoria convocatoria = new Convocatoria();
        convocatoria.setTitulo(convocatoriaDto.getTitulo());
        convocatoria.setDescripcion(convocatoriaDto.getDescripcion());
        convocatoria.setTipoConvocatoria(convocatoriaDto.getTipoConvocatoria());
        convocatoria.setFechaRegistro(convocatoriaDto.getFechaRegistro());

        return convocatoriaRepository.save(convocatoria);
    }

    @Override
    public Convocatoria updateOneById(ConvocatoriaDto convocatoriaDto, Integer convocatoriaId) {

        Convocatoria convocatoria = convocatoriaRepository.findById(convocatoriaId)
                .orElseThrow(() -> new ObjectNotFoundException("Este tipo de convocatoria no existe"));
                convocatoria.setTitulo(convocatoriaDto.getTitulo());
                convocatoria.setDescripcion(convocatoriaDto.getDescripcion());
                convocatoria.setFechaRegistro(convocatoria.getFechaRegistro());
                convocatoria.setTipoConvocatoria(convocatoria.getTipoConvocatoria());
        return convocatoriaRepository.save(convocatoria);

    }

    // @Override
    // public Convocatoria disabledOneById(Integer convocatoriaId) {
    //     Convocatoria convocatoria = convocatoriaRepository.findById(convocatoriaId)
    //             .orElseThrow(() -> new ObjectNotFoundException("No se encontro esta mascotas"));

    //             convocatoria.setTipoConvocatoria(TipoConvocatoria.EXTERNA);
    //     return convocatoriaRepository.save(convocatoria);
    // }

    @Override
    public Convocatoria deleteMascotas(Integer convocatoriaId) throws LocalNotFoundException {
        Convocatoria convocatoria = convocatoriaRepository.findById(convocatoriaId)
                .orElseThrow(() -> new LocalNotFoundException("Convocatoria no valida"));

        convocatoriaRepository.delete(convocatoria);

        return convocatoria;
    }
}
