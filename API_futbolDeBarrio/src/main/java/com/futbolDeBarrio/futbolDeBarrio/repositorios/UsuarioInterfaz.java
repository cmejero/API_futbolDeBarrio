package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;

/**
 * Repositorio encargado de gestionar operaciones CRUD sobre la entidad UsuarioEntidad.
 * Extiende {@link JpaRepository} para proporcionar acceso a la base de datos.
 */
@Repository
public interface UsuarioInterfaz extends JpaRepository<UsuarioEntidad, Long> {

    /**
     * Busca un usuario por su identificador único.
     *
     * @param idUsuario ID del usuario.
     * @return Entidad {@link UsuarioEntidad} correspondiente si existe.
     */
    UsuarioEntidad findByIdUsuario(long idUsuario);

    /**
     * Elimina un usuario por su identificador.
     *
     * @param idUsuario ID del usuario a eliminar.
     */
    void deleteByIdUsuario(Long idUsuario);

    /**
     * Busca un usuario por su dirección de correo electrónico.
     *
     * @param email Correo electrónico del usuario.
     * @return Un {@link Optional} que puede contener la entidad {@link UsuarioEntidad} si existe.
     */
    Optional<UsuarioEntidad> findByEmailUsuario(String email);
    
    
    
    boolean existsByEmailUsuario(String email);
}
