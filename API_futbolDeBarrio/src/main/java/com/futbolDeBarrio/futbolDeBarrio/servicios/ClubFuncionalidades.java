package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEstadisticaGlobalEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.CuentaEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.Rol;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubEstadisticaGlobalInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.CuentaInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.utilidades.Utilidades;
import com.futbolDeBarrio.futbolDeBarrio.verificacion.VerificacionEmailFuncionalidad;

import jakarta.transaction.Transactional;

@Service
/**
 * Clase que se encarga de la lógica de los métodos CRUD relacionados con los
 * clubes.
 */
public class ClubFuncionalidades {

	@Autowired
	ClubInterfaz clubInterfaz;
	@Autowired
	CuentaInterfaz cuentaInterfaz;
	@Autowired
	VerificacionEmailFuncionalidad verificacionEmailFuncionalidad;
	@Autowired
	ClubEstadisticaGlobalInterfaz clubEstadisticaGlobalInterfaz;

	/**
	 * Busca un club por su email.
	 * 
	 * @param email Email del club a buscar.
	 * @return Optional con la entidad del club si existe.
	 */
	public Optional<ClubEntidad> buscarClubPorEmail(String email) {
		return clubInterfaz.findByEmailClub(email);
	}

	/**
	 * Mapea una entidad Club a un DTO ClubDto.
	 * 
	 * @param clubEntidad Entidad Club a mapear.
	 * @return ClubDto correspondiente.
	 */
	public ClubDto mapearAClubDto(ClubEntidad clubEntidad) {
		ClubDto clubDto = new ClubDto();
		clubDto.setIdClub(clubEntidad.getIdClub());
		clubDto.setNombreClub(clubEntidad.getNombreClub());
		clubDto.setAbreviaturaClub(clubEntidad.getAbreviaturaClub());
		clubDto.setDescripcionClub(clubEntidad.getDescripcionClub());
		clubDto.setFechaCreacionClub(clubEntidad.getFechaCreacionClub());
		clubDto.setFechaFundacionClub(clubEntidad.getFechaFundacionClub());
		clubDto.setLocalidadClub(clubEntidad.getLocalidadClub());
		clubDto.setPaisClub(clubEntidad.getPaisClub());
		clubDto.setPasswordClub(null);
		if (clubEntidad.getLogoClub() != null) {
			String imagenBase64 = Base64.getEncoder().encodeToString(clubEntidad.getLogoClub());
			clubDto.setLogoClub(imagenBase64);
		}
		clubDto.setEmailClub(clubEntidad.getEmailClub());
		clubDto.setTelefonoClub(clubEntidad.getTelefonoClub());
		return clubDto;
	}

	/**
	 * Mapea un DTO ClubDto a una entidad ClubEntidad.
	 * 
	 * @param clubDto DTO del club a mapear.
	 * @return ClubEntidad correspondiente.
	 */
	public ClubEntidad mapearADtoAEntidad(ClubDto clubDto) {
		ClubEntidad clubEntidad = new ClubEntidad();
		clubEntidad.setIdClub(clubDto.getIdClub());
		clubEntidad.setNombreClub(clubDto.getNombreClub());
		clubEntidad.setAbreviaturaClub(clubDto.getAbreviaturaClub());
		clubEntidad.setDescripcionClub(clubDto.getDescripcionClub());
		clubEntidad.setFechaCreacionClub(clubDto.getFechaCreacionClub());
		clubEntidad.setFechaFundacionClub(clubDto.getFechaFundacionClub());
		clubEntidad.setLocalidadClub(clubDto.getLocalidadClub());
		clubEntidad.setPaisClub(clubDto.getPaisClub());
		clubEntidad.setPasswordClub(clubDto.getPasswordClub());
		if (clubDto.getLogoClub() != null) {
			byte[] imagenBytes = Base64.getDecoder().decode(clubDto.getLogoClub());
			clubEntidad.setLogoClub(imagenBytes);
		}
		clubEntidad.setEmailClub(clubDto.getEmailClub());
		clubEntidad.setTelefonoClub(clubDto.getTelefonoClub());
		return clubEntidad;
	}

	/**
	 * Obtiene todos los clubes en formato DTO.
	 * 
	 * @return Lista de ClubDto.
	 */
	public List<ClubDto> obtenerClubesDto() {
	    List<ClubEntidad> clubesEntidad = (List<ClubEntidad>) clubInterfaz.findAll();
	    List<ClubDto> clubesDto = new ArrayList<>();
	    for (ClubEntidad club : clubesEntidad) {
	        clubesDto.add(mapearAClubDto(club));
	    }
	    return clubesDto;
	}


	/**
	 * Obtiene un ClubDto por su ID.
	 * 
	 * @param idClub ID del club.
	 * @return ClubDto correspondiente o null si no existe.
	 */
	public ClubDto obtenerClubDtoPorId(Long idClub) {
		ClubEntidad clubEntidad = clubInterfaz.findById(idClub).orElse(null);
		return clubEntidad != null ? mapearAClubDto(clubEntidad) : null;
	}

