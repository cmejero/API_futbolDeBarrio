package com.futbolDeBarrio.futbolDeBarrio.verificacion;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.futbolDeBarrio.futbolDeBarrio.entidad.CuentaEntidad;
import com.futbolDeBarrio.futbolDeBarrio.entidad.TokenVerificacionEmailEntidad;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.CuentaInterfaz;
import com.futbolDeBarrio.futbolDeBarrio.repositorios.TokenVerificacionEmailInterfaz;

@Service
public class VerificacionEmailFuncionalidad {
	@Autowired
	private TokenVerificacionEmailInterfaz tokenVerificacionEmailInterfaz;

	@Autowired
	private CuentaInterfaz cuentaInterfaz;
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * Genera un token de verificación para una cuenta y lo guarda en la base de
	 * datos.
	 */
	public TokenVerificacionEmailEntidad generarToken(CuentaEntidad cuenta) {
		TokenVerificacionEmailEntidad token = new TokenVerificacionEmailEntidad();
		token.setCuenta(cuenta);
		token.setToken(UUID.randomUUID().toString());
		token.setFechaExpiracion(LocalDateTime.now().plusHours(24));
		return tokenVerificacionEmailInterfaz.save(token);
	}

	/**
	 * Verifica un token recibido desde el link de email.
	 */
	public String verificarToken(String tokenString) {
		TokenVerificacionEmailEntidad token = tokenVerificacionEmailInterfaz.findByToken(tokenString)
				.orElseThrow(() -> new RuntimeException("Token inválido"));

		if (token.getFechaExpiracion().isBefore(LocalDateTime.now())) {
			return "Token expirado";
		}

		CuentaEntidad cuenta = token.getCuenta();
		cuenta.setEmailVerificado(true);
		cuentaInterfaz.save(cuenta);

		tokenVerificacionEmailInterfaz.delete(token); // opcional: borrar token usado
		return "Email verificado correctamente";
	}
	
	public void enviarEmailVerificacion(CuentaEntidad cuenta, String token) {
	    SimpleMailMessage mensaje = new SimpleMailMessage();
	    mensaje.setTo(cuenta.getEmail());
	    mensaje.setSubject("Verifica tu correo");
	    mensaje.setText("Hola " + cuenta.getEmail() + ",\n\n"
	        + "Por favor verifica tu correo haciendo clic en el siguiente enlace:\n"
	        + "http://localhost:9527/api/verificarEmail?token=" + token
	        + "\n\nEste enlace expirará en 24 horas.");
	    mensaje.setFrom("futboldebarriosevilla@gmail.com");
	    
	    mailSender.send(mensaje);
	}
	
	@Async
	public void generarYEnviarToken(CuentaEntidad cuenta) {
	    TokenVerificacionEmailEntidad token = generarToken(cuenta); 
	    enviarEmailVerificacion(cuenta, token.getToken());         
	}

}
