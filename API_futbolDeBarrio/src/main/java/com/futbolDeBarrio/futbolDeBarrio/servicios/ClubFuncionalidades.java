package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.utilidades.Utilidades;

@Service
public class ClubFuncionalidades {

	@Autowired
	ClubInterfaz clubInterfaz;


	public Optional<ClubEntidad> buscarClubPorEmail(String email) {
		return clubInterfaz.findByEmailClub(email);
	}

	
	/**
	 * Método que mapea de entidad a DTO
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
		clubDto.setPasswordClub(clubEntidad.getPasswordClub());
		if (clubEntidad.getLogoClub() != null) {
			String imagenBase64 = Base64.getEncoder().encodeToString(clubEntidad.getLogoClub());
			clubDto.setLogoClub(imagenBase64);
		}
		clubDto.setEmailClub(clubEntidad.getEmailClub());
		clubDto.setTelefonoClub(clubEntidad.getTelefonoClub());
		return clubDto;
	}

	/**
	 * Método que mapea de DTO a entidad
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
	 * Método que obtiene la lista de clubes en DTO
	 */
	public List<ClubDto> obtenerClubesDto() {
		List<ClubEntidad> clubesEntidad = (List<ClubEntidad>) clubInterfaz.findAll();
		List<ClubDto> clubesDto = new ArrayList<>();
		for (ClubEntidad club : clubesEntidad) {
			clubesDto.add(mapearAClubDto(club));
		}
		return clubesDto;
	}

	public ClubDto obtenerClubDtoPorId(Long idClub) {
		ClubEntidad clubEntidad = clubInterfaz.findByIdClub(idClub);
		return clubEntidad != null ? mapearAClubDto(clubEntidad) : null;
	}

	/**
	 * Método para guardar un club en la base de datos, recibiendo un DTO
	 */
	public ClubEntidad guardarClub(ClubDto clubDto) {
		// Verificamos si el email ya existe en la base de datos
		Optional<ClubEntidad> clubExistente = clubInterfaz.findByEmailClub(clubDto.getEmailClub());
		if (clubExistente.isPresent()) {
			// Lanzamos la excepción con el mensaje de error
			throw new IllegalArgumentException("El email proporcionado ya está siendo utilizado por otro club.");
		}

		// Mapeamos el DTO a una entidad
		ClubEntidad clubEntidad = mapearADtoAEntidad(clubDto);

		// Verificamos que la contraseña no sea nula ni vacía
		if (clubDto.getPasswordClub() == null || clubDto.getPasswordClub().isEmpty()) {
			throw new IllegalArgumentException("La contraseña del club no puede ser nula o vacía.");
		}

		// Encriptamos la contraseña antes de guardar
		clubEntidad.setPasswordClub(Utilidades.encriptarContrasenya(clubDto.getPasswordClub()));

		// Guardamos la entidad en la base de datos
		return clubInterfaz.save(clubEntidad);
	}

	/**
	 * Método que modifica un club en la base de datos
	 */
	public boolean modificarClub(String id, ClubDto clubDto) {
	    boolean esModificado = false;
	    try {
	        Long idClub = Long.parseLong(id);
	        ClubEntidad club = clubInterfaz.findByIdClub(idClub);

	        if (club == null) {
	            System.out.println("El ID proporcionado no existe");
	        } else {
	            // Actualización de los demás campos
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

	            // Comprobamos si la contraseña es diferente y no vacía
	            if (clubDto.getPasswordClub() != null && !clubDto.getPasswordClub().isEmpty()) {
	                // Verificamos si la contraseña nueva es diferente de la actual (que está encriptada)
	                if (!Utilidades.verificarContrasena(clubDto.getPasswordClub(), club.getPasswordClub())) {
	                    // Solo encriptamos la nueva contraseña si es diferente
	                    club.setPasswordClub(Utilidades.encriptarContrasenya(clubDto.getPasswordClub()));
	                } else {
	                    // Si la contraseña es la misma, no se modifica
	                    System.out.println("La contraseña ingresada coincide con la actual. No se modifica.");
	                }
	            }

	            // Guardamos los cambios en la base de datos
	            clubInterfaz.save(club);
	            esModificado = true;
	        }

	    } catch (NumberFormatException nfe) {
	        System.out.println("Error: El ID proporcionado no es válido. " + nfe.getMessage());
	    } catch (Exception e) {
	        System.out.println("Se ha producido un error al modificar el club. " + e.getMessage());
	    }

	    return esModificado;
	}


	/**
	 * Método que borra un club por su ID
	 */
	public boolean borrarClub(String idClubString) {
		boolean estaBorrado = false;
		try {
			Long idClub = Long.parseLong(idClubString);
			ClubEntidad clubEntidad = clubInterfaz.findByIdClub(idClub);

			if (clubEntidad == null) {
				estaBorrado = false;
				System.out.println("El id del Club no existe");
			} else {
				clubInterfaz.delete(clubEntidad);
				estaBorrado = true;
				System.out.println("El club " + clubEntidad.getNombreClub() + " ha sido borrado con éxito");
			}

		} catch (NumberFormatException nfe) {
			System.out.println("Error: El ID proporcionado no es válido. " + nfe.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido un error al borrar el club. " + e.getMessage());
		}

		return estaBorrado;
	}
}
