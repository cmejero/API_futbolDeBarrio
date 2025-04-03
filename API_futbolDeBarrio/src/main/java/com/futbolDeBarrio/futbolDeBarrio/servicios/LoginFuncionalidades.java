package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.LoginDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.RespuestaLoginDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.Rol;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.InstalacionInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.UsuarioInterfaz;

@Service
public class LoginFuncionalidades {

    @Autowired
    private UsuarioInterfaz usuarioInterfaz;
    @Autowired
    private ClubInterfaz clubInterfaz;
    @Autowired
    private InstalacionInterfaz instalacionInterfaz;
    @Autowired
    private Utilidades utilidades;
    @Autowired
    private JwtUtil jwtUtil; // Para generar el token JWT

    // Método modificado para verificar credenciales y generar un token JWT
    public RespuestaLoginDto verificarCredenciales(LoginDto loginDto) {
        // Primero, buscar en Usuarios
        Optional<UsuarioEntidad> usuario = usuarioInterfaz.findByEmailUsuario(loginDto.getEmail());
        if (usuario.isPresent() && utilidades.verificarContrasena(loginDto.getPassword(), usuario.get().getPasswordUsuario())) {
            // Asignar el rol usando el enum
            String role = Rol.Usuario.name();  // Usar el enum para el rol
            // Generar token JWT para el usuario con su rol
            String token = jwtUtil.generarToken(loginDto.getEmail(), Rol.Usuario);
            return new RespuestaLoginDto("usuario", token);
        }

        // Buscar en Clubes
        Optional<ClubEntidad> club = clubInterfaz.findByEmailClub(loginDto.getEmail());
        if (club.isPresent() && utilidades.verificarContrasena(loginDto.getPassword(), club.get().getPasswordClub())) {
            // Asignar el rol usando el enum
            String role = Rol.Club.name();  // Usar el enum para el rol
            // Generar token JWT para el club con su rol
            String token = jwtUtil.generarToken(loginDto.getEmail(), Rol.Club);
            return new RespuestaLoginDto("club", token);
        }

        // Buscar en Instalaciones
        Optional<InstalacionEntidad> instalacion = instalacionInterfaz.findByEmailInstalacion(loginDto.getEmail());
        if (instalacion.isPresent() && utilidades.verificarContrasena(loginDto.getPassword(), instalacion.get().getPasswordInstalacion())) {
            // Asignar el rol usando el enum
            String role = Rol.Instalacion.name();  // Usar el enum para el rol
            // Generar token JWT para la instalación con su rol
            String token = jwtUtil.generarToken(loginDto.getEmail(), Rol.Instalacion);
            return new RespuestaLoginDto("instalacion", token);
        }

        // Si no se encuentran las credenciales en ninguna de las entidades
        return null;
    }
}
