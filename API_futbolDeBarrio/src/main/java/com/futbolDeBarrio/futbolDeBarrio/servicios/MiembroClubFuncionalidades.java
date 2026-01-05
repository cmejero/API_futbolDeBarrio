package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.MiembroClubDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.UsuarioDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.MiembroClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.RolUsuario;
import com.futbolDeBarrio.futbolDeBarrio.logs.Logs;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.MiembroClubInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.UsuarioInterfaz;

@Service
/**
 * Clase que se encarga de la lógica de los metodos CRUD de miembro club
 */
public class MiembroClubFuncionalidades {

    @Autowired
    private MiembroClubInterfaz miembroClubInterfaz;

    @Autowired
    private ClubInterfaz clubInterfaz;

    @Autowired
    private UsuarioInterfaz usuarioInterfaz;


    /**
     * Mapea una entidad {@link MiembroClubEntidad} a un DTO {@link MiembroClubDto}.
     * 
     * @param miembroClubEntidad La entidad del miembro del club que se va a mapear.
     * @return Un DTO de tipo {@link MiembroClubDto} con los datos mapeados.
     */
    public MiembroClubDto mapearAMiembroClubDto(MiembroClubEntidad miembroClubEntidad) {
        MiembroClubDto miembroClubDto = new MiembroClubDto();
        
        // Datos del miembro
        miembroClubDto.setIdMiembroClub(miembroClubEntidad.getIdMiembroClub());
        miembroClubDto.setFechaAltaUsuario(miembroClubEntidad.getFechaAltaUsuario());
        miembroClubDto.setFechaBajaUsuario(miembroClubEntidad.getFechaBajaUsuario());
        miembroClubDto.setIdClub(miembroClubEntidad.getClub().getIdClub());

        // Datos del usuario
        UsuarioEntidad usuarioEntidad = miembroClubEntidad.getUsuario();
        miembroClubDto.setUsuarioId(usuarioEntidad.getIdUsuario());
        UsuarioDto usuarioDto = new UsuarioDto(
                usuarioEntidad.getNombreCompletoUsuario(),
                usuarioEntidad.getAliasUsuario(),
                usuarioEntidad.getEmailUsuario()
        );
        miembroClubDto.setUsuario(usuarioDto);

        // Datos del club
        ClubEntidad clubEntidad = miembroClubEntidad.getClub();
        if (clubEntidad != null) {
            ClubDto clubDto = new ClubDto();
            clubDto.setIdClub(clubEntidad.getIdClub());
            clubDto.setNombreClub(clubEntidad.getNombreClub());
            clubDto.setAbreviaturaClub(clubEntidad.getAbreviaturaClub());
            clubDto.setDescripcionClub(clubEntidad.getDescripcionClub());
            clubDto.setFechaCreacionClub(clubEntidad.getFechaCreacionClub());
            clubDto.setFechaFundacionClub(clubEntidad.getFechaFundacionClub());
            clubDto.setLocalidadClub(clubEntidad.getLocalidadClub());
            clubDto.setPaisClub(clubEntidad.getPaisClub());
        	if (clubEntidad.getLogoClub() != null) {
    			String imagenBase64 = Base64.getEncoder().encodeToString(clubEntidad.getLogoClub());
    			clubDto.setLogoClub(imagenBase64);
    		}
            clubDto.setEmailClub(clubEntidad.getEmailClub());
            clubDto.setTelefonoClub(clubEntidad.getTelefonoClub());
            clubDto.setEsPremium(clubEntidad.isEsPremium());

            miembroClubDto.setClub(clubDto);
        }

        return miembroClubDto;
    }

    /**
     * Mapea un DTO {@link MiembroClubDto} a una entidad {@link MiembroClubEntidad}.
     * 
     * @param miembroClubDto El DTO del miembro del club a mapear.
     * @return La entidad {@link MiembroClubEntidad} correspondiente.
     */
    private MiembroClubEntidad mapearADtoAEntidad(MiembroClubDto miembroClubDto) {
        MiembroClubEntidad miembroClubEntidad = new MiembroClubEntidad();
        miembroClubEntidad.setIdMiembroClub(miembroClubDto.getIdMiembroClub());
        miembroClubEntidad.setFechaAltaUsuario(miembroClubDto.getFechaAltaUsuario());
        miembroClubEntidad.setFechaBajaUsuario(miembroClubDto.getFechaBajaUsuario());

        Optional<ClubEntidad> clubOpt = clubInterfaz.findById(miembroClubDto.getIdClub());
        clubOpt.ifPresent(miembroClubEntidad::setClub);

        Optional<UsuarioEntidad> usuarioOpt = usuarioInterfaz.findById(miembroClubDto.getUsuarioId());
        usuarioOpt.ifPresent(miembroClubEntidad::setUsuario);

        return miembroClubEntidad;
    }

    /**
     * Obtiene una lista de todos los miembros del club en formato DTO.
     * 
     * @return Una lista de objetos {@link MiembroClubDto} con los miembros del club.
     */
    public List<MiembroClubDto> obtenerMiembrosClubDto() {
        List<MiembroClubEntidad> miembrosClubEntidad = (List<MiembroClubEntidad>) miembroClubInterfaz.findAll();
        List<MiembroClubDto> miembrosClubDto = new ArrayList<>();
        for (MiembroClubEntidad miembroClub : miembrosClubEntidad) {
            miembrosClubDto.add(mapearAMiembroClubDto(miembroClub));
        }
        return miembrosClubDto;
    }
    
