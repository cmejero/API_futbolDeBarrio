package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    
    
    /**
     * Verifica si ya existe un usuario con el correo electrónico proporcionado.
     *
     * @param email Correo electrónico del usuario.
     * @return true si ya existe un usuario con ese email, false en caso contrario.
     */
    boolean existsByEmailUsuario(String email);
    
    /**
     * Busca un usuario por su correo electrónico incluyendo la cuenta asociada.
     *
     * @param email Correo electrónico del usuario.
     * @return Un Optional que contiene la entidad UsuarioEntidad con la cuenta cargada si existe.
    */
    @Query("SELECT u FROM UsuarioEntidad u JOIN FETCH u.cuenta WHERE u.emailUsuario = :email")
    Optional<UsuarioEntidad> findByEmailUsuarioConCuenta(@Param("email") String email);
}
