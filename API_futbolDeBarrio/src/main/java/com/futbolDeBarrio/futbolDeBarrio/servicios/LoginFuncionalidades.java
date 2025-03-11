package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.InstalacionDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.RespuestaLoginDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.UsuarioDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
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

    public RespuestaLoginDto verificarCredenciales(String email, String password) {
        // Buscar en Usuarios
        Optional<UsuarioEntidad> usuario = usuarioInterfaz.findByEmailUsuario(email);
        if (usuario.isPresent() && utilidades.verificarContrasena(password, usuario.get().getPasswordUsuario())) {
            return new RespuestaLoginDto("usuario", new UsuarioDto(usuario.get())); // Solo el DTO
        }

        // Buscar en Clubes
        Optional<ClubEntidad> club = clubInterfaz.findByEmailClub(email);
        if (club.isPresent() && utilidades.verificarContrasena(password, club.get().getPasswordClub())) {
            return new RespuestaLoginDto("club", new ClubDto(club.get())); // Solo el DTO
        }

        // Buscar en Instalaciones
        Optional<InstalacionEntidad> instalacion = instalacionInterfaz.findByEmailInstalacion(email);
        if (instalacion.isPresent() && utilidades.verificarContrasena(password, instalacion.get().getPasswordInstalacion())) {
            return new RespuestaLoginDto("instalacion", new InstalacionDto(instalacion.get())); // Solo el DTO
        }

        // Si no se encontró nada
        return null;
    }
}
