package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.TokenRecuperacionContrasenaEntidad;

/**
 * Repositorio para gestionar tokens de recuperación de contraseña.
 */
@Repository
public interface TokenRecuperacionContrasenaInterfaz extends JpaRepository<TokenRecuperacionContrasenaEntidad, Long> {

    /**
     * Busca un token de recuperación por su valor.
     *
     * @param token El token a buscar.
     * @return Optional con el token si existe.
     */
    Optional<TokenRecuperacionContrasenaEntidad> findByToken(String token);

    /**
     * Elimina un token de recuperación por su valor.
     *
     * @param token El token a eliminar.
     */
    void deleteByToken(String token); 
    
    /**
     * Elimina tokens asociados a un email.
     *
     * @param email Email asociado a los tokens a eliminar.
     */
    void deleteByEmail(String email);
}
