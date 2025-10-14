package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.LoginDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.LoginGoogleDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.RespuestaLoginDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.UsuarioDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.JugadorEstadisticaGlobalEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.Estado;
import com.futbolDeBarrio.futbolDeBarrio.enums.Rol;
import com.futbolDeBarrio.futbolDeBarrio.enums.RolUsuario;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.InstalacionInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.JugadorEstadisticaGlobalInterfaz;
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
    @Autowired
    private JugadorEstadisticaGlobalInterfaz jugadorEstadisticaGlobalInterfaz;

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
    public LoginGoogleDto loginConGoogle(LoginGoogleDto loginGoogleDto) {
        String email = loginGoogleDto.getEmail();
        String tipoUsuario = loginGoogleDto.getTipoUsuario();
        String nombreCompleto = loginGoogleDto.getNombreCompleto() != null ? 
                                loginGoogleDto.getNombreCompleto() : "Desconocido";

        switch (tipoUsuario.toLowerCase()) {

        case "jugador":
            UsuarioEntidad usuario = usuarioInterfaz.findByEmailUsuario(email).orElse(null);
            if (usuario == null) {
                // Crear nuevo usuario automáticamente usando datos de Google
                usuario = new UsuarioEntidad();
                usuario.setEmailUsuario(email);
                usuario.setNombreCompletoUsuario(nombreCompleto);

                // Generar alias: primeras 2 letras de cada palabra
                String[] palabras = nombreCompleto.split("\\s+");
                StringBuilder alias = new StringBuilder();
                for (String palabra : palabras) {
                    alias.append(palabra.length() >= 2 ? palabra.substring(0, 2) : palabra);
                }
                usuario.setAliasUsuario(alias.toString().toLowerCase());

                // Contraseña aleatoria
                usuario.setPasswordUsuario(UUID.randomUUID().toString());
                usuario.setRolUsuario(RolUsuario.Jugador);
                usuario.setEstadoUsuario(Estado.Activo);

                // 1️⃣ Guardar usuario primero para que tenga ID
                usuario = usuarioInterfaz.save(usuario);

                // 2️⃣ Crear estadísticas iniciales en cero
                JugadorEstadisticaGlobalEntidad estadistica = new JugadorEstadisticaGlobalEntidad();
                estadistica.setJugadorGlobalId(usuario); 
                estadistica.setGolesGlobal(0);
                estadistica.setAsistenciasGlobal(0);
                estadistica.setAmarillasGlobal(0);
                estadistica.setRojasGlobal(0);
                estadistica.setPartidosJugadosGlobal(0);
                estadistica.setPartidosGanadosGlobal(0);
                estadistica.setPartidosPerdidosGlobal(0);
                estadistica.setMinutosJugadosGlobal(0);

                // Guardar estadísticas
                jugadorEstadisticaGlobalInterfaz.save(estadistica);

                System.out.println("Usuario creado automáticamente: " + email + ", alias: " + alias);
            }

            String tokenUsuario = jwtUtil.obtenerToken(email, Rol.Usuario);
            return new LoginGoogleDto(tokenUsuario, email, "jugador", usuario.getNombreCompletoUsuario(), 
                                      usuario.getIdUsuario(), usuario.isEsPremium());

            case "club":
                ClubEntidad club = clubInterfaz.findByEmailClub(email).orElse(null);
                if (club == null) {
                    club = new ClubEntidad();
                    club.setEmailClub(email);
                    club.setNombreClub(nombreCompleto);

                    // Generar abreviatura: iniciales de hasta 3 palabras
                    String[] palabrasClub = nombreCompleto.split("\\s+");
                    StringBuilder abreviatura = new StringBuilder();
                    for (int i = 0; i < palabrasClub.length && i < 3; i++) {
                        abreviatura.append(palabrasClub[i].substring(0, 1));
                    }
                    club.setAbreviaturaClub(abreviatura.toString().toUpperCase());

                    // Contraseña aleatoria
                    club.setPasswordClub(UUID.randomUUID().toString());
                    clubInterfaz.save(club);

                    System.out.println("Club creado automáticamente: " + email + ", abreviatura: " + abreviatura);
                }

                String tokenClub = jwtUtil.obtenerToken(email, Rol.Club);
                return new LoginGoogleDto(tokenClub, email, "club", club.getNombreClub(), 
                                          club.getIdClub(), club.isEsPremium());

            case "instalacion":
                InstalacionEntidad instalacion = instalacionInterfaz.findByEmailInstalacion(email).orElse(null);
                if (instalacion == null) {
                    instalacion = new InstalacionEntidad();
                    instalacion.setEmailInstalacion(email);
                    instalacion.setNombreInstalacion(nombreCompleto);
                    instalacion.setPasswordInstalacion(UUID.randomUUID().toString());
                    instalacion.setEstadoInstalacion(Estado.Activo);
                    instalacionInterfaz.save(instalacion);

                    System.out.println("Instalación creada automáticamente: " + email);
                }

                String tokenInstalacion = jwtUtil.obtenerToken(email, Rol.Instalacion);
                // Instalación no tiene esPremium
                return new LoginGoogleDto(tokenInstalacion, email, "instalacion", instalacion.getNombreInstalacion(), 
                                          instalacion.getIdInstalacion(), false);

            default:
                System.out.println("Tipo de usuario desconocido: " + tipoUsuario);
                return null;
        }
    }




}
