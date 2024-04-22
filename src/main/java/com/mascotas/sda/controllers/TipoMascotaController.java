package com.mascotas.sda.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mascotas.sda.dto.TipoMascotaDto;
import com.mascotas.sda.exception.LocalNotFoundException;
import com.mascotas.sda.persistencia.entity.TipoMascota;
import com.mascotas.sda.service.TipoMascotaService;

@RestController
@RequestMapping("/tipomascota")
public class TipoMascotaController{

    @Autowired
    private TipoMascotaService tipoMascotaService;

    @GetMapping
    public ResponseEntity <Page<TipoMascota>> findAll(Pageable pageable)
    {
        Page<TipoMascota> tipoMascota = tipoMascotaService.findAll(pageable);
        if(tipoMascota.hasContent()){
            return ResponseEntity.ok(tipoMascota);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{tipoMascotaId}")
    public ResponseEntity<TipoMascota> findOneById(
            @PathVariable Integer tipoMascotaId) throws LocalNotFoundException{

               Optional<TipoMascota> tipoMascota=tipoMascotaService.findOneById(tipoMascotaId);
               if(tipoMascota.isPresent()){
                    return ResponseEntity.ok(tipoMascota.get());
               }
               return ResponseEntity.notFound().build();
            }

    @PostMapping
    public ResponseEntity<TipoMascota> createOne(
        @RequestBody TipoMascotaDto tipoMascotaDto){
            TipoMascota tipoMascota = tipoMascotaService.createOne(tipoMascotaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(tipoMascota);
        }

    @PutMapping("/{tipoMascotaId}")
    public ResponseEntity<TipoMascota> updateOneById(
        @PathVariable Integer tipoMascotaId,
        @RequestBody TipoMascotaDto tipoMascotaDto){
        TipoMascota tipoMascota =tipoMascotaService.updateOneById(
            tipoMascotaDto, tipoMascotaId);
            
        return ResponseEntity.ok(tipoMascota);

    }


}