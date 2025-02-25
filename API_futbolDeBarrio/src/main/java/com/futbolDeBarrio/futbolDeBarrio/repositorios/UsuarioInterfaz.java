package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;

/**
 * Clase que se encarga de gestionar operaciones CRUD básicas sobre la entidad
 * TorneoDto.
 */
public interface UsuarioInterfaz extends JpaRepository<UsuarioEntidad, Long> {

	/**
	 * metodo que busca un usuario dado su id.
	 * 
	 * @param idUsuario
	 * @return
	 */
	UsuarioEntidad findByIdUsuario(Long idUsuario);
	
	/**
	 * metodo que elimina un usuario dado su id.
	 * 
	 * @param idTorneo
	 * @return
	 */
	void deleteByIdUsuario(Long isUsuario);
	
	
	 Optional<UsuarioEntidad> findByEmailUsuarioAndPasswordUsuario(String email, String password);
}
