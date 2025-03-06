package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.LoginDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.UsuarioDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.UsuarioInterfaz;

@Service
public class LoginFuncionalidades {

	
	 @Autowired
	    private UsuarioInterfaz usuarioInterfaz;
	 
	 @Autowired
	 private JwtUtils jwtUtils;
	 

	    // Método para autenticar al usuario
	 public String autenticarUsuario(LoginDto loginDto) {
	        // Buscar el usuario por su email
	        Optional<UsuarioEntidad> optionalUsuario = usuarioInterfaz.findByEmailUsuario(loginDto.getEmail());

	        // Si el usuario existe
	        if (optionalUsuario.isPresent()) {
	            UsuarioEntidad usuarioEntidad = optionalUsuario.get();
	            // Crear el DTO a partir de la entidad
	            UsuarioDto usuarioDto = new UsuarioDto();
	            usuarioDto.setEmailUsuario(usuarioEntidad.getEmailUsuario());
	            usuarioDto.setPasswordUsuario(usuarioEntidad.getPasswordUsuario());

	            // Verificar si las contraseñas coinciden
	            if (usuarioDto.getPasswordUsuario().equals(loginDto.getPassword())) {
	                // Generar el token si las credenciales son correctas
	                return jwtUtils.generateToken(usuarioDto.getEmailUsuario());
	            }
	        }
	        return null; // Si las credenciales no son correctas o el usuario no existe
	    }
}
