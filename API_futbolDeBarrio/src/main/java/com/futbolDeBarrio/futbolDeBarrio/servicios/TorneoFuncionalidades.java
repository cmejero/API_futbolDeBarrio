package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.TorneoDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.InstalacionEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.TorneoEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.InstalacionInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.TorneoInterfaz;

@Service
public class TorneoFuncionalidades {

    @Autowired
    private TorneoInterfaz torneoInterfaz;

    @Autowired
    private InstalacionInterfaz instalacionInterfaz;
    
   
    // Método para mapear de entidad a DTO
    public TorneoDto mapearATorneoDto(TorneoEntidad torneoEntidad) {
        TorneoDto torneoDto = new TorneoDto();
        torneoDto.setIdTorneo(torneoEntidad.getIdTorneo());
        torneoDto.setNombreTorneo(torneoEntidad.getNombreTorneo());
        torneoDto.setFechaInicioTorneo(torneoEntidad.getFechaInicioTorneo());
        torneoDto.setFechaFinTorneo(torneoEntidad.getFechaFinTorneo());
        torneoDto.setDescripcionTorneo(torneoEntidad.getDescripcionTorneo());
        torneoDto.setModalidad(torneoEntidad.getModalidad());
        torneoDto.setInstalacionId(torneoEntidad.getInstalacion().getIdInstalacion());
        return torneoDto;
    }

    // Método para mapear de DTO a entidad
    public TorneoEntidad mapearADtoAEntidad(TorneoDto torneoDto) {
        TorneoEntidad torneoEntidad = new TorneoEntidad();
        torneoEntidad.setIdTorneo(torneoDto.getIdTorneo());
        torneoEntidad.setNombreTorneo(torneoDto.getNombreTorneo());
        torneoEntidad.setFechaInicioTorneo(torneoDto.getFechaInicioTorneo());
        torneoEntidad.setFechaFinTorneo(torneoDto.getFechaFinTorneo());
        torneoEntidad.setDescripcionTorneo(torneoDto.getDescripcionTorneo());
        torneoEntidad.setModalidad(torneoDto.getModalidad());

        // Obtener la instalación por ID
        Optional<InstalacionEntidad> instalacionOpt = instalacionInterfaz.findById(torneoDto.getInstalacionId());
        instalacionOpt.ifPresent(torneoEntidad::setInstalacion);

        return torneoEntidad;
    }

    // Obtener todos los torneos
    public List<TorneoDto> obtenerTodosLosTorneos() {
        List<TorneoEntidad> torneosEntidad = torneoInterfaz.findAll();
        return torneosEntidad.stream()
                             .map(this::mapearATorneoDto)
                             .collect(Collectors.toList());
    }

    // Obtener un torneo por ID
    public TorneoDto obtenerTorneoPorId(Long idTorneo) {
        Optional<TorneoEntidad> torneoEntidadOpt = torneoInterfaz.findById(idTorneo);
        return torneoEntidadOpt.map(this::mapearATorneoDto).orElse(null);
    }

    // Guardar un nuevo torneo
    public TorneoEntidad guardarTorneo(TorneoDto torneoDto) {
        TorneoEntidad torneoEntidad = mapearADtoAEntidad(torneoDto);
        return torneoInterfaz.save(torneoEntidad);
    }

    // Modificar un torneo existente
    public boolean modificarTorneo(String id, TorneoDto torneoDto) {
    	 Long idTorneo = Long.parseLong(id);
        Optional<TorneoEntidad> torneoOpt = torneoInterfaz.findById(idTorneo);

        if (torneoOpt.isPresent()) {
            TorneoEntidad torneoEntidad = torneoOpt.get();
            torneoEntidad.setNombreTorneo(torneoDto.getNombreTorneo());
            torneoEntidad.setFechaInicioTorneo(torneoDto.getFechaInicioTorneo());
            torneoEntidad.setFechaFinTorneo(torneoDto.getFechaFinTorneo());
            torneoEntidad.setDescripcionTorneo(torneoDto.getDescripcionTorneo());
            torneoEntidad.setModalidad(torneoDto.getModalidad());

            // Actualizar la instalación si es necesario
            Optional<InstalacionEntidad> instalacionOpt = instalacionInterfaz.findById(torneoDto.getInstalacionId());
            instalacionOpt.ifPresent(torneoEntidad::setInstalacion);

            torneoInterfaz.save(torneoEntidad);
            return true;
        } else {
            System.out.println("El ID proporcionado no existe");
            return false;
        }
    }

    // Borrar un torneo por ID
    public boolean borrarTorneo(String idTorneoString) {
    	  Long idTorneo = Long.parseLong(idTorneoString);
        Optional<TorneoEntidad> torneoOpt = torneoInterfaz.findById(idTorneo);

        if (torneoOpt.isPresent()) {
            torneoInterfaz.delete(torneoOpt.get());
            System.out.println("El torneo ha sido borrado con éxito");
            return true;
        } else {
            System.out.println("El id del Torneo no existe");
            return false;
        }
    }
}
