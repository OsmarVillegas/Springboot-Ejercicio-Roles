package com.mascotas.sda.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mascotas.sda.persistencia.entity.TipoMascota;

public interface TipoMascotaRepository  extends JpaRepository<TipoMascota, Integer>{

}
