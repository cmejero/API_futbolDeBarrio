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


/**
 * Servicio encargado de la verificación de correos electrónicos de las cuentas.
 */
@Service
public class VerificacionEmailFuncionalidad {
	@Autowired
	private TokenVerificacionEmailInterfaz tokenVerificacionEmailInterfaz;

	@Autowired
	private CuentaInterfaz cuentaInterfaz;
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * Genera y guarda un token de verificación de email para una cuenta determinada.
	 * El token expira 24 horas después de su creación.
	 *
	 * @param cuenta CuentaEntidad a la que se asociará el token.
	 * @return TokenVerificacionEmailEntidad generado y guardado en la base de datos.
	 */
	public TokenVerificacionEmailEntidad generarToken(CuentaEntidad cuenta) {
		TokenVerificacionEmailEntidad token = new TokenVerificacionEmailEntidad();
		token.setCuenta(cuenta);
		token.setToken(UUID.randomUUID().toString());
		token.setFechaExpiracion(LocalDateTime.now().plusHours(24));
		return tokenVerificacionEmailInterfaz.save(token);
	}

	/**
	 * Verifica un token de email y marca la cuenta asociada como verificada si es válido.
	 *
	 * @param tokenString Token de verificación recibido.
	 * @return Mensaje indicando si la verificación fue exitosa o si el token expiró.
	 * @throws RuntimeException si el token no existe o es inválido.
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
	
	
	/**
	 * Envía un correo electrónico de verificación a la cuenta especificada con el token proporcionado.
	 *
	 * @param cuenta CuentaEntidad que recibirá el correo de verificación.
	 * @param token Token de verificación que se incluirá en el enlace del email.
	 */
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
	/**
	 * Genera un token de verificación para la cuenta y envía el email correspondiente de forma asíncrona.
	 *
	 * @param cuenta CuentaEntidad a la que se asociará el token y se enviará el correo.
	 */
	public void generarYEnviarToken(CuentaEntidad cuenta) {
	    TokenVerificacionEmailEntidad token = generarToken(cuenta); 
	    enviarEmailVerificacion(cuenta, token.getToken());         
	}

}
