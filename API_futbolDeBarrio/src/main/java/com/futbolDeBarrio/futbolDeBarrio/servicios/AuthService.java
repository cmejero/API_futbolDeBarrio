package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.Rol;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.InstalacionInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.UsuarioInterfaz;

@Service
public class AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioInterfaz usuarioInterfaz;

    @Autowired
    private ClubInterfaz clubInterfaz;

    @Autowired
    private InstalacionInterfaz instalacionInterfaz;

    // Método para autenticar al usuario y generar un token
    public String login(String username, String password) {
        // Verificar las credenciales del usuario
        Optional<UsuarioEntidad> usuario = usuarioInterfaz.findByEmailUsuario(username);
        if (usuario.isPresent() && verificarContrasena(password, usuario.get().getPasswordUsuario())) {
            return jwtUtil.generarToken(username, Rol.Usuario);
        }

        // Verificar las credenciales del club
        Optional<ClubEntidad> club = clubInterfaz.findByEmailClub(username);
        if (club.isPresent() && verificarContrasena(password, club.get().getPasswordClub())) {
            return jwtUtil.generarToken(username, Rol.Club);
        }

        // Verificar las credenciales de la instalación
        Optional<InstalacionEntidad> instalacion = instalacionInterfaz.findByEmailInstalacion(username);
        if (instalacion.isPresent() && verificarContrasena(password, instalacion.get().getPasswordInstalacion())) {
            return jwtUtil.generarToken(username, Rol.Instalacion);
        }

        // Si las credenciales no son válidas, lanzar excepción
        throw new RuntimeException("Credenciales inválidas");
    }

    // Método para verificar la contraseña
    private boolean verificarContrasena(String password, String passwordHash) {
        // Aquí deberías implementar tu lógica de verificación de contraseña
        // Usualmente, se usaría un algoritmo como BCrypt o similar
        return password.equals(passwordHash);  // Para fines de ejemplo, hacemos una comparación directa
    }

    // Método para verificar si el token es válido
    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }

    // Método para obtener el nombre de usuario desde el token
    public String getUsernameFromToken(String token) {
        return jwtUtil.extractUsername(token);
    }
}
