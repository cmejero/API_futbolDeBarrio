package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubDto;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubInterfaz;

/**
 * Clase que se encarga de la logica interna de los metodos CRUD
 */
@Service
public class ClubFuncionalidades {

	@Autowired
	ClubInterfaz clubInterfaz;

	/**
	 * Metodo que se encarga de mostrar listado de los clubes
	 */
	public ArrayList<ClubDto> listaClubes() {

		return (ArrayList<ClubDto>) clubInterfaz.findAll();
	}

	/**
	 * Metodo que se encarga de guardar un nuevo club
	 */
	public ClubDto guardarClub(ClubDto club) {

		club.setPasswordClub(encriptarContrasenya(club.getPasswordClub()));

		return clubInterfaz.save(club);
	}

	/**
	 * Metodo que se encarga de eliminar un club
	 */
	public Boolean borrarClub(String id) {
		try {
			long idClub = Long.parseLong(id);
			Boolean estaBorrado = false;
			Boolean coincide = false;

			ClubDto clubDto = clubInterfaz.findByIdClub(idClub);

			if (clubDto == null) {
				System.out.println("El id introducido no existe");
				estaBorrado = false;
			}

			if (idClub == clubDto.getIdClub()) {
				coincide = true;
			}

			if (coincide == true) {

				clubInterfaz.delete(clubDto);
				System.out.println("El club: " + clubDto.getNombreClub() + " ha sido eliminado correctamente");
				estaBorrado = true;
			}
			return estaBorrado;

		} catch (NumberFormatException nfe) {
			System.out.println("Error: el ID proporcionado no es valido " + nfe.getMessage());
			return false;

		} catch (Exception e) {
			System.out.println("Error: Se ha porducido un error al eliminar el club " + e.getMessage());
			return false;
		}

	}

	/**
	 * Metodo que se encarga de modificar los campos de un club
	 */
	public boolean modificarClub(String id, ClubDto clubDto) {
	    boolean esModificado = false;

	    try {
	        Long idClub = Long.parseLong(id);
	        ClubDto club = clubInterfaz.findByIdClub(idClub);

	        if (club == null) {
	            System.out.println("El ID proporcionado no existe");
	        } else {
	            club.setNombreClub(clubDto.getNombreClub());
	            club.setDescripcionClub(clubDto.getDescripcionClub());
	            club.setEmailClub(clubDto.getEmailClub());
	            club.setFechaCreacionClub(clubDto.getFechaCreacionClub());
	            club.setFechaFundacionClub(clubDto.getFechaFundacionClub());	            
	            club.setLocalidadClub(clubDto.getLocalidadClub());
	            club.setInstalacion(clubDto.getInstalacion());
	            club.setLogoClub(clubDto.getLogoClub());
	            club.setPaisClub(clubDto.getPaisClub());
	            club.setPasswordClub(encriptarContrasenya(clubDto.getPasswordClub()));
	            club.setTelefonoClub(clubDto.getTelefonoClub());

	            clubInterfaz.save(club);
	            System.out.println("El club: " + club.getNombreClub() + " ha sido modificado.");
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
	 * Metodo que se encarga de encriptar una contraseña
	 */
	public String encriptarContrasenya(String contraseña) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(contraseña.getBytes());
			StringBuilder hexString = new StringBuilder();

			for (byte b : hash) {
				String hex = String.format("%02x", b); // Formato hexadecimal simplificado
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

	}
}
