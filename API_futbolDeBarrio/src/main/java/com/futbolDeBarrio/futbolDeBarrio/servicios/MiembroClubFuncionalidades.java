package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.MiembroClubDto;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.MiembroClubInterfaz;

/**
 * Clase que se encarga de la logica interna de los metodos CRUD
 */
@Service
public class MiembroClubFuncionalidades {

	@Autowired
	MiembroClubInterfaz miembroClubInterfaz;
	
	/**
	 * Metodo que se encarga de mostrar listado de los miembros de cada club
	 */
	public ArrayList<MiembroClubDto> listaMiembroClub(){
		return (ArrayList<MiembroClubDto>) miembroClubInterfaz.findAll();
	}
	
	/**
	 * Metodo que se encarga de guardar un miembro en un club
	 */
	public MiembroClubDto guardarMiembroClub(MiembroClubDto miembroClubDto) {
		return miembroClubInterfaz.save(miembroClubDto);
	}
	
	/**
	 * Metodo que se encarga de eliminar un miembro de un club
	 */
	public boolean eliminarMiembroClub(String idMiembroClubString) {
		
		boolean estaBorrado = false;
		try {
		Long idMiembroClub = Long.parseLong(idMiembroClubString);
		boolean coincide = false;
		MiembroClubDto miembroClubDto = miembroClubInterfaz.findByIdMiembroClub(idMiembroClub);
		
		if(miembroClubDto == null) {
			System.out.println("El ID introducido no existe");
			estaBorrado= false;
		}
		
		if(idMiembroClub == miembroClubDto.getIdMiembroClub()) {
			
			coincide = true;
		}
		
		if(coincide) {
			
			System.out.println("el miembro del club se ha eliminado con exito");
			miembroClubInterfaz.delete(miembroClubDto);
			estaBorrado= true;
		}
		
		}catch(NumberFormatException nfe) {
			System.out.println("Error: el ID proporcionado no es valido " + nfe.getMessage());
			return false;
		} catch (Exception e) {

			System.out.println("Se ha producido un error " + e.getMessage());
			return false;
		}
		return estaBorrado;
	}
	
	/**
	 * Metodo que se encarga de modificar los campos de la tabla miembro_club
	 */
	public boolean modificarMiembroClub(String idMiembroClubString, MiembroClubDto miembroClub) {
		
		boolean estaModificado= false;
		try {
		Long idMiembroClub = Long.parseLong(idMiembroClubString);
		MiembroClubDto miembroClubDto = miembroClubInterfaz.findByIdMiembroClub(idMiembroClub);
		
		if(miembroClub == null) {
			System.out.println("El ID introducido no existe");
			estaModificado = false;
		}
		else {
			miembroClubDto.setClub(miembroClub.getClub());
			miembroClubDto.setFechaAltaUsuario(miembroClub.getFechaAltaUsuario());
			miembroClubDto.setFechaBajaUsuario(miembroClub.getFechaBajaUsuario());
			miembroClubDto.setUsuario(miembroClub.getUsuario());;
			
			miembroClubInterfaz.save(miembroClubDto);
			System.out.println("Se ha modificado miembro_club con exito");
			estaModificado = true;
		}
		}catch(NumberFormatException nfe) {
			System.out.println("Error: el ID proporcionado no es valido " + nfe.getMessage());
			return false;
		} catch (Exception e) {

			System.out.println("Se ha producido un error " + e.getMessage());
			return false;
		}
		return estaModificado;
	}
}
