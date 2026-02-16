package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.InstalacionDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.CuentaEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.Rol;
import com.futbolDeBarrio.futbolDeBarrio.logs.Logs;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.CuentaInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.InstalacionInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.TokenVerificacionEmailInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.utilidades.Utilidades;
import com.futbolDeBarrio.futbolDeBarrio.verificacion.VerificacionEmailFuncionalidad;

import jakarta.transaction.Transactional;

/**
 * Clase que se encarga de la logica interna de los metodos CRUD de isntalacion
 */
@Service
public class InstalacionFuncionalidades {

	@Autowired
	InstalacionInterfaz instalacionInterfaz;
	@Autowired
    CuentaInterfaz cuentaInterfaz;
    @Autowired
    VerificacionEmailFuncionalidad verificacionEmailFuncionalidad;
    @Autowired
	TokenVerificacionEmailInterfaz tokenVerificacionEmailInterfaz;
    

	/**
	 * Método que busca una instalación por su email.
	 *
	 * @param email El email de la instalación a buscar.
	 * @return Un {@link Optional} que contiene la instalación si se encuentra, o
	 *         vacío si no existe.
	 */
	public Optional<InstalacionEntidad> buscarInstalacionPorEmail(String email) {
		return instalacionInterfaz.findByEmailInstalacion(email);
	}

	/**
	 * Método que mapea una entidad a DTO.
	 *
	 * @param instalacionEntidad La entidad de la instalación a mapear.
	 * @return El DTO correspondiente a la instalación.
	 */
	public InstalacionDto mapearAInstalacionDto(InstalacionEntidad instalacionEntidad) {
		InstalacionDto instalacionDto = new InstalacionDto();
		instalacionDto.setIdInstalacion(instalacionEntidad.getIdInstalacion());
		instalacionDto.setNombreInstalacion(instalacionEntidad.getNombreInstalacion());
		instalacionDto.setDireccionInstalacion(instalacionEntidad.getDireccionInstalacion());
		instalacionDto.setTelefonoInstalacion(instalacionEntidad.getTelefonoInstalacion());
		instalacionDto.setEmailInstalacion(instalacionEntidad.getEmailInstalacion());
		instalacionDto.setTipoCampo1(instalacionEntidad.getTipoCampo1());
		instalacionDto.setTipoCampo2(instalacionEntidad.getTipoCampo2());
		instalacionDto.setTipoCampo3(instalacionEntidad.getTipoCampo3());
		instalacionDto.setServiciosInstalacion(instalacionEntidad.getServiciosInstalacion());
		instalacionDto.setPasswordInstalacion(null);
		instalacionDto.setEstadoInstalacion(instalacionEntidad.getEstadoInstalacion());
		if (instalacionEntidad.getImagenInstalacion() != null) {
			String imagenBase64 = Base64.getEncoder().encodeToString(instalacionEntidad.getImagenInstalacion());
			instalacionDto.setImagenInstalacion(imagenBase64);
		}

		return instalacionDto;
	}

	/**
	 * Método que mapea un DTO a entidad.
	 *
	 * @param instalacionDto El DTO de la instalación a mapear.
	 * @return La entidad correspondiente a la instalación.
	 */
	private InstalacionEntidad mapearADtoAEntidad(InstalacionDto instalacionDto) {
		InstalacionEntidad instalacionEntidad = new InstalacionEntidad();
		instalacionEntidad.setIdInstalacion(instalacionDto.getIdInstalacion());
		instalacionEntidad.setNombreInstalacion(instalacionDto.getNombreInstalacion());
		instalacionEntidad.setDireccionInstalacion(instalacionDto.getDireccionInstalacion());
		instalacionEntidad.setTelefonoInstalacion(instalacionDto.getTelefonoInstalacion());
		instalacionEntidad.setEmailInstalacion(instalacionDto.getEmailInstalacion());
		instalacionEntidad.setTipoCampo1(instalacionDto.getTipoCampo1());
		instalacionEntidad.setTipoCampo2(instalacionDto.getTipoCampo2());
		instalacionEntidad.setTipoCampo3(instalacionDto.getTipoCampo3());
		instalacionEntidad.setServiciosInstalacion(instalacionDto.getServiciosInstalacion());
		instalacionEntidad.setPasswordInstalacion(Utilidades.encriptarContrasenya(instalacionDto.getPasswordInstalacion()));
		instalacionEntidad.setEstadoInstalacion(instalacionDto.getEstadoInstalacion());
		if (instalacionDto.getImagenInstalacion() != null) {
			byte[] imagenBytes = Base64.getDecoder().decode(instalacionDto.getImagenInstalacion());
			instalacionEntidad.setImagenInstalacion(imagenBytes);
		}

		return instalacionEntidad;
	}

