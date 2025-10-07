package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.LoginDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.LoginGoogleDto;
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
/**
 * Clase que se encarga del login
 */
public class LoginFuncionalidades {

    @Autowired
    private UsuarioInterfaz usuarioInterfaz;
    
    @Autowired
    private ClubInterfaz clubInterfaz;
    
    @Autowired
    private InstalacionInterfaz instalacionInterfaz;
   
    @Autowired
    private JwtFuncionalidades jwtUtil;

    /**
     * Método que verifica las credenciales del usuario, club o instalación.
     * @param loginDto Objeto con el email y la contraseña introducidos
     * @return Objeto RespuestaLoginDto si las credenciales son válidas, o null si no lo son
     */
    public RespuestaLoginDto verificarCredenciales(LoginDto loginDto) {
        String tipoUsuario = loginDto.getTipoUsuario(); // nuevo

        switch (tipoUsuario) {
            case "jugador":
            case "administrador":
                Optional<UsuarioEntidad> usuario = usuarioInterfaz.findByEmailUsuario(loginDto.getEmail());
                if (usuario.isPresent() && Utilidades.verificarContrasena(loginDto.getPassword(), usuario.get().getPasswordUsuario())) {
                    String token = jwtUtil.obtenerToken(loginDto.getEmail(), Rol.Usuario);
                    String tipo = usuario.get().getRolUsuario() == RolUsuario.Administrador ? "administrador" : "jugador";   	    
                    UsuarioDto usuarioDto = new UsuarioDto(usuario.get());
                    return new RespuestaLoginDto(tipo, token, usuarioDto);
                }
                break;

            case "club":
                Optional<ClubEntidad> club = clubInterfaz.findByEmailClub(loginDto.getEmail());
                if (club.isPresent() && Utilidades.verificarContrasena(loginDto.getPassword(), club.get().getPasswordClub())) {
                    String token = jwtUtil.obtenerToken(loginDto.getEmail(), Rol.Club);
                    return new RespuestaLoginDto("club", token, club.get());
                }
                break;

            case "instalacion":
                Optional<InstalacionEntidad> instalacion = instalacionInterfaz.findByEmailInstalacion(loginDto.getEmail());
                if (instalacion.isPresent() && Utilidades.verificarContrasena(loginDto.getPassword(), instalacion.get().getPasswordInstalacion())) {
                    String token = jwtUtil.obtenerToken(loginDto.getEmail(), Rol.Instalacion);
                    return new RespuestaLoginDto("instalacion", token, instalacion.get());
                }
                break;

            default:
                return null; // tipo de usuario desconocido
        }

        return null; // si no encontró credenciales válidas
    }
    
    /**
     * Verifica si el email ya existe en alguno de los tipos de usuario y genera token.
     * @param loginGoogleDto contiene email y tipoUsuario
     * @return RespuestaLoginDto con token y datos del usuario
     */
    public RespuestaLoginDto loginConGoogle(LoginGoogleDto loginGoogleDto) {
        String email = loginGoogleDto.getEmail();
        String tipoUsuario = loginGoogleDto.getTipoUsuario();

        switch (tipoUsuario.toLowerCase()) {
            case "jugador":
            case "administrador":
                UsuarioEntidad usuario = usuarioInterfaz.findByEmailUsuario(email).orElse(null);
                if (usuario != null) {
                    String token = jwtUtil.obtenerToken(email, Rol.Usuario);
                    String tipo = usuario.getRolUsuario() == RolUsuario.Administrador ? "administrador" : "jugador";
                    return new RespuestaLoginDto(tipo, token, usuario);
                }
                break;
            case "club":
                ClubEntidad club = clubInterfaz.findByEmailClub(email).orElse(null);
                if (club != null) {
                    String token = jwtUtil.obtenerToken(email, Rol.Club);
                    return new RespuestaLoginDto("club", token, club);
                }
                break;
            case "instalacion":
                InstalacionEntidad instalacion = instalacionInterfaz.findByEmailInstalacion(email).orElse(null);
                if (instalacion != null) {
                    String token = jwtUtil.obtenerToken(email, Rol.Instalacion);
                    return new RespuestaLoginDto("instalacion", token, instalacion);
                }
                break;
        }

        return null;
    }

}
