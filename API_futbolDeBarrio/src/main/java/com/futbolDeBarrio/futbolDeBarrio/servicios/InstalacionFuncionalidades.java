package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.InstalacionDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.InstalacionInterfaz;

/**
 * Clase que se encarga de la logica interna de los metodos CRUD
 */
@Service
public class InstalacionFuncionalidades {

	@Autowired
	InstalacionInterfaz instalacionInterfaz;
	
	 public Optional<InstalacionEntidad> buscarInstalacionPorEmail(String email) {
	        return instalacionInterfaz.findByEmailInstalacion(email);
	    }
	 /**
     * Método que mapea de entidad a DTO
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
        instalacionDto.setPasswordInstalacion(instalacionEntidad.getPasswordInstalacion());
        instalacionDto.setEstadoInstalacion(instalacionEntidad.getEstadoInstalacion());
        instalacionDto.setImagenInstalacion(instalacionEntidad.getImagenInstalacion());
        return instalacionDto;
    }

    /**
     * Método que mapea de DTO a entidad
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
        instalacionDto.setPasswordInstalacion(instalacionEntidad.getPasswordInstalacion());
        instalacionEntidad.setEstadoInstalacion(instalacionDto.getEstadoInstalacion());
        instalacionEntidad.setImagenInstalacion(instalacionDto.getImagenInstalacion());
        return instalacionEntidad;
    }

    /**
     * Método que obtiene una lista de instalaciones en formato DTO
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
     * Método que obtiene una instalación por su ID en formato DTO
     */
    public InstalacionDto obtenerInstalacionDtoPorId(Long idInstalacion) {
        InstalacionEntidad instalacionEntidad = instalacionInterfaz.findByIdInstalacion(idInstalacion);
        return instalacionEntidad != null ? mapearAInstalacionDto(instalacionEntidad) : null;
    }

    /**
     * Método para guardar una instalación en la base de datos, recibiendo un DTO
     */
    public InstalacionEntidad guardarInstalacion(InstalacionDto instalacionDto) {
        InstalacionEntidad instalacionEntidad = mapearADtoAEntidad(instalacionDto);
        instalacionEntidad.setPasswordInstalacion(encriptarContrasenya(instalacionDto.getPasswordInstalacion()));
        return instalacionInterfaz.save(instalacionEntidad);
    }

    /**
     * Método que modifica una instalación en la base de datos
     */
    public boolean modificarInstalacion(String id, InstalacionDto instalacionDto) {
        boolean esModificado = false;
        try {
            Long idInstalacion = Long.parseLong(id);
            InstalacionEntidad instalacion = instalacionInterfaz.findByIdInstalacion(idInstalacion);

            if (instalacion == null) {
                System.out.println("El ID proporcionado no existe");
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
                instalacion.setPasswordInstalacion(encriptarContrasenya(instalacionDto.getPasswordInstalacion()));
                instalacion.setImagenInstalacion(instalacionDto.getImagenInstalacion());

                instalacionInterfaz.save(instalacion);
                System.out.println("La instalación: " + instalacion.getNombreInstalacion() + " ha sido modificada.");
                esModificado = true;
            }

        } catch (NumberFormatException nfe) {
            System.out.println("Error: El ID proporcionado no es válido. " + nfe.getMessage());
        } catch (Exception e) {
            System.out.println("Se ha producido un error al modificar la instalación. " + e.getMessage());
        }

        return esModificado;
    }

    /**
     * Método que encripta una contraseña utilizando SHA-256
     * @param contraseña La contraseña en texto plano
     * @return La contraseña encriptada en formato hexadecimal
     */
    public String encriptarContrasenya(String contraseña) {
        if (contraseña == null || contraseña.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede ser nula o vacía.");
        }
        
        // Genera el hash de la contraseña usando BCrypt
        return BCrypt.hashpw(contraseña, BCrypt.gensalt());
    }
    
    public boolean verificarContrasena(String contraseñaIngresada, String hashAlmacenado) {
        // Verifica que la contraseña ingresada coincide con el hash almacenado
        return BCrypt.checkpw(contraseñaIngresada, hashAlmacenado);
    }
    
    
    /**
     * Método que borra una instalación por su ID
     * @param idInstalacionString El ID de la instalación en formato String
     * @return true si la instalación fue borrada, false si no se encontró
     */
    public boolean borrarInstalacion(String idInstalacionString) {
        try {
            Long idInstalacion = Long.parseLong(idInstalacionString);
            if (instalacionInterfaz.existsById(idInstalacion)) {
                instalacionInterfaz.deleteById(idInstalacion);
                System.out.println("La instalación con ID " + idInstalacion + " ha sido borrada con éxito.");
                return true;
            } else {
                System.out.println("El ID de la instalación no existe.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID proporcionado no es válido. " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Se ha producido un error al borrar la instalación. " + e.getMessage());
            return false;
        }
    }
}
