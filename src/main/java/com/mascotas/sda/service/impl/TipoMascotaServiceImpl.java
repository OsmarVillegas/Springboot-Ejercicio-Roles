package com.mascotas.sda.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mascotas.sda.dto.TipoMascotaDto;
import com.mascotas.sda.exception.LocalNotFoundException;
import com.mascotas.sda.exception.ObjectNotFoundException;
import com.mascotas.sda.persistencia.entity.TipoMascota;
import com.mascotas.sda.persistencia.repository.TipoMascotaRepository;
import com.mascotas.sda.service.TipoMascotaService;

@Service
public class TipoMascotaServiceImpl implements TipoMascotaService {

    @Autowired
    private TipoMascotaRepository tipoMascotaRepository;
 
    
    @Override
    public Page<TipoMascota> findAll(Pageable pageable) {
      return tipoMascotaRepository.findAll(pageable);
    }

    @Override
    public Optional<TipoMascota> findOneById(Integer tipoMascotaId) throws LocalNotFoundException {
        Optional<TipoMascota> tipomascota = tipoMascotaRepository.findById(tipoMascotaId);

        if(!tipomascota.isPresent()){
            throw new LocalNotFoundException("Tipo mascota no esta disponible");
        }

        return tipoMascotaRepository.findById(tipoMascotaId);
    }

    @Override
    public TipoMascota createOne(TipoMascotaDto tipoMascotaDto) {
        TipoMascota tipoMascota = new TipoMascota();
        tipoMascota.setNombre(tipoMascotaDto.getNombre());
        return tipoMascotaRepository.save(tipoMascota);
    }

    @Override
    public TipoMascota updateOneById(TipoMascotaDto tipoMascotaDto, Integer tipoMascotaId) {
       
        TipoMascota tipoMascota = tipoMascotaRepository.findById(tipoMascotaId).orElseThrow(()->
        new ObjectNotFoundException("Este tipo de mastcota no existe"));
        tipoMascota.setNombre(tipoMascotaDto.getNombre());
        return tipoMascotaRepository.save(tipoMascota);
        
    }

}
