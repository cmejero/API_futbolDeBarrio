package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.UsuarioDto;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.UsuarioInterfaz;

/**
 * Clase que se encarga de la lógica interna de los métodos CRUD para Usuario
 */
@Service
public class UsuarioFuncionalidades {

	@Autowired
	UsuarioInterfaz usuarioInterfaz;

	/**
	 * Método que se encarga de mostrar listado de todos los usuarios
	 */
	public ArrayList<UsuarioDto> listaUsuarios() {
		return (ArrayList<UsuarioDto>) usuarioInterfaz.findAll();
	}

	/**
	 * Método que se encarga de guardar un usuario
	 */
	public UsuarioDto guardarUsuario(UsuarioDto usuarioDto) {
		usuarioDto.setPasswordUsuario(encriptarContrasenya(usuarioDto.getPasswordUsuario()));
		return usuarioInterfaz.save(usuarioDto);
	}

	/**
	 * Método que se encarga de eliminar un usuario por su ID
	 */
	public boolean borrarUsuario(String idUsuarioString) {

		boolean estaBorrado = false;
		try {
			Long idUsuario = Long.parseLong(idUsuarioString);
			UsuarioDto usuarioDto = usuarioInterfaz.findByIdUsuario(idUsuario);

			boolean coincide = false;

			if (usuarioDto == null) {
				estaBorrado = false;
				System.out.println("El id del Usuario no existe");
			}

			if (idUsuario == usuarioDto.getIdUsuario()) {

				coincide = true;
			}

			if (coincide) {

				usuarioInterfaz.delete(usuarioDto);
				estaBorrado = true;
				System.out.println("El usuario " + usuarioDto.getNombreCompletoUsuario() + " ha sido borrado con exito");
			}
		} catch (NumberFormatException nfe) {
	        System.out.println("Error: El ID proporcionado no es válido. " + nfe.getMessage());
	    } catch (Exception e) {
	        System.out.println("Se ha producido un error al modificar el usuario. " + e.getMessage());
	    }

		return estaBorrado;
	}
	
	/**
	 * Método que se encarga de modificar los campos del usuario
	 */
	public boolean modificarUsuario(String id, UsuarioDto usuarioDto) {
	    boolean esModificado = false;

	    try {
	        Long idUsuario = Long.parseLong(id);
	        UsuarioDto usuario = usuarioInterfaz.findByIdUsuario(idUsuario);

	        if (usuario == null) {
	            System.out.println("El ID proporcionado no existe");
	        } else {
	            usuario.setNombreCompletoUsuario(usuarioDto.getNombreCompletoUsuario());
	   
	            usuario.setFechaNacimientoUsuario(usuarioDto.getFechaNacimientoUsuario());
	            usuario.setEmailUsuario(usuarioDto.getEmailUsuario());
	            usuario.setTelefonoUsuario(usuarioDto.getTelefonoUsuario());
	            usuario.setPasswordUsuario(encriptarContrasenya(usuario.getPasswordUsuario()));
	            usuario.setRolJugador(usuarioDto.getRolJugador());
	            usuario.setDescripcionUsuario(usuarioDto.getDescripcionUsuario());
	            usuario.setImagenUsuario(usuarioDto.getImagenUsuario());
	            usuario.setEstadoUsuario(usuarioDto.getEstadoUsuario());
	            usuario.setRolUsuario(usuarioDto.getRolUsuario());

	            usuarioInterfaz.save(usuario);
	            System.out.println("El usuario: " + usuario.getNombreCompletoUsuario() + " ha sido modificado.");
	            esModificado = true;
	        }

	    } catch (NumberFormatException nfe) {
	        System.out.println("Error: El ID proporcionado no es válido. " + nfe.getMessage());
	    } catch (Exception e) {
	        System.out.println("Se ha producido un error al modificar el usuario. " + e.getMessage());
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
