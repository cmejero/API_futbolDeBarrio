package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.CuentaEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.TokenVerificacionEmailEntidad;

/**
 * Repositorio para la entidad TokenVerificacionEmail, permite operaciones CRUD y consultas personalizadas.
 */
@Repository
public interface TokenVerificacionEmailInterfaz extends JpaRepository<TokenVerificacionEmailEntidad, Long> {
	
	/**
	 * Busca un token de verificación por su valor.
	 *
	 * @param token Valor del token a buscar.
	 * @return Optional que contiene el token si se encuentra, o vacío si no existe.
	 */
	Optional<TokenVerificacionEmailEntidad> findByToken(String token);
	
	
	/**
	 * Elimina todos los tokens asociados a una cuenta específica.
	 *
	 * @param cuenta Cuenta relacionada con los tokens a eliminar.
	 */
	void deleteByCuenta(CuentaEntidad cuenta);


}
