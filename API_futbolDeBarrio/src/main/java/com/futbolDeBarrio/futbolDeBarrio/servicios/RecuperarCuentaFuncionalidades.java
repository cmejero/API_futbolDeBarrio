package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.entidad.TokenRecuperacionContrasenaEntidad;
import com.futbolDeBarrio.futbolDeBarrio.logs.Logs;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.InstalacionInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.TokenRecuperacionContrasenaInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.UsuarioInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.utilidades.Utilidades;

import jakarta.transaction.Transactional;

@Service
public class RecuperarCuentaFuncionalidades {

    @Autowired
    private UsuarioInterfaz usuarioInterfaz;

    @Autowired
    private InstalacionInterfaz instalacionInterfaz;

    @Autowired
    private ClubInterfaz clubInterfaz;

    @Autowired
    private TokenRecuperacionContrasenaInterfaz tokenRecuperacionContrasenaInterfaz;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Envía un enlace de recuperación a un email válido registrado.
     * @param email Correo del usuario a recuperar.
     */
    @Transactional
    public void enviarEnlaceRecuperacion(String email, String tipoUsuario) {
        Logs.ficheroLog("Iniciando recuperación para email: " + email);
        String tipo = tipoUsuario;  

        switch(tipoUsuario) {
        case "usuario":
            if (!usuarioInterfaz.existsByEmailUsuario(email)) throw new RuntimeException("Email no encontrado");
            break;
        case "club":
            if (!clubInterfaz.existsByEmailClub(email)) throw new RuntimeException("Email no encontrado");
            break;
        case "instalacion":
            if (!instalacionInterfaz.existsByEmailInstalacion(email)) throw new RuntimeException("Email no encontrado");
            break;
        default:
            throw new RuntimeException("Tipo de usuario no válido");
    }

        Logs.ficheroLog("Tipo de usuario identificado: " + tipo + " para email: " + email);
        tokenRecuperacionContrasenaInterfaz.deleteByEmail(email);
        Logs.ficheroLog("Tokens anteriores eliminados para email: " + email);

        String token = UUID.randomUUID().toString();
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime expiracion = ahora.plusMinutes(10);

        TokenRecuperacionContrasenaEntidad trc = new TokenRecuperacionContrasenaEntidad();
        trc.setToken(token);
        trc.setEmail(email);
        trc.setTipoUsuario(tipo);
        trc.setFechaCreacion(ahora);
        trc.setFechaExpiracion(expiracion);

        tokenRecuperacionContrasenaInterfaz.save(trc);
        Logs.ficheroLog("Token guardado para recuperación. Email: " + email + ", Token: " + token);

        String enlace = "http://localhost:8080/vista_futbolDeBarrio/restablecerCuenta?token=" + token;

        enviarCorreo(email, enlace);
        Logs.ficheroLog("Correo de recuperación enviado a: " + email);
    }

    /**
     * Envía el correo con el enlace de recuperación.
     * @param destinatario Email destino.
     * @param enlace URL para restablecer contraseña.
     */
    @Transactional
    private void enviarCorreo(String destinatario, String enlace) {
        Logs.ficheroLog("Enviando correo a: " + destinatario + " con enlace: " + enlace);
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject("Recuperación de contraseña");
        mensaje.setText("Haz clic en el siguiente enlace para restablecer tu contraseña:\n" + enlace);
        mailSender.send(mensaje);
    }

    /**
     * Actualiza la contraseña si el token es válido y las contraseñas coinciden.
     * @param token Token de recuperación.
     * @param nuevaContrasenya Nueva contraseña.
     * @param repetirContrasena Confirmación de contraseña.
     */
    public void actualizarContrasena(String token, String nuevaContrasenya, String repetirContrasena) {
        Logs.ficheroLog("Iniciando actualización de contraseña con token: " + token);

        if (!nuevaContrasenya.equals(repetirContrasena)) {
            Logs.ficheroLog("Contraseñas no coinciden para token: " + token);
            throw new RuntimeException("Las contraseñas no coinciden.");
        }

        TokenRecuperacionContrasenaEntidad trc = tokenRecuperacionContrasenaInterfaz.findByToken(token)
                .orElseThrow(() -> {
                    Logs.ficheroLog("Token inválido o no encontrado: " + token);
                    return new RuntimeException("Token inválido o no encontrado.");
                });

        if (trc.estaExpirado()) {
            Logs.ficheroLog("Token expirado: " + token);
            throw new RuntimeException("El token ha expirado.");
        }

        actualizarSegunTipoUsuario(trc, nuevaContrasenya);
    }

    /**
     * Actualiza la contraseña según el tipo de usuario y elimina el token.
     * @param trc Entidad del token de recuperación.
     * @param nuevaContrasenya Nueva contraseña.
     */
    private void actualizarSegunTipoUsuario(TokenRecuperacionContrasenaEntidad trc, String nuevaContrasenya) {
        String tipo = trc.getTipoUsuario();
        String email = trc.getEmail();
        Logs.ficheroLog("Token válido. Tipo de usuario: " + tipo + ", Email: " + email);

        switch (tipo) {
            case "usuario" -> {
                var usuario = usuarioInterfaz.findByEmailUsuario(email)
                        .orElseThrow(() -> {
                            Logs.ficheroLog("Usuario no encontrado con email: " + email);
                            return new RuntimeException("Usuario no encontrado");
                        });
                usuario.setPasswordUsuario(Utilidades.encriptarContrasenya(nuevaContrasenya));
                usuarioInterfaz.save(usuario);
            }
            case "instalacion" -> {
                var instalacion = instalacionInterfaz.findByEmailInstalacion(email)
                        .orElseThrow(() -> {
                            Logs.ficheroLog("Instalación no encontrada con email: " + email);
                            return new RuntimeException("Instalación no encontrada");
                        });
                instalacion.setPasswordInstalacion(Utilidades.encriptarContrasenya(nuevaContrasenya));
                instalacionInterfaz.save(instalacion);
            }
            case "club" -> {
                var club = clubInterfaz.findByEmailClub(email)
                        .orElseThrow(() -> {
                            Logs.ficheroLog("Club no encontrado con email: " + email);
                            return new RuntimeException("Club no encontrado");
                        });
                club.setPasswordClub(Utilidades.encriptarContrasenya(nuevaContrasenya));
                clubInterfaz.save(club);
            }
            default -> {
                Logs.ficheroLog("Tipo de usuario desconocido: " + tipo);
                throw new RuntimeException("Tipo de usuario desconocido.");
            }
        }

        tokenRecuperacionContrasenaInterfaz.delete(trc);
        Logs.ficheroLog("Token eliminado y contraseña actualizada para email: " + email);
    }
}
