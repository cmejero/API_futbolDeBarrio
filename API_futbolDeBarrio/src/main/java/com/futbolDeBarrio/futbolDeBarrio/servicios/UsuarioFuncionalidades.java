	package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.UsuarioDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.UsuarioInterfaz;

/**
 * Clase que se encarga de la lógica interna de los métodos CRUD para Usuario
 */
@Service
public class UsuarioFuncionalidades {

    @Autowired
    UsuarioInterfaz usuarioInterfaz;

    // Otros métodos existentes...

    /**
     * Método que mapea de entidad a DTO
     */
    public UsuarioDto mapearAUsuarioDto(UsuarioEntidad usuarioEntidad) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setIdUsuario(usuarioEntidad.getIdUsuario());
        usuarioDto.setNombreCompletoUsuario(usuarioEntidad.getNombreCompletoUsuario());
        usuarioEntidad.setAliasUsuario(usuarioDto.getAliasUsuario());
        usuarioDto.setFechaNacimientoUsuario(usuarioEntidad.getFechaNacimientoUsuario());
        usuarioDto.setEmailUsuario(usuarioEntidad.getEmailUsuario());
        usuarioDto.setTelefonoUsuario(usuarioEntidad.getTelefonoUsuario());
        usuarioDto.setDescripcionUsuario(usuarioEntidad.getDescripcionUsuario());
        usuarioDto.setImagenUsuario(usuarioEntidad.getImagenUsuario());
        usuarioDto.setEstadoUsuario(usuarioEntidad.getEstadoUsuario());
        usuarioDto.setRolUsuario(usuarioEntidad.getRolUsuario());
        return usuarioDto;
    }

    /**
     * Método que mapea de DTO a entidad
     */
    private UsuarioEntidad mapearADtoAEntidad(UsuarioDto usuarioDto) {
        UsuarioEntidad usuarioEntidad = new UsuarioEntidad();
        usuarioEntidad.setIdUsuario(usuarioDto.getIdUsuario());
        usuarioEntidad.setNombreCompletoUsuario(usuarioDto.getNombreCompletoUsuario());
        usuarioEntidad.setAliasUsuario(usuarioDto.getAliasUsuario());
        usuarioEntidad.setFechaNacimientoUsuario(usuarioDto.getFechaNacimientoUsuario());
        usuarioEntidad.setEmailUsuario(usuarioDto.getEmailUsuario());
        usuarioEntidad.setTelefonoUsuario(usuarioDto.getTelefonoUsuario());
        usuarioEntidad.setDescripcionUsuario(usuarioDto.getDescripcionUsuario());
        usuarioEntidad.setImagenUsuario(usuarioDto.getImagenUsuario());
        usuarioEntidad.setEstadoUsuario(usuarioDto.getEstadoUsuario());
        usuarioEntidad.setRolUsuario(usuarioDto.getRolUsuario());
        return usuarioEntidad;
    }

    /**
     * Método que mapea una lista de entidades a DTOs
     */
    public List<UsuarioDto> obtenerUsuariosDto() {
        List<UsuarioEntidad> usuariosEntidad = (List<UsuarioEntidad>) usuarioInterfaz.findAll();
        List<UsuarioDto> usuariosDto = new ArrayList<>();
        for (UsuarioEntidad usuario : usuariosEntidad) {
            usuariosDto.add(mapearAUsuarioDto(usuario));
        }
        return usuariosDto;
    }
    
    public UsuarioDto obtenerUsuarioDtoPorId(Long idUsuario) {
        UsuarioEntidad usuarioEntidad = usuarioInterfaz.findByIdUsuario(idUsuario);
        return usuarioEntidad != null ? mapearAUsuarioDto(usuarioEntidad) : null;
    }

    /**
     * Método para guardar un usuario a la base de datos, recibiendo un DTO
     */
    public UsuarioEntidad guardarUsuario(UsuarioDto usuarioDto) {
        // Mapeamos el DTO a una entidad
        UsuarioEntidad usuarioEntidad = mapearADtoAEntidad(usuarioDto);

        // Encriptamos la contraseña antes de guardar
        usuarioEntidad.setPasswordUsuario(encriptarContrasenya(usuarioDto.getPasswordUsuario()));

        // Guardamos la entidad en la base de datos
        return usuarioInterfaz.save(usuarioEntidad);
    }

    /**
     * Método que se encarga de modificar un usuario en la base de datos
     */
    public boolean modificarUsuario(String id, UsuarioDto usuarioDto) {
        boolean esModificado = false;
        try {
            Long idUsuario = Long.parseLong(id);
            UsuarioEntidad usuario = usuarioInterfaz.findByIdUsuario(idUsuario);

            if (usuario == null) {
                System.out.println("El ID proporcionado no existe");
            } else {
                // Mapeamos el DTO a entidad
                usuario.setNombreCompletoUsuario(usuarioDto.getNombreCompletoUsuario());
                usuario.setAliasUsuario(usuarioDto.getAliasUsuario());
                usuario.setFechaNacimientoUsuario(usuarioDto.getFechaNacimientoUsuario());
                usuario.setEmailUsuario(usuarioDto.getEmailUsuario());
                usuario.setTelefonoUsuario(usuarioDto.getTelefonoUsuario());
                usuario.setPasswordUsuario(encriptarContrasenya(usuarioDto.getPasswordUsuario())); 
                usuario.setDescripcionUsuario(usuarioDto.getDescripcionUsuario());
                usuario.setImagenUsuario(usuarioDto.getImagenUsuario());
                usuario.setEstadoUsuario(usuarioDto.getEstadoUsuario());
                usuario.setRolUsuario(usuarioDto.getRolUsuario());

                // Guardamos la entidad modificada en la base de datos
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
     * Método que encripta una contraseña antes de guardarla
     */
    public String encriptarContrasenya(String contraseña) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(contraseña.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = String.format("%02x", b);
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método que borra un usuario por su ID
     */
    public boolean borrarUsuario(String idUsuarioString) {
        boolean estaBorrado = false;
        try {
            Long idUsuario = Long.parseLong(idUsuarioString);
            UsuarioEntidad usuarioDto = usuarioInterfaz.findByIdUsuario(idUsuario);

            if (usuarioDto == null) {
                estaBorrado = false;
                System.out.println("El id del Usuario no existe");
            } else {
                usuarioInterfaz.delete(usuarioDto);
                estaBorrado = true;
                System.out.println("El usuario " + usuarioDto.getNombreCompletoUsuario() + " ha sido borrado con éxito");
            }

        } catch (NumberFormatException nfe) {
            System.out.println("Error: El ID proporcionado no es válido. " + nfe.getMessage());
        } catch (Exception e) {
            System.out.println("Se ha producido un error al borrar el usuario. " + e.getMessage());
        }

        return estaBorrado;
    }
}
