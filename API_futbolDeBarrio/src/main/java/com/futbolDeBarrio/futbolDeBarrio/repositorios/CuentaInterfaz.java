package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.CuentaEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.Rol;

/**
 * Interfaz de repositorio para la entidad Cuenta, permite operaciones CRUD y consultas personalizadas relacionadas con las cuentas.
 */
@Repository
public interface CuentaInterfaz extends JpaRepository<CuentaEntidad, Long> {

	/**
	 * Busca una cuenta por su correo electrónico y rol.
	 *
	 * @param email Correo electrónico de la cuenta.
	 * @param rol Rol asociado a la cuenta.
	 * @return Optional que contiene la cuenta si se encuentra, o vacío si no existe.
	 */
    Optional<CuentaEntidad> findByEmailAndRol(String email, Rol rol);

    /**
     * Busca una cuenta por su correo electrónico.
     *
     * @param email Correo electrónico de la cuenta.
     * @return Optional que contiene la cuenta si se encuentra, o vacío si no existe.
     */
    Optional<CuentaEntidad> findByEmail(String email);

}
