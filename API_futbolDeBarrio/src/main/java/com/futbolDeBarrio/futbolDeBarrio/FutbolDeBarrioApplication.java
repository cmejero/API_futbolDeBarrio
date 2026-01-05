package com.futbolDeBarrio.futbolDeBarrio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync

/**
 * Clase principal de la aplicación
 */
public class FutbolDeBarrioApplication {

	/**
	 * Metodo de entrada principal de la aplicación
	 * @param args Argumentos de línea de comandos pasados al iniciar la aplicación.
	 */
	public static void main(String[] args) {
		SpringApplication.run(FutbolDeBarrioApplication.class, args);
	}

	

}
	