	/**
	 * Guarda un nuevo club en la base de datos.
	 * 
	 * @param clubDto DTO del club a guardar.
	 * @return ClubEntidad guardado.
	 * @throws IllegalArgumentException si el email ya existe o la contraseña es
	 *                                  inválida.
	 */
    @Transactional
	public ClubEntidad guardarClub(ClubDto clubDto) {
		if (clubDto.getEmailClub() == null || clubDto.getEmailClub().isEmpty()) {
			throw new IllegalArgumentException("El email del club es obligatorio.");
		}

		// Validar email existente
		cuentaInterfaz.findByEmailAndRol(clubDto.getEmailClub(), Rol.Club).ifPresent(c -> {
			throw new IllegalArgumentException("El email ya está en uso.");
		});

		if (clubDto.getPasswordClub() == null || clubDto.getPasswordClub().isEmpty()) {
			throw new IllegalArgumentException("La contraseña del club no puede ser nula o vacía.");
		}

		// 1️⃣ Crear y guardar Cuenta
		CuentaEntidad cuenta = new CuentaEntidad();
		cuenta.setEmail(clubDto.getEmailClub());
		cuenta.setPassword(Utilidades.encriptarContrasenya(clubDto.getPasswordClub()));
		cuenta.setRol(Rol.Club);
		cuenta.setEmailVerificado(false);
		cuenta.setFechaCreacion(LocalDateTime.now());
		cuenta = cuentaInterfaz.save(cuenta);

		// 2️⃣ Crear Club y asociar Cuenta
		ClubEntidad clubEntidad = mapearADtoAEntidad(clubDto);
		clubEntidad.setCuenta(cuenta);
		clubEntidad.setPasswordClub(Utilidades.encriptarContrasenya(clubDto.getPasswordClub()));
		clubEntidad.setFechaCreacionClub(LocalDateTime.now().toString());

		clubEntidad = clubInterfaz.save(clubEntidad);

		// 3️⃣ Crear estadísticas globales iniciales para el club
		ClubEstadisticaGlobalEntidad estadistica = new ClubEstadisticaGlobalEntidad();
		estadistica.setClubGlobal(clubEntidad);
		estadistica.setPartidosJugadosGlobal(0);
		estadistica.setGanadosGlobal(0);
		estadistica.setPerdidosGlobal(0);
		estadistica.setGolesFavorGlobal(0);
		estadistica.setGolesContraGlobal(0);
		clubEstadisticaGlobalInterfaz.save(estadistica);

		verificacionEmailFuncionalidad.generarYEnviarToken(cuenta);

		return clubEntidad;
	}

	/**
	 * Modifica un club existente en la base de datos.
	 * 
	 * @param id      ID del club como String.
	 * @param clubDto DTO con los datos a modificar.
	 * @return true si fue modificado correctamente, false en caso contrario.
	 */
	public boolean modificarClub(String id, ClubDto clubDto) {
		boolean esModificado = false;
		try {
			Long idClub = Long.parseLong(id);
			ClubEntidad club = clubInterfaz.findByIdClub(idClub);

			if (club != null) {
				club.setNombreClub(clubDto.getNombreClub());
				club.setAbreviaturaClub(clubDto.getAbreviaturaClub());
				club.setDescripcionClub(clubDto.getDescripcionClub());
				club.setFechaCreacionClub(clubDto.getFechaCreacionClub());
				club.setFechaFundacionClub(clubDto.getFechaFundacionClub());
				club.setLocalidadClub(clubDto.getLocalidadClub());
				club.setPaisClub(clubDto.getPaisClub());
				if (clubDto.getLogoClub() != null) {
					byte[] imagenBytes = Base64.getDecoder().decode(clubDto.getLogoClub());
					club.setLogoClub(imagenBytes);
				}
				club.setEmailClub(clubDto.getEmailClub());
				club.setTelefonoClub(clubDto.getTelefonoClub());
				if (clubDto.getPasswordClub() != null && !clubDto.getPasswordClub().isEmpty()) {
					if (!Utilidades.verificarContrasena(clubDto.getPasswordClub(), club.getPasswordClub())) {
						club.setPasswordClub(Utilidades.encriptarContrasenya(clubDto.getPasswordClub()));
					}
				}
				clubInterfaz.save(club);
				esModificado = true;
			}

		} catch (NumberFormatException nfe) {
			// Error de formato del ID
		} catch (Exception e) {
			// Error general
		}
		return esModificado;
	}

	/**
	 * Método para actualizar solo el campo esPremium de un club.
	 *
	 * @param idClub      ID del club a actualizar.
	 * @param nuevoEstado Valor del campo esPremium (true o false).
	 * @return true si se actualizó correctamente, false si no se encontró el club.
	 */
	public boolean marcarClubPremium(Long idClub, String emailClubLogueado) {
		try {
			Optional<ClubEntidad> clubOpt = clubInterfaz.findById(idClub);

			if (clubOpt.isPresent()) {
				ClubEntidad club = clubOpt.get();

				// ✅ Validar que el email logueado coincide con el club
				if (!club.getEmailClub().equals(emailClubLogueado)) {
					return false;
				}

				club.setEsPremium(true);
				clubInterfaz.save(club);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Elimina un club por su ID.
	 * 
	 * @param idClubString ID del club en formato String.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean borrarClub(String idClubString) {
		boolean estaBorrado = false;
		try {
			Long idClub = Long.parseLong(idClubString);
			ClubEntidad clubEntidad = clubInterfaz.findByIdClub(idClub);

			if (clubEntidad != null) {
				clubInterfaz.delete(clubEntidad);
				estaBorrado = true;
			}
		} catch (NumberFormatException nfe) {
			// Error de formato
		} catch (Exception e) {
			// Error general
		}
		return estaBorrado;
	}
}
