package com.mascotas.sda.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mascotas.sda.persistencia.entity.Mascotas;

public interface MascotasRepository extends JpaRepository<Mascotas, Integer>{

}
