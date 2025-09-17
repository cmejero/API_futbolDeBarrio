package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.EventoPartidoDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ActaPartidoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.EquipoTorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.EventoPartidoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ActaPartidoInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.EquipoTorneoInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.EventoPartidoInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.UsuarioInterfaz;

@Service
public class EventoPartidoFuncionalidades {

	@Autowired
	EventoPartidoInterfaz eventoPartidoInterfaz;
	@Autowired
	ActaPartidoInterfaz actaPartidoInterfaz;
	@Autowired
	UsuarioInterfaz usuarioInterfaz;
	@Autowired
	ClubInterfaz clubInterfaz;
	@Autowired
	EquipoTorneoInterfaz equipoTorneoInterfaz;

	public EventoPartidoDto mapearAEventoPartidoDto(EventoPartidoEntidad eventoPartidoEntidad) {
		
		EventoPartidoDto eventoPartidoDto = new EventoPartidoDto();
		
		eventoPartidoDto.setIdEventoPartido(eventoPartidoEntidad.getIdEventoPartido());
		eventoPartidoDto.setJugadorId(eventoPartidoEntidad.getJugador().getIdUsuario());
		eventoPartidoDto.setActaPartidoId(eventoPartidoEntidad.getActaPartido().getIdActaPartido());
		eventoPartidoDto.setClubId(eventoPartidoEntidad.getClub().getIdClub());
		eventoPartidoDto.setEquipoTorneoId(eventoPartidoEntidad.getEquipoTorneo().getIdEquipoTorneo());
		eventoPartidoDto.setTipoEvento(eventoPartidoEntidad.getTipoEvento());
		eventoPartidoDto.setMinuto(eventoPartidoEntidad.getMinuto());

		return eventoPartidoDto;
	}
	
	
	
	
	/**
	 * Mapea un DTO EventoPartidoDto a una entidad EventoPartidoEntidad.
	 * 
	 * @param eventoPartidoDto el DTO del eventoPartido
	 * @return la entidad correspondiente al eventoPartido
	 */
	public EventoPartidoEntidad mapearAEventoPartidoEntidad(EventoPartidoDto eventoPartidoDto) {

	    EventoPartidoEntidad eventoPartidoEntidad = new EventoPartidoEntidad();

	    // ID del evento (puede ser null si es nuevo)
	    eventoPartidoEntidad.setIdEventoPartido(eventoPartidoDto.getIdEventoPartido());

	    // VALIDACIÓN Y MAPEO DE ACTA
	    if (eventoPartidoDto.getActaPartidoId() == null) {
	        throw new IllegalArgumentException("El actaPartidoId no puede ser nulo");
	    }
	    ActaPartidoEntidad acta = actaPartidoInterfaz.findById(eventoPartidoDto.getActaPartidoId())
	            .orElseThrow(() -> new IllegalArgumentException(
	                    "El acta de partido con ID " + eventoPartidoDto.getActaPartidoId() + " no existe"));
	    eventoPartidoEntidad.setActaPartido(acta);

	    // VALIDACIÓN Y MAPEO DE JUGADOR
	    if (eventoPartidoDto.getJugadorId() == null) {
	        throw new IllegalArgumentException("El jugadorId no puede ser nulo");
	    }
	    UsuarioEntidad usuario = usuarioInterfaz.findById(eventoPartidoDto.getJugadorId())
	            .orElseThrow(() -> new IllegalArgumentException(
	                    "El jugador con ID " + eventoPartidoDto.getJugadorId() + " no existe"));
	    eventoPartidoEntidad.setJugador(usuario);

	    // VALIDACIÓN Y MAPEO DE CLUB
	    if (eventoPartidoDto.getClubId() == null) {
	        throw new IllegalArgumentException("El clubId no puede ser nulo");
	    }
	    ClubEntidad club = clubInterfaz.findById(eventoPartidoDto.getClubId())
	            .orElseThrow(() -> new IllegalArgumentException(
	                    "El club con ID " + eventoPartidoDto.getClubId() + " no existe"));
	    eventoPartidoEntidad.setClub(club);

	    // VALIDACIÓN Y MAPEO DE EQUIPO TORNEO
	    if (eventoPartidoDto.getEquipoTorneoId() == null) {
	        throw new IllegalArgumentException("El equipoTorneoId no puede ser nulo");
	    }
	    EquipoTorneoEntidad equipoTorneo = equipoTorneoInterfaz.findById(eventoPartidoDto.getEquipoTorneoId())
	            .orElseThrow(() -> new IllegalArgumentException(
	                    "El equipo del torneo con ID " + eventoPartidoDto.getEquipoTorneoId() + " no existe"));
	    eventoPartidoEntidad.setEquipoTorneo(equipoTorneo);

	    // Tipo de evento y minuto
	    if (eventoPartidoDto.getTipoEvento() == null || eventoPartidoDto.getTipoEvento().isEmpty()) {
	        throw new IllegalArgumentException("El tipoEvento no puede estar vacío");
	    }
	    eventoPartidoEntidad.setTipoEvento(eventoPartidoDto.getTipoEvento());

	    eventoPartidoEntidad.setMinuto(eventoPartidoDto.getMinuto());

	    return eventoPartidoEntidad;
	}

	
	   /**
     * Método que obtiene un evento de partido por su ID.
     * 
     * @param idEventoPartido el ID del evento
     * @return el DTO del evento null si no se encuentra
     */
	public EventoPartidoDto obtenerEventoPartidoPorId(Long idEventoPartido) {
		
			EventoPartidoEntidad eventoPartidoEntidad = eventoPartidoInterfaz.findById(idEventoPartido).orElse(null);
			return eventoPartidoEntidad != null ? mapearAEventoPartidoDto(eventoPartidoEntidad) : null;

	}
	
