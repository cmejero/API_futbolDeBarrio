package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.InstalacionDto;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.InstalacionInterfaz;

/**
 * Clase que se encarga de la logica interna de los metodos CRUD
 */
@Service
public class InstalacionFuncionalidades {

	@Autowired
	InstalacionInterfaz instalacionInterfaz;
	
	/**
	 * Metodo que se encarga de mostrar listado de las instalaciones
	 */
	public ArrayList<InstalacionDto> listaInstalacion(){
		return (ArrayList<InstalacionDto>) instalacionInterfaz.findAll();
		
	}
	
	/**
	 * Metodo que se encarga de guardar una nueva instalacion
	 */
	public InstalacionDto guardarInstalacion(InstalacionDto instalacionDto) {
		instalacionDto.setPasswordInstalacion(encriptarContrasenya(instalacionDto.getPasswordInstalacion()));
		return instalacionInterfaz.save(instalacionDto);
	}
	
	/**
	 * Metodo que se encarga de eliminar una instalacion
	 */
	public Boolean borrarInstalacion(String idInstalacionString) {
		
		try {
		boolean estaBorrado= false;
		Long idInstalacion = Long.parseLong(idInstalacionString);
		boolean coincide = false;
		
		InstalacionDto instalacionDto = instalacionInterfaz.findByIdInstalacion(idInstalacion);
		
		if(instalacionDto == null) {
			
			System.out.println("El id introducido de la instalacion no existe");
			estaBorrado = false;
		}
		
		if(idInstalacion == instalacionDto.getIdInstalacion()) {
			coincide = true;
		}
		
		if(coincide) {
			
			System.out.println("La instalacion " + instalacionDto.getNombreInstalacion() + " ha sido eliminada con exito");
			instalacionInterfaz.delete(instalacionDto);
			estaBorrado = true;	
		}
		return estaBorrado;
		
		}catch (NumberFormatException nfe) {
			System.out.println("Error: el ID proporcionado no es valido " + nfe.getMessage());
			return false;
		} catch (Exception e) {

			System.out.println("Se ha producido un error " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * Metodo que se encarga de modificar los campos de una instalacion
	 */
	public Boolean modificarInstalacion(String idInstalacionString, InstalacionDto instalacion) {
		
		boolean estaModificado = false;
		try {
		Long idInstalacion = Long.parseLong(idInstalacionString);
		InstalacionDto instalacionDto = instalacionInterfaz.findByIdInstalacion(idInstalacion);
		
		if(instalacionDto == null) {
			
			System.out.println("El id introducido de la instalcion no existe");
			estaModificado = false;
		}else {
			instalacionDto.setDescripcion(instalacion.getDescripcion());
			instalacionDto.setDireccionInstalacion(instalacion.getDireccionInstalacion());
			instalacionDto.setEmailInstalacion(instalacion.getEmailInstalacion());
			instalacionDto.setEstadoInstalacion(instalacion.getEstadoInstalacion());
			instalacionDto.setImagenInstalacion(instalacion.getImagenInstalacion());
			instalacionDto.setNombreInstalacion(instalacion.getNombreInstalacion());
			instalacionDto.setPasswordInstalacion(encriptarContrasenya(instalacion.getPasswordInstalacion()));
			instalacionDto.setServiciosInstalacion(instalacion.getServiciosInstalacion());
			instalacionDto.setTelefonoInstalacion(instalacion.getTelefonoInstalacion());
			instalacionDto.setTipoCampo1(instalacion.getTipoCampo1());
			instalacionDto.setTipoCampo2(instalacion.getTipoCampo2());
			instalacionDto.setTipoCampo3(instalacion.getTipoCampo3());
			
			instalacionInterfaz.save(instalacionDto);
			System.out.println("Se ha modificado "+ instalacionDto.getNombreInstalacion() + " con exito");
			estaModificado = true;
			
		}
		
		}catch (NumberFormatException nfe) {
			System.out.println("Error: el ID proporcionado no es valido " + nfe.getMessage());
			return false;
		} catch (Exception e) {

			System.out.println("Se ha producido un error " + e.getMessage());
			return false;
		}
		return estaModificado;
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
