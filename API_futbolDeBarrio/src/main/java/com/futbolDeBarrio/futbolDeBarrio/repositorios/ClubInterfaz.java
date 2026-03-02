package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;

/**
 * Interfaz que se encarga de gestionar las operaciones CRUD básicas sobre la entidad ClubEntidad. 
 * Extiende JpaRepository para facilitar el acceso.
 */
@Repository
public interface ClubInterfaz extends JpaRepository<ClubEntidad, Long> {

    /**
     * Método que busca un club por su ID.
     * 
     * @param idClub El identificador único del club.
     * @return El club correspondiente al ID proporcionado, si existe.
     */
    ClubEntidad findByIdClub(long idClub);

    /**
     * Método que elimina un club dado su ID.
     * 
     * @param idClub El identificador único del club que se desea eliminar.
     */
    void deleteByIdClub(long idClub);
    
    /**
     * Método que busca un club por su dirección de correo electrónico.
     * 
     * @param email El correo electrónico del club que se desea buscar.
     * @return Un Optional que contiene el club correspondiente al correo electrónico,
     *         si existe.
     */
    Optional<ClubEntidad> findByEmailClub(String email); 
    
    
    /**
     * Verifica si existe un club con el correo electrónico proporcionado.
     *
     * @param email Correo electrónico del club a comprobar.
     * @return true si ya existe un club con ese email, false en caso contrario.
     */
    boolean existsByEmailClub(String email);
    
    /**
     * Busca un club por su correo electrónico incluyendo la cuenta asociada.
     *
     * @param email Correo electrónico del club.
     * @return Un Optional que contiene la entidad ClubEntidad con la cuenta cargada si existe.
     */
    @Query("SELECT c FROM ClubEntidad c JOIN FETCH c.cuenta WHERE c.emailClub = :email")
    Optional<ClubEntidad> findByEmailClubConCuenta(@Param("email") String email);
}
