package com.mascotas.sda.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mascotas.sda.persistencia.entity.Convocatoria;

public interface ConvocatoriaRepository extends JpaRepository<Convocatoria,Integer>{
    
}
