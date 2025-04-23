package com.futbolDeBarrio.futbolDeBarrio.logs;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.futbolDeBarrio.futbolDeBarrio.utilidades.Utilidades;



public class Logs {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // CAMBIA AQUÍ LA RUTA BASE DE TU LOG
    private static final String RUTA_BASE_LOGS = "C:\\Users\\Carlo\\OneDrive\\Escritorio\\FICHEROS\\apiFutbolDeBarrioLog\\";

    public static void ficheroLog(String mensaje) {
        try {
            String timestamp = LocalDateTime.now().format(formatter);
            String logEntry = "[" + timestamp + "] " + mensaje;

            String nombreCarpeta = Utilidades.nombreCarpetaFecha();
            String nombreArchivo = Utilidades.nombreArchivoLog();

            // Crear carpeta si no existe
            String rutaCarpeta = RUTA_BASE_LOGS + nombreCarpeta;
            File carpeta = new File(rutaCarpeta);
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }
            
            // Crear archivo y escribir log
            String rutaArchivo = rutaCarpeta + File.separator + nombreArchivo;
            FileWriter fw = new FileWriter(rutaArchivo, true);
            fw.write(logEntry + "\n");
            fw.close();

        } catch (Exception e) {
            System.out.println("Se ha producido un error al escribir en el log: " + e.getMessage());
        }
    }
}
