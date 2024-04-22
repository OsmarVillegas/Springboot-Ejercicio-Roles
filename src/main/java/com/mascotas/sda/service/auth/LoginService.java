package com.mascotas.sda.service.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mascotas.sda.dto.GuardarUsuarioDto;
import com.mascotas.sda.dto.UsuarioRegistrado;
import com.mascotas.sda.dto.auth.LoginRequest;
import com.mascotas.sda.dto.auth.LoginResponse;
import com.mascotas.sda.persistencia.entity.Usuario;
import com.mascotas.sda.service.UsuarioService;

@Service
public class LoginService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UsuarioRegistrado registerOneUsuario(GuardarUsuarioDto nuevoUsuario){
        Usuario usuario = usuarioService.registerOneUser(nuevoUsuario);
        
        UsuarioRegistrado userDto = new UsuarioRegistrado();
        userDto.setId(usuario.getId());
        userDto.setName(usuario.getNombre());
        userDto.setUsername(usuario.getUsername());
        userDto.setRole(usuario.getRoles().name());

        String jwt = jwtService.generateToken(usuario, generateExtraClaims(usuario));
        userDto.setJwt(jwt);

        return userDto;
    }

    private Map<String, Object> generateExtraClaims(Usuario usuario){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", usuario.getId());
        claims.put("name", usuario.getNombre());
        claims.put("authorities", usuario.getRoles().name());
        return claims;
    }

    public LoginResponse login(LoginRequest authRequest) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());

            authenticationManager.authenticate(authentication);
    
            UserDetails usuario = usuarioService.findOneByUsername(authRequest.getUsername()).get();
            String jwt = jwtService.generateToken(usuario, generateExtraClaims((Usuario) usuario));
    
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);

            return response;
        } catch (AuthenticationException e) {

            System.out.println("Error de autenticación: " + e.getMessage());

            throw new BadCredentialsException("Credenciales invalidas", e);
        }
    }

    public boolean validateToken(String jwt) {
        try {
            jwtService.extractUsername(jwt);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

}
