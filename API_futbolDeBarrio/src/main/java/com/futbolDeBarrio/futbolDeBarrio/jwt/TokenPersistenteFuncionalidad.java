package com.futbolDeBarrio.futbolDeBarrio.jwt;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.InstalacionDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.TokenPersistenteDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.UsuarioDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.TokenPersistenteEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.Rol;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.TokenPersistenteInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.servicios.ClubFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.InstalacionFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.UsuarioFuncionalidades;

@Service
/**
 * Servicio para la gestión de tokens persistentes.
 * 
 * Permite generar, validar e invalidar tokens persistentes asociados
 * a usuarios, clubes o instalaciones, así como renovar JWTs a partir
 * de dichos tokens para sesiones prolongadas.
 */
public class TokenPersistenteFuncionalidad {

    @Autowired
    private TokenPersistenteInterfaz tokenRepo;

    @Autowired
    private JwtFuncionalidades jwtFuncionalidades;

    @Autowired
    private UsuarioFuncionalidades usuarioFuncionalidades;

    @Autowired
    private ClubFuncionalidades clubFuncionalidades;

    @Autowired
    private InstalacionFuncionalidades instalacionFuncionalidades;

    /**
     * Genera y guarda un token persistente para un usuario.
     *
     * @param idUsuario ID del usuario.
     * @param tipoUsuario Tipo de usuario (usuario, club, etc.).
     * @return Token generado como cadena.
     */
    public String generarTokenPersistente(Long idUsuario, String tipoUsuario) {
        String token = UUID.randomUUID().toString();
        TokenPersistenteEntidad entidad = new TokenPersistenteEntidad();
        entidad.setToken(token);
        entidad.setTipoUsuario(tipoUsuario.toLowerCase());
        entidad.setIdUsuario(idUsuario);
        entidad.setFechaCreacion(LocalDateTime.now());
        entidad.setFechaExpiracion(LocalDateTime.now().plusDays(30));
        entidad.setActivo(true);
        tokenRepo.save(entidad);
        return token;
    }

    
    /**
     * Valida un token persistente activo y no expirado.
     *
     * @param token Token a validar.
     * @return Optional con el token si es válido, vacío si no lo es.
     */
    public Optional<TokenPersistenteEntidad> validarToken(String token) {
        Optional<TokenPersistenteEntidad> t = tokenRepo.findByTokenAndActivoTrue(token);
        if (t.isPresent() && t.get().getFechaExpiracion().isAfter(LocalDateTime.now())) {
            return t;
        }
        return Optional.empty();
    }

    
    
    /**
     * Marca un token persistente como inactivo, impidiendo su uso futuro.
     *
     * @param token Token a invalidar.
     */

    public void invalidarToken(String token) {
        tokenRepo.findByTokenAndActivoTrue(token).ifPresent(t -> {
            t.setActivo(false);
            tokenRepo.save(t);
        });
    }

    
    /**
     * Genera un token persistente para un usuario tras validar su JWT.
     *
     * @param jwtHeader Encabezado Authorization con el JWT.
     * @param idUsuario ID del usuario para el que se genera el token.
     * @param tipoUsuario Tipo de usuario (ej. Usuario, Club, Instalacion).
     * @return DTO con el token persistente y los datos del usuario.
     * @throws SecurityException si el JWT es inválido o no coincide con el usuario.
     */
    public TokenPersistenteDto generarTokenParaUsuario(String jwtHeader, Long idUsuario, String tipoUsuario) {
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
            throw new SecurityException("JWT no válido");
        }

        String jwt = jwtHeader.replace("Bearer ", "");
        Long idJwt = jwtFuncionalidades.obtenerIdUsuario(jwt);
        if (!idJwt.equals(idUsuario)) {
            throw new SecurityException("JWT no corresponde al usuario");
        }

        String tokenPersistente = generarTokenPersistente(idUsuario, tipoUsuario);
        Object datosUsuario = obtenerDtoPorTipoYId(tipoUsuario, idUsuario);

        return new TokenPersistenteDto(tokenPersistente, tipoUsuario, datosUsuario);
    }

    /**
     * Valida un token persistente y genera un nuevo JWT para el usuario asociado.
     *
     * @param tokenPersistente Token persistente recibido.
     * @return Mapa con el nuevo JWT, token persistente, tipo de usuario y datos del usuario.
     * @throws SecurityException si el token es inválido, expirado o el usuario no existe.
     */
    public Map<String, Object> validarTokenPersistente(String tokenPersistente) {
        TokenPersistenteEntidad entidad = validarToken(tokenPersistente)
                .orElseThrow(() -> new SecurityException("Token persistente inválido o expirado"));

        Object datosUsuario = obtenerDtoPorTipoYId(entidad.getTipoUsuario(), entidad.getIdUsuario());
        if (datosUsuario == null) throw new SecurityException("Usuario no encontrado");

        // Generar JWT nuevo
        String emailUsuario;
        Rol rolUsuario;
        switch (entidad.getTipoUsuario().toLowerCase()) {
            case "jugador" -> {
                UsuarioDto u = (UsuarioDto) datosUsuario;
                emailUsuario = u.getEmailUsuario();
                rolUsuario = Rol.Usuario;
            }
            case "club" -> {
                ClubDto c = (ClubDto) datosUsuario;
                emailUsuario = c.getEmailClub();
                rolUsuario = Rol.Club;
            }
            case "instalacion" -> {
                InstalacionDto i = (InstalacionDto) datosUsuario;
                emailUsuario = i.getEmailInstalacion();
                rolUsuario = Rol.Instalacion;
            }
            default -> throw new SecurityException("Tipo de usuario no válido");
        }

        String jwt = jwtFuncionalidades.obtenerToken(emailUsuario, rolUsuario);

        Map<String, Object> resp = new HashMap<>();
        resp.put("jwt", jwt);
        resp.put("tokenPersistente", tokenPersistente);
        resp.put("tipoUsuario", entidad.getTipoUsuario());
        resp.put("datosUsuario", datosUsuario);

        return resp;
    }

    /**
     * Obtiene el DTO correspondiente según el tipo de usuario y su ID.
     *
     * @param tipoUsuario Tipo de usuario ("jugador", "club", "instalacion").
     * @param idUsuario ID del usuario.
     * @return DTO del usuario correspondiente, o null si el tipo no es válido.
     */
    private Object obtenerDtoPorTipoYId(String tipoUsuario, Long idUsuario) {
        return switch (tipoUsuario.toLowerCase()) {
            case "jugador" -> usuarioFuncionalidades.obtenerUsuarioDtoPorId(idUsuario);
            case "club" -> clubFuncionalidades.obtenerClubDtoPorId(idUsuario);
            case "instalacion" -> instalacionFuncionalidades.obtenerInstalacionDtoPorId(idUsuario);
            default -> null;
        };
    }
}