    public List<MiembroClubDto> obtenerMisClubesPorUsuario(Long usuarioId) {
        List<MiembroClubEntidad> miembros = miembroClubInterfaz.findByUsuario_IdUsuario(usuarioId);
        List<MiembroClubDto> misClubes = new ArrayList<>();
        for (MiembroClubEntidad miembro : miembros) {
            misClubes.add(mapearAMiembroClubDto(miembro));
        }
        return misClubes;
    }

    /**
     * Obtiene un miembro del club por su ID en formato DTO.
     * 
     * @param idMiembroClub El ID del miembro del club a buscar.
     * @return El {@link MiembroClubDto} correspondiente al miembro del club, o null si no se encuentra.
     */
    public MiembroClubDto obtenerMiembroClubDtoPorId(Long idMiembroClub) {
        Optional<MiembroClubEntidad> miembroClubEntidad = miembroClubInterfaz.findById(idMiembroClub);
        return miembroClubEntidad.map(this::mapearAMiembroClubDto).orElse(null);
    }
    
    /**
     * Obtiene la lista de miembros de un club según el ID del club.
     * 
     * @param idClub El ID del club del cual obtener los miembros.
     * @return Una lista de {@link MiembroClubDto} de los miembros del club.
     */
    public List<MiembroClubDto> obtenerMiembrosPorClub(Long idClub) {
        List<MiembroClubEntidad> miembrosClubEntidad = miembroClubInterfaz.findByClub_IdClub(idClub);
        
        List<MiembroClubDto> miembrosClubDto = new ArrayList<>();
        for (MiembroClubEntidad miembroClub : miembrosClubEntidad) {
            miembrosClubDto.add(mapearAMiembroClubDto(miembroClub));  // Convertir la entidad en DTO
        }
        return miembrosClubDto;
    }

    /**
     * Guarda un nuevo miembro del club en la base de datos.
     * 
     * @param miembroClubDto El DTO del miembro del club que se desea guardar.
     * @return La entidad {@link MiembroClubEntidad} del miembro del club guardado.
     * @throws RuntimeException Si el miembro ya existe en el club.
     */
    public MiembroClubDto guardarMiembroClub(MiembroClubDto miembroClubDto, String emailUsuarioLogueado) {
        Logs.ficheroLog("Procesando miembro para club con usuario: " + emailUsuarioLogueado);

        // 1️⃣ Asegurar que UsuarioDto exista
        if (miembroClubDto.getUsuario() == null) {
            miembroClubDto.setUsuario(new UsuarioDto());
        }

        // 2️⃣ Forzar email y rol
        miembroClubDto.getUsuario().setEmailUsuario(emailUsuarioLogueado);
        miembroClubDto.getUsuario().setRolUsuario(RolUsuario.Jugador);

        // 3️⃣ Validación de negocio
        if (miembroClubDto.getUsuario().getRolUsuario() != RolUsuario.Jugador
                || !miembroClubDto.getUsuario().getEmailUsuario().equals(emailUsuarioLogueado)) {
            Logs.ficheroLog("Intento no autorizado de añadir miembro al club por: " + emailUsuarioLogueado);
            throw new RuntimeException("Usuario no autorizado para unirse al club");
        }

        // 4️⃣ Verificar que el usuario no esté ya en el club
        Optional<MiembroClubEntidad> existente = miembroClubInterfaz
                .findByUsuario_IdUsuarioAndClub_IdClub(miembroClubDto.getUsuarioId(), miembroClubDto.getIdClub());

        if (existente.isPresent()) {
            Logs.ficheroLog("El miembro ya existe en el club");
            throw new RuntimeException("El miembro ya pertenece al club");
        }

        // 5️⃣ Guardar
        MiembroClubEntidad miembroEntidad = mapearADtoAEntidad(miembroClubDto);
        miembroEntidad = miembroClubInterfaz.save(miembroEntidad);

        // 6️⃣ Mapear de vuelta a DTO
        return mapearAMiembroClubDto(miembroEntidad);
    }



    public boolean eliminarMiembroClub(Long idMiembroClub, Long usuarioId, Long clubId, String emailLogueado) {
        Optional<MiembroClubEntidad> miembroOpt = miembroClubInterfaz.findById(idMiembroClub);
        if (miembroOpt.isEmpty()) return false;

        MiembroClubEntidad miembro = miembroOpt.get();

        if (usuarioId != null) {
            // Verificar que el usuario logueado coincide con el miembro
            UsuarioEntidad usuario = usuarioInterfaz.findById(usuarioId).orElse(null);
            if (usuario != null && emailLogueado.equals(usuario.getEmailUsuario())
                    && miembro.getUsuario().getIdUsuario() == usuarioId) {
                miembroClubInterfaz.delete(miembro);
                return true;
            }
            Logs.ficheroLog("Intento no autorizado de eliminar miembro por usuario: " + emailLogueado);
            return false;
        }

        if (clubId != null) {
            // Verificar que el club logueado coincide con el miembro
            ClubEntidad club = clubInterfaz.findById(clubId).orElse(null);
            if (club != null && emailLogueado.equals(club.getEmailClub())
                    && miembro.getClub().getIdClub() == clubId) {
                miembroClubInterfaz.delete(miembro);
                return true;
            }
            Logs.ficheroLog("Intento no autorizado de eliminar miembro por club: " + emailLogueado);
            return false;
        }

        Logs.ficheroLog("No se proporcionó usuarioId ni clubId para eliminar miembro: " + idMiembroClub);
        return false;
    }

}
