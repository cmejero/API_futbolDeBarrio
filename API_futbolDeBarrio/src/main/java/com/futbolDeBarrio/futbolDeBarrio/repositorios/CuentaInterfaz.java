package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.CuentaEntidad;
import com.futbolDeBarrio.futbolDeBarrio.enums.Rol;

public interface CuentaInterfaz extends JpaRepository<CuentaEntidad, Long> {

    // Buscar cuenta por email y rol (útil si permites mismo email en varios roles)
    Optional<CuentaEntidad> findByEmailAndRol(String email, Rol rol);

    // Buscar solo por email
    Optional<CuentaEntidad> findByEmail(String email);

}
