package com.mascotas.sda.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mascotas.sda.dto.MascotaDto;
import com.mascotas.sda.exception.LocalNotFoundException;
import com.mascotas.sda.exception.ObjectNotFoundException;
import com.mascotas.sda.persistencia.entity.Mascotas;
import com.mascotas.sda.persistencia.entity.TipoMascota;
import com.mascotas.sda.persistencia.entity.Mascotas.MascotaEstatus;
import com.mascotas.sda.persistencia.repository.MascotasRepository;
import com.mascotas.sda.persistencia.repository.TipoMascotaRepository;
import com.mascotas.sda.service.MascotaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MascotasServiceImpl implements MascotaService {

    @Autowired
    private MascotasRepository mascotaRepository;
    @Autowired
    private TipoMascotaRepository tipoMascotaRepository;

    @Override
    public Page<Mascotas> findAll(Pageable pageable) {
        return mascotaRepository.findAll(pageable);
    }

    @Override
    public Optional<Mascotas> findOneById(Integer tipoMascotaId) throws LocalNotFoundException {
        Optional <Mascotas> mascota = mascotaRepository.findById(tipoMascotaId);

        if(!mascota.isPresent()){
            throw new LocalNotFoundException("Mascota no disponible");
        }

        return mascotaRepository.findById(tipoMascotaId);
    }

    @Override
    public Mascotas createOne(MascotaDto mascotaDto) {
        Mascotas mascotas = new Mascotas();
        mascotas.setNombre(mascotaDto.getNombre());
        mascotas.setPrecio(mascotaDto.getPrecio());
        TipoMascota tipomascotaOptional = tipoMascotaRepository.findById(mascotaDto.getTipomascota_id()).orElseThrow(() -> new EntityNotFoundException("tipoMascota not found with ID: " + mascotaDto.getTipomascota_id()));
        mascotas.setTipoMascota(tipomascotaOptional);
        mascotas.setEstatus(mascotaDto.getEstatus());

        return mascotaRepository.save(mascotas);
    }

    @Override
    public Mascotas updateOneById(MascotaDto mascotaDto, Integer mascotaId) {

        Mascotas mascotas = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new ObjectNotFoundException("Este tipo de mascotas no existe"));
        mascotas.setNombre(mascotaDto.getNombre());
        return mascotaRepository.save(mascotas);

    }

    @Override
    public Mascotas disabledOneById(Integer mascotaId) {
        Mascotas mascotas = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new ObjectNotFoundException("No se encontro esta mascotas"));

        mascotas.setEstatus(MascotaEstatus.AGOTADO);
        return mascotaRepository.save(mascotas);
    }

    @Override
    public Mascotas deleteMascotas(Integer mascotaId) throws LocalNotFoundException {
        Mascotas mascota = mascotaRepository.findById(mascotaId).orElseThrow(() -> new LocalNotFoundException("Mascota no valida"));

        mascotaRepository.delete(mascota);

        return mascota;
    }

}
