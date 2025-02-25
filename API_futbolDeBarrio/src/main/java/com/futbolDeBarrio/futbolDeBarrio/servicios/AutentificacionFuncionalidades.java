package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.LoginDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.TipoUsuarioLoginDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;

@Service
public class AutentificacionFuncionalidades {

	  @Autowired
	    private UsuarioFuncionalidades usuarioFuncionalidades;

	    @Autowired
	    private ClubFuncionalidades clubFuncionalidades;

	    @Autowired
	    private InstalacionFuncionalidades instalacionFuncionalidades;

	    public ResponseEntity<TipoUsuarioLoginDto> login(LoginDto loginDto) {
	        String email = loginDto.getEmail();
	        String password = loginDto.getPassword();

	        // Intentar encontrar usuario en la base de datos
	        Optional<UsuarioEntidad> usuario = usuarioFuncionalidades.buscarPorEmailYPassword(email, password);

	        if (usuario.isPresent()) {
	            // Si el usuario existe, devolverlo con tipo "usuario"
	            return ResponseEntity.ok(new TipoUsuarioLoginDto("usuario", usuario.get()));
	        }

	        // Si no se encuentra en la tabla de usuario, buscar en la tabla club
	        Optional<ClubEntidad> club = clubFuncionalidades.buscarClubPorEmailYPassword(email, password);
	        if (club.isPresent()) {
	            return ResponseEntity.ok(new TipoUsuarioLoginDto("club", club.get()));
	        }

	        // Si no se encuentra en la tabla club, buscar en la tabla instalación
	        Optional<InstalacionEntidad> instalacion = instalacionFuncionalidades.buscarInstalacionPorEmailYPassword(email, password);
	        if (instalacion.isPresent()) {
	            return ResponseEntity.ok(new TipoUsuarioLoginDto("instalacion", instalacion.get()));
	        }

	        // Si no se encuentra en ninguna tabla, devolver un error con mensaje
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body(new TipoUsuarioLoginDto("error", null, "Usuario no encontrado"));
	    }

}