	/**
	 * Método que obtiene todas las instalaciones en formato DTO.
	 *
	 * @return Una lista de DTOs de todas las instalaciones.
	 */
	public List<InstalacionDto> obtenerInstalacionesDto() {
		List<InstalacionEntidad> instalacionesEntidad = (List<InstalacionEntidad>) instalacionInterfaz.findAll();
		List<InstalacionDto> instalacionesDto = new ArrayList<>();
		for (InstalacionEntidad instalacion : instalacionesEntidad) {
			instalacionesDto.add(mapearAInstalacionDto(instalacion));
		}
		return instalacionesDto;
	}

	/**
	 * Método que obtiene una instalación por su ID en formato DTO.
	 *
	 * @param idInstalacion El ID de la instalación a buscar.
	 * @return El DTO de la instalación, o null si no se encuentra.
	 */
	public InstalacionDto obtenerInstalacionDtoPorId(
	        Long idInstalacion,
	        String emailLogueado) {

	    InstalacionEntidad instalacion = instalacionInterfaz.findById(idInstalacion)
	            .orElseThrow(() -> new RuntimeException("Instalación no encontrada"));

	    // 🔐 AUTORIZACIÓN DE NEGOCIO
	    if (!instalacion.getEmailInstalacion().equals(emailLogueado)) {
	        throw new SecurityException("No autorizado para acceder a esta instalación");
	    }

	    return mapearAInstalacionDto(instalacion);
	}

	/**
	 * Obtiene un InstalacionDto por su ID sin validación de email.
	 * Este método es útil para reconstrucción de sesión desde token persistente.
	 *
	 * @param idInstalacion El ID de la instalación
	 * @return InstalacionDto o null si no existe
	 */
	public InstalacionDto obtenerInstalacionDtoPorId(Long idInstalacion) {
	    return instalacionInterfaz.findById(idInstalacion)
	            .map(this::mapearAInstalacionDto)
	            .orElse(null);
	}

	/**
	 * Obtiene un InstalacionDto por su email.
	 *
	 * @param email Email de la instalación
	 * @return InstalacionDto si existe, null si no
	 */
	public InstalacionDto obtenerInstalacionDtoPorEmail(String email) {
	    return instalacionInterfaz.findByEmailInstalacion(email)
	            .map(this::mapearAInstalacionDto)
	            .orElse(null);
	}

	

	/**
	 * Método que guarda una nueva instalación en la base de datos, recibiendo un
	 * DTO.
	 *
	 * @param instalacionDto El DTO que contiene los datos de la instalación a
	 *                       guardar.
	 * @return La entidad de la instalación guardada.
	 * @throws IllegalArgumentException Si el email ya está en uso o si la
	 *                                  contraseña es nula o vacía.
	 */
	@Transactional
	public InstalacionEntidad guardarInstalacion(InstalacionDto instalacionDto) {
	    // Validar si ya existe cuenta con ese email
	    Optional<CuentaEntidad> cuentaExistente = cuentaInterfaz.findByEmailAndRol(instalacionDto.getEmailInstalacion(), Rol.Instalacion);
	    if (cuentaExistente.isPresent()) {
	        throw new IllegalArgumentException("El correo ya está en uso");
	    }

	    if (instalacionDto.getPasswordInstalacion() == null || instalacionDto.getPasswordInstalacion().isEmpty()) {
	        throw new IllegalArgumentException("La contraseña de la instalación no puede ser nula o vacía.");
	    }

	    // Crear y guardar la cuenta
	    CuentaEntidad cuenta = new CuentaEntidad();
	    cuenta.setEmail(instalacionDto.getEmailInstalacion());
	    cuenta.setPassword(Utilidades.encriptarContrasenya(instalacionDto.getPasswordInstalacion()));
	    cuenta.setRol(Rol.Instalacion);
	    cuenta.setEmailVerificado(false);
	    cuenta.setFechaCreacion(LocalDateTime.now());
	    cuenta = cuentaInterfaz.save(cuenta);

	    // Crear la instalación y asociar la cuenta
	    InstalacionEntidad instalacionEntidad = mapearADtoAEntidad(instalacionDto);
	    instalacionEntidad.setPasswordInstalacion(Utilidades.encriptarContrasenya(instalacionDto.getPasswordInstalacion()));
	    instalacionEntidad.setCuenta(cuenta);

	    instalacionInterfaz.save(instalacionEntidad);

	    verificacionEmailFuncionalidad.generarYEnviarToken(cuenta);

	    return instalacionEntidad;
	}


