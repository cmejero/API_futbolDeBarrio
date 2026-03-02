package com.futbolDeBarrio.futbolDeBarrio.logs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.futbolDeBarrio.futbolDeBarrio.utilidades.Utilidades;

/**
 * Clase que se encarga de escribir registros (logs) en archivos.
 */
public class Logs {

    private static final DateTimeFormatter FORMATEADOR_FECHA =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Ruta configurable (Linux por defecto)
    private static final String RUTA_BASE_LOGS =
            System.getProperty("log.path", "/var/log/futboldebarrio/api");

    public static void ficheroLog(String mensaje) {

        String marcaDeTiempo = LocalDateTime.now().format(FORMATEADOR_FECHA);
        String entradaLog = "[" + marcaDeTiempo + "] " + mensaje;

        try {

            String nombreCarpeta = Utilidades.nombreCarpetaFecha();
            String nombreArchivo = Utilidades.nombreArchivoLog();

            String rutaCarpeta = RUTA_BASE_LOGS + File.separator + nombreCarpeta;

            File carpeta = new File(rutaCarpeta);
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            File archivo = new File(rutaCarpeta + File.separator + nombreArchivo);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
                writer.write(entradaLog);
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error al escribir en el log: " + e.getMessage());
        }
    }
}