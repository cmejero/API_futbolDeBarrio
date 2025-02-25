package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;

/**
 * Clase que se encarga de gestionar operaciones CRUD básicas sobre la entidad InstalacionDto.
 */
public interface InstalacionInterfaz extends JpaRepository<InstalacionEntidad, Long> {
	
	
	/**
	 * metodo que busca una instalacion dado su id.
	 * @param idInstalacion
	 * @return
	 */
	InstalacionEntidad findByIdInstalacion(Long idInstalacion);
	
	/**
	 * metodo que elimina una instalacion por su id.
	 * @param idInstalacion
	 * @return
	 */
	void deleteByIdInstalacion(Long idInstalacion);
	
	Optional<InstalacionEntidad> findByEmailInstalacionAndPasswordInstalacion(String email, String password);

}
