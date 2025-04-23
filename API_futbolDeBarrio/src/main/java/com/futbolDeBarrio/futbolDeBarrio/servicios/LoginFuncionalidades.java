package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.LoginDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.RespuestaLoginDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.UsuarioDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.Rol;
import com.futbolDeBarrio.futbolDeBarrio.enums.RolUsuario;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.InstalacionInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.UsuarioInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.utilidades.Utilidades;

@Service
public class LoginFuncionalidades {

    @Autowired
    private UsuarioInterfaz usuarioInterfaz;
    @Autowired
    private ClubInterfaz clubInterfaz;
    @Autowired
    private InstalacionInterfaz instalacionInterfaz;
   
    @Autowired
    private JwtFuncionalidades jwtUtil;

    public RespuestaLoginDto verificarCredenciales(LoginDto loginDto) {
        // Buscar en Usuarios
    	Optional<UsuarioEntidad> usuario = usuarioInterfaz.findByEmailUsuario(loginDto.getEmail());
    	if (usuario.isPresent() && Utilidades.verificarContrasena(loginDto.getPassword(), usuario.get().getPasswordUsuario())) {
    	    String token = jwtUtil.obtenerToken(loginDto.getEmail(), Rol.Usuario);

    	    // Usamos el RolUsuario del UsuarioEntidad
    	    String tipo = usuario.get().getRolUsuario() == RolUsuario.Administrador ? "administrador" : "jugador";
    	    
    	    UsuarioDto usuarioDto = new UsuarioDto(usuario.get());
    	    return new RespuestaLoginDto(tipo, token, usuarioDto);
    	}


        // Buscar en Clubes
        Optional<ClubEntidad> club = clubInterfaz.findByEmailClub(loginDto.getEmail());
        if (club.isPresent() && Utilidades.verificarContrasena(loginDto.getPassword(), club.get().getPasswordClub())) {
            String token = jwtUtil.obtenerToken(loginDto.getEmail(), Rol.Club);
            return new RespuestaLoginDto("club", token, club.get());
        }

        // Buscar en Instalaciones
        Optional<InstalacionEntidad> instalacion = instalacionInterfaz.findByEmailInstalacion(loginDto.getEmail());
        if (instalacion.isPresent() && Utilidades.verificarContrasena(loginDto.getPassword(), instalacion.get().getPasswordInstalacion())) {
            String token = jwtUtil.obtenerToken(loginDto.getEmail(), Rol.Instalacion);
            return new RespuestaLoginDto("instalacion", token, instalacion.get());
        }

        return null;
    }
}
