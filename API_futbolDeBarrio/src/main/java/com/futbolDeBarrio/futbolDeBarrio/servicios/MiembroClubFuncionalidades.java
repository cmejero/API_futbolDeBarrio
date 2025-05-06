package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.MiembroClubDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.UsuarioDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.MiembroClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.UsuarioEntidad;
import com.futbolDeBarrio.futbolDeBarrio.logs.Logs;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.MiembroClubInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.UsuarioInterfaz;

@Service
public class MiembroClubFuncionalidades {

    @Autowired
    private MiembroClubInterfaz miembroClubInterfaz;

    @Autowired
    private ClubInterfaz clubInterfaz;

    @Autowired
    private UsuarioInterfaz usuarioInterfaz;

    /**
     * Mapea una entidad MiembroClubEntidad a un DTO MiembroClubDto.
     */
    public MiembroClubDto mapearAMiembroClubDto(MiembroClubEntidad miembroClubEntidad) {
        MiembroClubDto miembroClubDto = new MiembroClubDto();
        miembroClubDto.setIdMiembroClub(miembroClubEntidad.getIdMiembroClub());
        miembroClubDto.setFechaAltaUsuario(miembroClubEntidad.getFechaAltaUsuario());
        miembroClubDto.setFechaBajaUsuario(miembroClubEntidad.getFechaBajaUsuario());
        miembroClubDto.setIdClub(miembroClubEntidad.getClub().getIdClub());

       
        UsuarioEntidad usuarioEntidad = miembroClubEntidad.getUsuario();
        miembroClubDto.setUsuarioId(usuarioEntidad.getIdUsuario());

       
        UsuarioDto usuarioDto = new UsuarioDto(
                usuarioEntidad.getNombreCompletoUsuario(),
                usuarioEntidad.getAliasUsuario(),
                usuarioEntidad.getEmailUsuario()
        );
        miembroClubDto.setUsuario(usuarioDto);  

        return miembroClubDto;
    }

    /**
     * Mapea un DTO MiembroClubDto a una entidad MiembroClubEntidad.
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
     * Obtiene una lista de todos los MiembroClubDto.
     */
    public List<MiembroClubDto> obtenerMiembrosClubDto() {
        List<MiembroClubEntidad> miembrosClubEntidad = (List<MiembroClubEntidad>) miembroClubInterfaz.findAll();
        List<MiembroClubDto> miembrosClubDto = new ArrayList<>();
        for (MiembroClubEntidad miembroClub : miembrosClubEntidad) {
            miembrosClubDto.add(mapearAMiembroClubDto(miembroClub));
        }
        return miembrosClubDto;
    }

    /**
     * Obtiene un MiembroClubDto por su ID.
     */
    public MiembroClubDto obtenerMiembroClubDtoPorId(Long idMiembroClub) {
        Optional<MiembroClubEntidad> miembroClubEntidad = miembroClubInterfaz.findById(idMiembroClub);
        return miembroClubEntidad.map(this::mapearAMiembroClubDto).orElse(null);
    }
    
    /**
     * Obtiene la lista de miembros de un club según el clubId.
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
     */
    public MiembroClubEntidad guardarMiembroClub(MiembroClubDto miembroClubDto) {
        Logs.ficheroLog("Verificando si el miembro ya existe en el club con usuario ID " + miembroClubDto.getUsuarioId() + " y club ID " + miembroClubDto.getIdClub());
        
        Optional<MiembroClubEntidad> miembroExistente = miembroClubInterfaz
            .findByUsuario_IdUsuarioAndClub_IdClub(miembroClubDto.getUsuarioId(), miembroClubDto.getIdClub());

        if (miembroExistente.isPresent()) {
            Logs.ficheroLog("El miembro ya existe en el club");
            throw new RuntimeException("El miembro con ID de usuario " + miembroClubDto.getUsuarioId() + 
                                       " ya pertenece al club con ID " + miembroClubDto.getIdClub());
        }

        // Si no existe, continuar con el proceso de guardar el miembro
        MiembroClubEntidad miembroClubEntidad = mapearADtoAEntidad(miembroClubDto);
        Logs.ficheroLog("Guardando miembro del club...");
        return miembroClubInterfaz.save(miembroClubEntidad);
    }






    /**
     * Modifica un miembro del club existente en la base de datos.
     */
    public boolean modificarMiembroClub(String idMiembroClubString, MiembroClubDto miembroClubDto) {
    	 Long idMiembroClub = Long.parseLong(idMiembroClubString);
        Optional<MiembroClubEntidad> miembroClubOpt = miembroClubInterfaz.findById(idMiembroClub);
        if (miembroClubOpt.isPresent()) {
            MiembroClubEntidad miembroClub = miembroClubOpt.get();
            miembroClub.setFechaAltaUsuario(miembroClubDto.getFechaAltaUsuario());
            miembroClub.setFechaBajaUsuario(miembroClubDto.getFechaBajaUsuario());

            Optional<ClubEntidad> clubOpt = clubInterfaz.findById(miembroClubDto.getIdClub());
            clubOpt.ifPresent(miembroClub::setClub);

            Optional<UsuarioEntidad> usuarioOpt = usuarioInterfaz.findById(miembroClubDto.getUsuarioId());
            usuarioOpt.ifPresent(miembroClub::setUsuario);

            miembroClubInterfaz.save(miembroClub);
            return true;
        } else {
            System.out.println("El ID proporcionado no existe");
            return false;
        }
    }

    /**
     * Borra un miembro del club por su ID.
     */
    public boolean borrarMiembroClub(String idMiembroClubString) {
    	 Long idMiembroClub = Long.parseLong(idMiembroClubString);
         Optional<MiembroClubEntidad> miembroClubOpt = miembroClubInterfaz.findById(idMiembroClub);
        if (miembroClubOpt.isPresent()) {
            miembroClubInterfaz.delete(miembroClubOpt.get());
            System.out.println("El miembro del club ha sido borrado con éxito");
            return true;
        } else {
            System.out.println("El id del miembro del club no existe");
            return false;
        }
    }
}
