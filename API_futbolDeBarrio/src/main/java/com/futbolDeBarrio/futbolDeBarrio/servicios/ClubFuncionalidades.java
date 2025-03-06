package com.futbolDeBarrio.futbolDeBarrio.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubDto;
import com.futbolDeBarrio.futbolDeBarrio.entidad.ClubEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.ClubInterfaz;

@Service
public class ClubFuncionalidades {

    @Autowired
    ClubInterfaz clubInterfaz;

    public Optional<ClubEntidad> buscarClubPorEmail(String email) {
        return clubInterfaz.findByEmailClub(email);
    }
    
    /**
     * Método que mapea de entidad a DTO
     */
    public ClubDto mapearAClubDto(ClubEntidad clubEntidad) {
        ClubDto clubDto = new ClubDto();
        clubDto.setIdClub(clubEntidad.getIdClub());
        clubDto.setNombreClub(clubEntidad.getNombreClub());
        clubDto.setAbreviaturaClub(clubEntidad.getAbreviaturaClub());
        clubDto.setDescripcionClub(clubEntidad.getDescripcionClub());
        clubDto.setFechaCreacionClub(clubEntidad.getFechaCreacionClub());
        clubDto.setFechaFundacionClub(clubEntidad.getFechaFundacionClub());
        clubDto.setLocalidadClub(clubEntidad.getLocalidadClub());
        clubDto.setPaisClub(clubEntidad.getPaisClub());
        clubDto.setPasswordClub(clubEntidad.getPasswordClub());
        clubDto.setLogoClub(clubEntidad.getLogoClub());
        clubDto.setEmailClub(clubEntidad.getEmailClub());
        clubDto.setTelefonoClub(clubEntidad.getTelefonoClub());
        return clubDto;
    }

    /**
     * Método que mapea de DTO a entidad
     */
    public ClubEntidad mapearADtoAEntidad(ClubDto clubDto) {
        ClubEntidad clubEntidad = new ClubEntidad();
        clubEntidad.setIdClub(clubDto.getIdClub());
        clubEntidad.setNombreClub(clubDto.getNombreClub());
        clubEntidad.setAbreviaturaClub(clubDto.getAbreviaturaClub());
        clubEntidad.setDescripcionClub(clubDto.getDescripcionClub());
        clubEntidad.setFechaCreacionClub(clubDto.getFechaCreacionClub());
        clubEntidad.setFechaFundacionClub(clubDto.getFechaFundacionClub());
        clubEntidad.setLocalidadClub(clubDto.getLocalidadClub());
        clubEntidad.setPaisClub(clubDto.getPaisClub());
        clubDto.setPasswordClub(clubEntidad.getPasswordClub());
        clubEntidad.setLogoClub(clubDto.getLogoClub());
        clubEntidad.setEmailClub(clubDto.getEmailClub());
        clubEntidad.setTelefonoClub(clubDto.getTelefonoClub());
        return clubEntidad;
    }

    /**
     * Método que obtiene la lista de clubes en DTO
     */
    public List<ClubDto> obtenerClubesDto() {
        List<ClubEntidad> clubesEntidad = (List<ClubEntidad>) clubInterfaz.findAll();
        List<ClubDto> clubesDto = new ArrayList<>();
        for (ClubEntidad club : clubesEntidad) {
            clubesDto.add(mapearAClubDto(club));
        }
        return clubesDto;
    }

    public ClubDto obtenerClubDtoPorId(Long idClub) {
        ClubEntidad clubEntidad = clubInterfaz.findByIdClub(idClub);
        return clubEntidad != null ? mapearAClubDto(clubEntidad) : null;
    }

    /**
     * Método para guardar un club en la base de datos, recibiendo un DTO
     */
    public ClubEntidad guardarClub(ClubDto clubDto) {
        ClubEntidad clubEntidad = mapearADtoAEntidad(clubDto);
        clubEntidad.setPasswordClub(encriptarContrasenya(clubDto.getPasswordClub()));
        return clubInterfaz.save(clubEntidad);
    }

    /**
     * Método que modifica un club en la base de datos
     */
    public boolean modificarClub(String id, ClubDto clubDto) {
        boolean esModificado = false;
        try {
            Long idClub = Long.parseLong(id);
            ClubEntidad club = clubInterfaz.findByIdClub(idClub);

            if (club == null) {
                System.out.println("El ID proporcionado no existe");
            } else {
                club.setNombreClub(clubDto.getNombreClub());
                club.setAbreviaturaClub(clubDto.getAbreviaturaClub());
                club.setDescripcionClub(clubDto.getDescripcionClub());
                club.setFechaCreacionClub(clubDto.getFechaCreacionClub());
                club.setFechaFundacionClub(clubDto.getFechaFundacionClub());
                club.setLocalidadClub(clubDto.getLocalidadClub());
                club.setPaisClub(clubDto.getPaisClub());
                club.setLogoClub(clubDto.getLogoClub());
                club.setEmailClub(clubDto.getEmailClub());
                club.setTelefonoClub(clubDto.getTelefonoClub());
                club.setPasswordClub(encriptarContrasenya(clubDto.getPasswordClub()));
                clubInterfaz.save(club);
                esModificado = true;
            }

        } catch (NumberFormatException nfe) {
            System.out.println("Error: El ID proporcionado no es válido. " + nfe.getMessage());
        } catch (Exception e) {
            System.out.println("Se ha producido un error al modificar el club. " + e.getMessage());
        }

        return esModificado;
    }

    /**
     * Método que encripta una contraseña antes de guardarla
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
     * Método que borra un club por su ID
     */
    public boolean borrarClub(String idClubString) {
        boolean estaBorrado = false;
        try {
            Long idClub = Long.parseLong(idClubString);
            ClubEntidad clubEntidad = clubInterfaz.findByIdClub(idClub);

            if (clubEntidad == null) {
                estaBorrado = false;
                System.out.println("El id del Club no existe");
            } else {
                clubInterfaz.delete(clubEntidad);
                estaBorrado = true;
                System.out.println("El club " + clubEntidad.getNombreClub() + " ha sido borrado con éxito");
            }

        } catch (NumberFormatException nfe) {
            System.out.println("Error: El ID proporcionado no es válido. " + nfe.getMessage());
        } catch (Exception e) {
            System.out.println("Se ha producido un error al borrar el club. " + e.getMessage());
        }

        return estaBorrado;
    }
}
