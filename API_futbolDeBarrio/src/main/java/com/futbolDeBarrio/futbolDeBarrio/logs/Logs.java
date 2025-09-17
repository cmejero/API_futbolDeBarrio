package com.futbolDeBarrio.futbolDeBarrio.logs;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.futbolDeBarrio.futbolDeBarrio.utilidades.Utilidades;

/**
 * Clase que se encarga de escribir registros (logs) en archivos.
 */
public class Logs {

    // Formato para la fecha y hora del log
    private static final DateTimeFormatter FORMATEADOR_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Ruta base donde se almacenarán los logs
    private static final String RUTA_BASE_LOGS = "C:\\Users\\Carlo\\OneDrive\\Escritorio\\FICHEROS\\apiFutbolDeBarrioLog\\";

    /**
     * Método que se encarga de escribir un mensaje en un archivo log.
     * Crea la carpeta y el archivo si no existen, y añade el mensaje con fecha y hora.
     *
     * @param mensaje Texto que se desea registrar.
     */
    public static void ficheroLog(String mensaje) {
        try {
            String marcaDeTiempo = LocalDateTime.now().format(FORMATEADOR_FECHA);
            String entradaLog = "[" + marcaDeTiempo + "] " + mensaje;

            String nombreCarpeta = Utilidades.nombreCarpetaFecha();
            String nombreArchivo = Utilidades.nombreArchivoLog();

            String rutaCarpeta = RUTA_BASE_LOGS + nombreCarpeta;
            File carpeta = new File(rutaCarpeta);
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            String rutaArchivo = rutaCarpeta + File.separator + nombreArchivo;
            FileWriter escritor = new FileWriter(rutaArchivo, true);
            escritor.write(entradaLog + "\n");
            escritor.close();

        } catch (Exception e) {
            // Se puede registrar el error o dejarlo silencioso según necesidad
            // System.out.println("Error al escribir en el log: " + e.getMessage());
        }
    }
}