	/**
     * Método que mapea una lista de entidades a DTOs.
     * 
     * @return la lista de DTOs de evento partido
     */
	public ArrayList<EventoPartidoDto> mostrarEventosPartidos(){
		
		ArrayList<EventoPartidoEntidad> listaEventoPartidoEntidad = (ArrayList<EventoPartidoEntidad>) eventoPartidoInterfaz.findAll();
		ArrayList<EventoPartidoDto> listaEventoPartidoDto = new ArrayList<EventoPartidoDto>();
		for(EventoPartidoEntidad evento : listaEventoPartidoEntidad) {
			listaEventoPartidoDto.add(mapearAEventoPartidoDto(evento));
		}
		return listaEventoPartidoDto;

	}
	
	
	/**
     * Guarda un nuevo Evento de partido.
     * 
     * @param EventoPartidoDto el DTO del eventoPartido a guardar
     * @return la entidad del EventoPartido guardada
     */
	public EventoPartidoEntidad guardarEventoPartido(EventoPartidoDto eventoPartidoDto) {
		
		EventoPartidoEntidad evento = mapearAEventoPartidoEntidad(eventoPartidoDto);
		return eventoPartidoInterfaz.save(evento);
	}
	
	
	/**
     * Modifica un evento de partido existente.
     * 
     * @param id el ID del evento a modificar
     * @param eventoPartidoDto el DTO actualizado del eventoPartido
     * @return true si la modificación fue exitosa, false en caso contrario
     */
	public boolean modificarEventoPartido(String idEventoPartidoString, EventoPartidoDto eventoPartidoDto) {
		
		Long idEventoPartido = Long.parseLong(idEventoPartidoString);
		Optional<EventoPartidoEntidad> eventoOpt = eventoPartidoInterfaz.findById(idEventoPartido);
		
		if(eventoOpt.isPresent()) {
			
			EventoPartidoEntidad eventoPartidoEntidad = eventoOpt.get();
			
			Optional<ActaPartidoEntidad> actaOpt = actaPartidoInterfaz.findById(eventoPartidoDto.getActaPartidoId());
			actaOpt.ifPresent(eventoPartidoEntidad::setActaPartido);
			
			Optional<UsuarioEntidad> usuarioOpt = usuarioInterfaz.findById(eventoPartidoDto.getJugadorId());
			usuarioOpt.ifPresent(eventoPartidoEntidad::setJugador);
			
			Optional<ClubEntidad> clubOpt = clubInterfaz.findById(eventoPartidoDto.getClubId());
			clubOpt.ifPresent(eventoPartidoEntidad::setClub);
			
			Optional<EquipoTorneoEntidad> equipoOpt = equipoTorneoInterfaz.findById(eventoPartidoDto.getEquipoTorneoId());
			equipoOpt.ifPresent(eventoPartidoEntidad::setEquipoTorneo);
			
			eventoPartidoEntidad.setTipoEvento(eventoPartidoDto.getTipoEvento());
			eventoPartidoEntidad.setMinuto(eventoPartidoDto.getMinuto());
			
			eventoPartidoInterfaz.save(eventoPartidoEntidad);
			
			return true;
		 } else {
	            return false;
	        }
			
		}
		
	/**
     * Método que borra un evento de partido por su ID.
     * 
     * @param idEventoPartidoString el ID del evento como cadena
     * @return true si el evento fue borrado correctamente, false en caso contrario
     */
		public boolean borrarEventoPartido(String idEventoPartidoString) {
			boolean estaBorrado = false;
			Long idEventoPartido= Long.parseLong(idEventoPartidoString);
			EventoPartidoEntidad eventoPartidoEntidad = eventoPartidoInterfaz.findByIdEventoPartido(idEventoPartido);
			
			if(eventoPartidoEntidad == null) {
				
				 estaBorrado = false;
            } else {
                eventoPartidoInterfaz.delete(eventoPartidoEntidad);
                estaBorrado = true;
            }
			
			return estaBorrado;
		}
		
	}
	
	
	
