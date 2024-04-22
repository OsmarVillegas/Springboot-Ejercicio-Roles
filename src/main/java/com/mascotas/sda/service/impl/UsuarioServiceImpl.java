package com.mascotas.sda.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mascotas.sda.dto.GuardarUsuarioDto;
import com.mascotas.sda.exception.InvalidPasswordException;
import com.mascotas.sda.persistencia.entity.Usuario;
import com.mascotas.sda.persistencia.repository.UsuarioRepository;
import com.mascotas.sda.service.UsuarioService;
import com.mascotas.sda.util.Roles;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario registerOneUser(GuardarUsuarioDto nuevoUsuario) {
        validatePassword(nuevoUsuario);

        Usuario usuario = new Usuario();
        usuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
        usuario.setUsername(nuevoUsuario.getUsername());
        usuario.setNombre(nuevoUsuario.getNombre());
        usuario.setRoles(Roles.ROLE_USUARIO);

        return usuarioRepository.save(usuario);
    }


    @Override
	public Optional<Usuario> findOneByUsername(String username){
		return usuarioRepository.findByUsername(username);
	}
	
	private void validatePassword(GuardarUsuarioDto dto) {
		if(!StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getRepitePassword())) {
			throw new InvalidPasswordException("password o repeatPassword vacios");
		}
		if(!dto.getPassword().equals(dto.getRepitePassword())) {
			throw new InvalidPasswordException("El password no coincide");
		}
	}
}
