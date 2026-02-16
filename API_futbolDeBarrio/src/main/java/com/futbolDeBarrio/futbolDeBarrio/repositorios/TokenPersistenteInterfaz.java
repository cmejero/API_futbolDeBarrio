package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.TokenPersistenteEntidad;

@Repository
public interface TokenPersistenteInterfaz extends JpaRepository<TokenPersistenteEntidad, Long> {
    Optional<TokenPersistenteEntidad> findByTokenAndActivoTrue(String token);
}
