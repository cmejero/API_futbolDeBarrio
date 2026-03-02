package com.futbolDeBarrio.futbolDeBarrio.jwt;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.futbolDeBarrio.futbolDeBarrio.dtos.ClubDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.InstalacionDto;
import com.futbolDeBarrio.futbolDeBarrio.dtos.UsuarioDto;
import com.futbolDeBarrio.futbolDeBarrio.enums.Rol;
import com.futbolDeBarrio.futbolDeBarrio.servicios.ClubFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.InstalacionFuncionalidades;
import com.futbolDeBarrio.futbolDeBarrio.servicios.UsuarioFuncionalidades;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

@Component
/**
 * Clase que proporciona funcionalidades relacionadas con la generación de JWT.
 */
public class JwtFuncionalidades {

	@Value("${jwt.secret}")
	private String claveSecreta;
	@Autowired
	UsuarioFuncionalidades usuarioFuncionalidades;
	@Autowired
	ClubFuncionalidades clubFuncionalidades;
	@Autowired
	InstalacionFuncionalidades instalacionFuncionalidades;

	/**
	 * Genera un token JWT utilizando el email del usuario y su rol.
	 *
	 * @param email Email del usuario (se usará como "subject" del token)
	 * @param rol   Rol del usuario, que se añade como "claim" adicional
	 * @return Token JWT como cadena
	 */
	public String obtenerToken(String email, Rol rol) {
		Map<String, Object> extraClaims = new HashMap<>();
		extraClaims.put("role", rol.name());

		return Jwts.builder().setClaims(extraClaims).setSubject(email)
				.setIssuedAt(new java.util.Date(System.currentTimeMillis()))
				.setExpiration(new java.util.Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24))
				.signWith(Keys.hmacShaKeyFor(claveSecreta.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
				.compact();
	}

	/**
	 * Obtiene el ID del usuario asociado a un JWT dado.
	 *
	 * @param token JWT a validar.
	 * @return ID del usuario (jugador, club o instalación), o null si no se
	 *         encuentra o el token es inválido.
	 */
	public Long obtenerIdUsuario(String token) {
		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(Keys.hmacShaKeyFor(claveSecreta.getBytes(StandardCharsets.UTF_8))).build()
					.parseClaimsJws(token).getBody();

			// Supongamos que tu "subject" es el email
			String email = claims.getSubject();

			// Aquí devuelves el ID del usuario según el email
			// Por ejemplo usando tu servicio de usuarios:
			UsuarioDto u = usuarioFuncionalidades.obtenerUsuarioDtoPorEmail(email);
			if (u != null)
				return u.getIdUsuario();

			ClubDto c = clubFuncionalidades.obtenerClubDtoPorEmail(email);
			if (c != null)
				return c.getIdClub();

			InstalacionDto i = instalacionFuncionalidades.obtenerInstalacionDtoPorEmail(email);
			if (i != null)
				return i.getIdInstalacion();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
