package com.mascotas.sda.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mascotas.sda.dto.MascotaDto;
import com.mascotas.sda.exception.LocalNotFoundException;
import com.mascotas.sda.persistencia.entity.Mascotas;
import com.mascotas.sda.service.MascotaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mascotas")
public class MacotasController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping
    public ResponseEntity<Page<Mascotas>> findAll(Pageable pageable) {
        Page<Mascotas> mascota = mascotaService.findAll(pageable);
        if (mascota.hasContent()) {
            return ResponseEntity.ok(mascota);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{mascotaId}")
    public ResponseEntity<Mascotas> findOneById(
            @PathVariable(name = "mascotaId", required = true) Integer mascotaId) throws LocalNotFoundException {

        Optional<Mascotas> mascotas = mascotaService.findOneById(mascotaId);
        if (mascotas.isPresent()) {
            return ResponseEntity.ok(mascotas.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Mascotas> createOne(
            @RequestBody @Valid MascotaDto mascotaDto) {
        Mascotas mascotas = mascotaService.createOne(mascotaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mascotas);
    }

    @PutMapping("/{mascotaId}")
    public ResponseEntity<Mascotas> updateOneById(
            @PathVariable Integer mascotaId,
            @RequestBody MascotaDto mascotaDto) {
        Mascotas mascotas = mascotaService.updateOneById(
                mascotaDto, mascotaId);

        return ResponseEntity.ok(mascotas);

    }

    @PutMapping("/{mascotaId}/disabled")
    public ResponseEntity<Mascotas> disabledOneById(
            @PathVariable Integer mascotaId) {
        Mascotas mascotas = mascotaService.disabledOneById(mascotaId);

        return ResponseEntity.ok(mascotas);

    }

    @DeleteMapping("/{mascotaId}")
    public ResponseEntity<String> eliminarMascota(@PathVariable Integer mascotaId) {
        {
            try {
                mascotaService.deleteMascotas(mascotaId);
                return new ResponseEntity<>("Mascota eliminada exitosamente", HttpStatus.OK);
            } catch (LocalNotFoundException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            }
        }
    }

}
