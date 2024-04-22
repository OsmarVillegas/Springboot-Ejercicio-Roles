package com.mascotas.sda.persistencia.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mascotas.sda.persistencia.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
