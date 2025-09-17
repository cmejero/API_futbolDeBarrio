package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.ActaPartidoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.EventoPartidoEntidad;

/**
 * Interfaz que se encarga de gestionar las operaciones CRUD básicas sobre la entidad EventoPartidoEntidad. 
 * Extiende JpaRepository para facilitar el acceso.
 */
@Repository
public interface EventoPartidoInterfaz extends JpaRepository<EventoPartidoEntidad, Long> {

	
	 /**
     * Busca un evento de partido por su identificador único.
     *
     * @param idEventoPartido ID del evento.
     * @return Entidad {@link EventoPartidoEntidad} correspondiente si existe.
     */
    EventoPartidoEntidad findByIdEventoPartido(long idEventoPartido);
}
