	package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.UsuarioDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.CuentaEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.JugadorEstadisticaGlobalEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.Rol;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.CuentaInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.JugadorEstadisticaGlobalInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.UsuarioInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.utilidades.Utilidades;
import com.futbolDeBarrio.futbolDeBarrio.verificacion.VerificacionEmailFuncionalidad;

/**
 * Clase que se encarga de la lógica interna de los métodos CRUD para Usuario
 */
@Service
public class UsuarioFuncionalidades {

    @Autowired
    UsuarioInterfaz usuarioInterfaz;
    @Autowired
    JugadorEstadisticaGlobalInterfaz jugadorEstadisticaGlobalInterfaz;
    @Autowired
    CuentaInterfaz cuentaInterfaz;
    @Autowired
    VerificacionEmailFuncionalidad verificacionEmailFuncionalidad;
    
    
    /**
     * Método para buscar un usuario por su email.
     * 
     * @param email el email del usuario
     * @return un Optional con el usuario encontrado
     */
    public Optional<UsuarioEntidad> buscarUsuarioPorEmail(String email) {
        return usuarioInterfaz.findByEmailUsuario(email);
    }
    
    /**
     * Método que mapea de entidad a DTO.
     * 
     * @param usuarioEntidad la entidad del usuario
     * @return el DTO correspondiente al usuario
     */
    public UsuarioDto mapearAUsuarioDto(UsuarioEntidad usuarioEntidad) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setIdUsuario(usuarioEntidad.getIdUsuario());
        usuarioDto.setNombreCompletoUsuario(usuarioEntidad.getNombreCompletoUsuario());
        usuarioDto.setAliasUsuario(usuarioEntidad.getAliasUsuario());
        usuarioDto.setFechaNacimientoUsuario(usuarioEntidad.getFechaNacimientoUsuario());
        usuarioDto.setEmailUsuario(usuarioEntidad.getEmailUsuario());
        usuarioDto.setPasswordUsuario(null);
        usuarioDto.setTelefonoUsuario(usuarioEntidad.getTelefonoUsuario());
        usuarioDto.setDescripcionUsuario(usuarioEntidad.getDescripcionUsuario());
        if (usuarioEntidad.getImagenUsuario() != null) {
            String imagenBase64 = Base64.getEncoder().encodeToString(usuarioEntidad.getImagenUsuario());
            usuarioDto.setImagenUsuario(imagenBase64);
        }
        usuarioDto.setEstadoUsuario(usuarioEntidad.getEstadoUsuario());
        usuarioDto.setRolUsuario(usuarioEntidad.getRolUsuario());
        usuarioDto.setEsPremium(usuarioEntidad.isEsPremium());
        return usuarioDto;
    }
    /**
     * Método que mapea de DTO a entidad.
     * 
     * @param usuarioDto el DTO del usuario
     * @return la entidad correspondiente al usuario
     */
    private UsuarioEntidad mapearADtoAEntidad(UsuarioDto usuarioDto) {
        UsuarioEntidad usuarioEntidad = new UsuarioEntidad();
        usuarioEntidad.setIdUsuario(usuarioDto.getIdUsuario());
        usuarioEntidad.setNombreCompletoUsuario(usuarioDto.getNombreCompletoUsuario());
        usuarioEntidad.setAliasUsuario(usuarioDto.getAliasUsuario());
        usuarioEntidad.setFechaNacimientoUsuario(usuarioDto.getFechaNacimientoUsuario());
        usuarioEntidad.setEmailUsuario(usuarioDto.getEmailUsuario());
        usuarioEntidad.setPasswordUsuario(usuarioDto.getPasswordUsuario());
        usuarioEntidad.setTelefonoUsuario(usuarioDto.getTelefonoUsuario());
        usuarioEntidad.setDescripcionUsuario(usuarioDto.getDescripcionUsuario());
        if (usuarioDto.getImagenUsuario() != null) {
            byte[] imagenBytes = Base64.getDecoder().decode(usuarioDto.getImagenUsuario());
            usuarioEntidad.setImagenUsuario(imagenBytes);
        }
        usuarioEntidad.setEstadoUsuario(usuarioDto.getEstadoUsuario());
        usuarioEntidad.setRolUsuario(usuarioDto.getRolUsuario());
        usuarioEntidad.setEsPremium(usuarioDto.isEsPremium());
        System.out.println(usuarioEntidad.toString());
        return usuarioEntidad;
    }

    /**
     * Método que mapea una lista de entidades a DTOs.
     * 
     * @return la lista de DTOs de usuarios
     */
    public ArrayList<UsuarioDto> obtenerUsuariosDto() {
        ArrayList<UsuarioEntidad> usuariosEntidad = (ArrayList<UsuarioEntidad>) usuarioInterfaz.findAll();
        ArrayList<UsuarioDto> usuariosDto = new ArrayList<>();
        for (UsuarioEntidad usuario : usuariosEntidad) {
            usuariosDto.add(mapearAUsuarioDto(usuario));
        }
        return usuariosDto;
    }
    
    /**
     * Método que obtiene un usuario por su ID.
     * 
     * @param idUsuario el ID del usuario
     * @return el DTO del usuario o null si no se encuentra
     */
    public UsuarioDto obtenerUsuarioDtoPorId(Long idUsuario) {
        UsuarioEntidad usuarioEntidad = usuarioInterfaz.findById(idUsuario).orElse(null);
        return usuarioEntidad != null ? mapearAUsuarioDto(usuarioEntidad) : null;
    }

    /**
     * Método para guardar un usuario en la base de datos, recibiendo un DTO.
     * 
     * @param usuarioDto el DTO del usuario
     * @return la entidad del usuario guardada
     */
    public UsuarioEntidad guardarUsuario(UsuarioDto usuarioDto) {
        if (usuarioDto.getRolUsuario() == null) {
            throw new IllegalArgumentException("El rol del usuario es obligatorio.");
        }

        // Validar email existente
        cuentaInterfaz.findByEmailAndRol(usuarioDto.getEmailUsuario(), Rol.Usuario)
            .ifPresent(c -> { throw new IllegalArgumentException("El email ya está en uso."); });

        // 1️⃣ Crear y guardar Cuenta
        CuentaEntidad cuenta = new CuentaEntidad();
        cuenta.setEmail(usuarioDto.getEmailUsuario());
        cuenta.setPassword(Utilidades.encriptarContrasenya(usuarioDto.getPasswordUsuario()));
        cuenta.setRol(Rol.Usuario);
        cuenta.setEmailVerificado(false);
        cuenta.setFechaCreacion(LocalDateTime.now());
        cuenta = cuentaInterfaz.save(cuenta);

        // 2️⃣ Crear Usuario y asociar Cuenta
        UsuarioEntidad usuarioEntidad = mapearADtoAEntidad(usuarioDto);
        usuarioEntidad.setCuenta(cuenta);
        usuarioEntidad.setPasswordUsuario(Utilidades.encriptarContrasenya(usuarioDto.getPasswordUsuario()));

        usuarioEntidad = usuarioInterfaz.save(usuarioEntidad);

        // 3️⃣ Crear estadísticas globales
        JugadorEstadisticaGlobalEntidad estadistica = new JugadorEstadisticaGlobalEntidad();
        estadistica.setJugadorGlobalId(usuarioEntidad);
        estadistica.setGolesGlobal(0);
        estadistica.setAsistenciasGlobal(0);
        estadistica.setAmarillasGlobal(0);
        estadistica.setRojasGlobal(0);
        estadistica.setPartidosJugadosGlobal(0);
        estadistica.setPartidosGanadosGlobal(0);
        estadistica.setPartidosPerdidosGlobal(0);
        estadistica.setMinutosJugadosGlobal(0);
        jugadorEstadisticaGlobalInterfaz.save(estadistica);

        verificacionEmailFuncionalidad.generarYEnviarToken(cuenta);

        return usuarioEntidad;
    }
    /**
     * Método que se encarga de modificar un usuario en la base de datos.
     * 
     * @param id el ID del usuario a modificar
     * @param usuarioDto el DTO con los nuevos datos del usuario
     * @return true si el usuario fue modificado correctamente, false en caso contrario
     */
    public boolean modificarUsuario(String id, UsuarioDto usuarioDto) {
        boolean esModificado = false;
        try {
            Long idUsuario = Long.parseLong(id);
            UsuarioEntidad usuario = usuarioInterfaz.findByIdUsuario(idUsuario);

            if (usuario == null) {
                // El ID no existe, no hacemos nada
            } else {
                usuario.setNombreCompletoUsuario(usuarioDto.getNombreCompletoUsuario());
                usuario.setAliasUsuario(usuarioDto.getAliasUsuario());
                usuario.setFechaNacimientoUsuario(usuarioDto.getFechaNacimientoUsuario());
                usuario.setEmailUsuario(usuarioDto.getEmailUsuario());
                usuario.setTelefonoUsuario(usuarioDto.getTelefonoUsuario());

                if (usuarioDto.getPasswordUsuario() != null && !usuarioDto.getPasswordUsuario().isEmpty()) {
                    // Actualizar contraseña sólo si viene una nueva (encriptándola)
                    usuario.setPasswordUsuario(Utilidades.encriptarContrasenya(usuarioDto.getPasswordUsuario()));
                } // sino dejamos la contraseña actual intacta

                usuario.setDescripcionUsuario(usuarioDto.getDescripcionUsuario());

                if (usuarioDto.getImagenUsuario() != null) {
                    byte[] imagenBytes = Base64.getDecoder().decode(usuarioDto.getImagenUsuario());
                    usuario.setImagenUsuario(imagenBytes);
                }
                
                // Estado y rol siempre se actualizan aunque no haya imagen
                usuario.setEstadoUsuario(usuarioDto.getEstadoUsuario());
                usuario.setRolUsuario(usuarioDto.getRolUsuario());
                usuario.setEsPremium(usuarioDto.isEsPremium());

                usuarioInterfaz.save(usuario);
                esModificado = true;
            }
        } catch (NumberFormatException nfe) {
            // ID inválido
        } catch (Exception e) {	
            e.printStackTrace();
        }
        return esModificado;
    }
    
    
    /**
     * Método para actualizar solo el campo esPremium de un usuario.
     *
     * @param idUsuario ID del usuario a actualizar.
     * @param nuevoEstado Valor del campo esPremium (true o false).
     * @return true si se actualizó correctamente, false si no se encontró el usuario.
     */
    public boolean actualizarPremium(Long idUsuario, boolean nuevoEstado) {
        try {
            Optional<UsuarioEntidad> usuarioOpt = usuarioInterfaz.findById(idUsuario);

            if (usuarioOpt.isPresent()) {
                UsuarioEntidad usuario = usuarioOpt.get();
                usuario.setEsPremium(nuevoEstado);
                usuarioInterfaz.save(usuario);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    
    /**
     * Método que borra un usuario por su ID.
     * 
     * @param idUsuarioString el ID del usuario como cadena
     * @return true si el usuario fue borrado correctamente, false en caso contrario
     */
    public boolean borrarUsuario(String idUsuarioString) {
        boolean estaBorrado = false;
        try {
            Long idUsuario = Long.parseLong(idUsuarioString);
            UsuarioEntidad usuarioEntidad = usuarioInterfaz.findByIdUsuario(idUsuario);

            if (usuarioEntidad == null) {
                estaBorrado = false;

            } else {
                String email = usuarioEntidad.getEmailUsuario();
                if (email != null && email.equalsIgnoreCase("admin@1")) {

                    estaBorrado = false;
                } else {
                    usuarioInterfaz.delete(usuarioEntidad);
                    estaBorrado = true;
                }
            }

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return estaBorrado;
    }

    
}
