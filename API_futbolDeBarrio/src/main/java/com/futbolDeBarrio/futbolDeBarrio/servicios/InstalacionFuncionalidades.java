package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
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
	
	@Autowired
	Utilidades utilidades;
	
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
        if (instalacionEntidad.getImagenInstalacion() != null) {
            String imagenBase64 = Base64.getEncoder().encodeToString(instalacionEntidad.getImagenInstalacion());
            instalacionDto.setImagenInstalacion(imagenBase64);
        }
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
        instalacionEntidad.setPasswordInstalacion(instalacionDto.getPasswordInstalacion());
        instalacionEntidad.setEstadoInstalacion(instalacionDto.getEstadoInstalacion());
        if (instalacionDto.getImagenInstalacion() != null) {
            byte[] imagenBytes = Base64.getDecoder().decode(instalacionDto.getImagenInstalacion());
            instalacionEntidad.setImagenInstalacion(imagenBytes);
        }
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
        // Verificamos si el email ya existe en la base de datos
        Optional<InstalacionEntidad> instalacionExistente = instalacionInterfaz.findByEmailInstalacion(instalacionDto.getEmailInstalacion());
        if (instalacionExistente.isPresent()) {
            throw new IllegalArgumentException("El email proporcionado ya está siendo utilizado por otra instalación.");
        }

        // Mapeamos el DTO a una entidad
        InstalacionEntidad instalacionEntidad = mapearADtoAEntidad(instalacionDto);

        // Verificamos que la contraseña no sea nula ni vacía
        if (instalacionDto.getPasswordInstalacion() == null || instalacionDto.getPasswordInstalacion().isEmpty()) {
            throw new IllegalArgumentException("La contraseña de la instalación no puede ser nula o vacía.");
        }

        // Encriptamos la contraseña antes de guardar
        instalacionEntidad.setPasswordInstalacion(utilidades.encriptarContrasenya(instalacionDto.getPasswordInstalacion()));

        // Guardamos la entidad en la base de datos
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
                instalacion.setPasswordInstalacion(instalacionDto.getPasswordInstalacion());
                if (instalacionDto.getPasswordInstalacion() != null && !instalacionDto.getPasswordInstalacion().isEmpty()) {
                    // Si la contraseña se modificó, la encriptamos
                    instalacion.setPasswordInstalacion((utilidades.encriptarContrasenya(instalacionDto.getPasswordInstalacion())));
                } else {
                    // Si la contraseña no se modificó, no tocamos la contraseña encriptada actual
                    System.out.println("La contraseña no ha sido modificada. Se mantiene la actual.");
                }
                if (instalacionDto.getImagenInstalacion() != null) {
                    byte[] imagenBytes = Base64.getDecoder().decode(instalacionDto.getImagenInstalacion());
                    instalacion.setImagenInstalacion(imagenBytes);
                }

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
