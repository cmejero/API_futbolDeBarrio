package com.futbolDeBarrio.futbolDeBarrio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futbolDeBarrio.futbolDeBarrio.entidad.TokenVerificacionEmailEntidad;

@Repository
public interface TokenVerificacionEmailInterfaz extends JpaRepository<TokenVerificacionEmailEntidad, Long> {
	Optional<TokenVerificacionEmailEntidad> findByToken(String token);

}