	/**
	 * Método que modifica una instalación en la base de datos.
	 *
	 * @param id             El ID de la instalación a modificar.
	 * @param instalacionDto El DTO con los nuevos datos de la instalación.
	 * @return true si la instalación fue modificada con éxito, false en caso
	 *         contrario.
	 */
	public boolean modificarInstalacion(String id, InstalacionDto instalacionDto) {
		boolean esModificado = false;
		try {
			Long idInstalacion = Long.parseLong(id);
			InstalacionEntidad instalacion = instalacionInterfaz.findByIdInstalacion(idInstalacion);

			if (instalacion == null) {
				// System.out.println("El ID proporcionado no existe");
			} else {
				instalacion.setNombreInstalacion(instalacionDto.getNombreInstalacion());
				instalacion.setDireccionInstalacion(instalacionDto.getDireccionInstalacion());
				instalacion.setTelefonoInstalacion(instalacionDto.getTelefonoInstalacion());
				instalacion.setEmailInstalacion(instalacionDto.getEmailInstalacion());
				instalacion.setTipoCampo1(instalacionDto.getTipoCampo1());
				instalacion.setTipoCampo2(instalacionDto.getTipoCampo2());
				instalacion.setTipoCampo3(instalacionDto.getTipoCampo3());
				instalacion.setServiciosInstalacion(instalacionDto.getServiciosInstalacion());
				instalacion.setEstadoInstalacion(instalacionDto.getEstadoInstalacion());
				if (instalacionDto.getPasswordInstalacion() != null
				        && !instalacionDto.getPasswordInstalacion().isEmpty()) {
				    instalacion.setPasswordInstalacion(
				        Utilidades.encriptarContrasenya(instalacionDto.getPasswordInstalacion())
				    );
				}
				if (instalacionDto.getImagenInstalacion() != null) {
					byte[] imagenBytes = Base64.getDecoder().decode(instalacionDto.getImagenInstalacion());
					instalacion.setImagenInstalacion(imagenBytes);
				}

				instalacionInterfaz.save(instalacion);
				// System.out.println("La instalación: " + instalacion.getNombreInstalacion() +
				// " ha sido modificada.");
				esModificado = true;
			}

		} catch (NumberFormatException nfe) {
			// System.out.println("Error: El ID proporcionado no es válido. " +
			// nfe.getMessage());
		} catch (Exception e) {
			// System.out.println("Se ha producido un error al modificar la instalación. " +
			// e.getMessage());
		}

		return esModificado;
	}

	/**
	 * Método que borra una instalación por su ID
	 * 
	 * @param idInstalacionString El ID de la instalación en formato String
	 * @return true si la instalación fue borrada, false si no se encontró
	 */
	@Transactional
	public boolean borrarInstalacion(String idInstalacionString) {
	    try {
	        Long idInstalacion = Long.parseLong(idInstalacionString);
	        InstalacionEntidad instalacion = instalacionInterfaz.findByIdInstalacion(idInstalacion);

	        if (instalacion == null) return false;

	        CuentaEntidad cuenta = instalacion.getCuenta();
	        if (cuenta != null) {
	            tokenVerificacionEmailInterfaz.deleteByCuenta(cuenta);
	        }

	        instalacionInterfaz.delete(instalacion);

	        Logs.ficheroLog("Instalación eliminada: " + instalacion.getNombreInstalacion());
	        return true;

	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	        return false;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}


}