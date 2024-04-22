package com.mascotas.sda.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mascotas.sda.dto.GuardarUsuarioDto;
import com.mascotas.sda.persistencia.entity.Usuario;

@Service
public interface UsuarioService {

    Usuario registerOneUser(GuardarUsuarioDto newUser);

    Optional<Usuario> findOneByUsername (String usermane);
}