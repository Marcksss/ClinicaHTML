package HerramientasDesarrollo.Herramientas;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DatabaseBackupService {

    private static final Logger logger = Logger.getLogger(DatabaseBackupService.class.getName());

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${backup.directory}")
    private String backupDirectory;

    private static final String DATABASE_NAME = "dbprueba";

    // Ejecuta backup cada sábado a las 4:00 AM
    @Scheduled(cron = "0 0 4 * * SAT")
    public void createWeeklyBackup() {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String backupFileName = String.format("%s_backup_%s.sql", DATABASE_NAME, timestamp);
            String backupPath = backupDirectory + "/" + backupFileName;

            new File(backupDirectory).mkdirs();

            ProcessBuilder processBuilder = new ProcessBuilder(
              	"D:\\Marco de Desarroyo Proyecto final\\pgsql\\pgsql\\pgAdmin 4\\runtime\\pg_dump.exe",
                "-h", "localhost",
                "-U", dbUsername,
                "-d", DATABASE_NAME,
                "-f", backupPath
            );

            processBuilder.environment().put("PGPASSWORD", dbPassword);

            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
            	
            	 System.out.println(
            		        "\n" +
            		        "************************************************************\n" +
            		        "*                                                          *\n" +
            		        "*              ✅ BACKUP CREADO EXITOSAMENTE              *\n" +
            		        "*                                                          *\n" +
            		        "************************************************************\n" +
            		        "*                                                          *\n" +
            		        "*  Se ha generado una copia de seguridad de la base de     *\n" +
            		        "*  datos correctamente. Este respaldo asegura que los      *\n" +
            		        "*  datos clínicos y administrativos de los     *\n" +
            		        "*  pacientes estén protegidos ante cualquier fallo.        *\n" +
            		        "*                                                          *\n" +
            		        "*  El archivo ha sido guardado :                           *\n" +
            		        "*     ➤ "  + "\n" +
            		        "*                                                          *\n" +
            		        "*  Recomendamos mantener al menos 4 respaldos recientes y  *\n" +
            		        "*  almacenarlos en una ubicación segura.                   *\n" +
            		        "*                                                          *\n" +
            		        "*  ¡Gracias por proteger los datos de forma responsable!   *\n" +
            		        "*                                                          *\n" +
            		        "************************************************************\n"
            		    );

                logger.info("Backup logrado: " + backupFileName);
                cleanOldBackups();
            } else {
                System.out.println(
                    "\n" +
                    "************************************************************\n" +
                    "*                                                          *\n" +
                    "*              ❌ ERROR AL CREAR EL BACKUP                *\n" +
                    "*                                                          *\n" +
                    "************************************************************\n" +
                    "*                                                          *\n" +
                    "*  No se pudo completar la copia de seguridad de la base   *\n" +
                    "*  de datos. Esto puede deberse a problemas de conexión,   *\n" +
                    "*  credenciales inválidas, o errores internos del sistema. *\n" +
                    "*                                                          *\n" +
                    "*  Código de salida del proceso: " + exitCode + "          *\n" +
                    "*                                                          *\n" +
                    "*  Verifica los parámetros de conexión, el estado del SGBD *\n" +
                    "*  y que pg_dump esté correctamente configurado.           *\n" +
                    "*                                                          *\n" +
                    "*  Intenta ejecutar el backup manualmente para probar.     *\n" +
                    "*                                                          *\n" +
                    "************************************************************\n"
                );

                logger.severe("Error " + exitCode);
            }


        } catch (IOException | InterruptedException e) {
        	
        	 System.out.println(
        		        "\n" +
        		        "************************************************************\n" +
        		        "*                                                          *\n" +
        		        "*           ❌ EXCEPCIÓN AL EJECUTAR BACKUP               *\n" +
        		        "*                                                          *\n" +
        		        "************************************************************\n" +
        		        "*                                                          *\n" +
        		        "*  Ha ocurrido un error inesperado al intentar crear la     *\n" +
        		        "*  copia de seguridad de la base de datos.                 *\n" +
        		        "*                                                          *\n" +
        		        "*  Por favor, revisa la configuración, permisos y estado   *\n" +
        		        "*  del sistema antes de intentar nuevamente.               *\n" +
        		        "*                                                          *\n" +
        		        "************************************************************\n"
        		    );
        	 
            logger.severe("Error " + e.getMessage());
        }
    }

    private void cleanOldBackups() {
        try {
            File dir = new File(backupDirectory);
            File[] files = dir.listFiles((d, name) -> name.startsWith(DATABASE_NAME + "_backup") && name.endsWith(".sql"));

            if (files != null && files.length > 4) {
                java.util.Arrays.sort(files, java.util.Comparator.comparingLong(File::lastModified));
                for (int i = 0; i < files.length - 4; i++) {
                    if (files[i].delete()) {
                    	
                    	System.out.println(
                    	        "\n" +
                    	        "************************************************************\n" +
                    	        "*                                                          *\n" +
                    	        "*               🗑️ BACKUP ANTIGUO ELIMINADO               *\n" +
                    	        "*                                                          *\n" +
                    	        "************************************************************\n" +
                    	        "*                                                          *\n" +
                    	        "*  Se ha eliminado correctamente el archivo de respaldo    *\n" +
                    	        "*                                                          *\n" +
                    	        "*     " + files[i].getName() + "\n" +
                    	        "*                                                          *\n" +
                    	        "************************************************************\n"
                    	    );
                    	
                        logger.info("Backup antiguo eliminado: " + files[i].getName());
                    }
                }
            }
        } catch (Exception e) {
        	
        	System.out.println(
        	        "\n" +
        	        "************************************************************\n" +
        	        "*                                                          *\n" +
        	        "*          ⚠️ ERROR AL LIMPIAR BACKUPS ANTIGUOS           *\n" +
        	        "*                                                          *\n" +
        	        "************************************************************\n" +
        	        "*                                                          *\n" +
        	        "*  Ocurrió un problema al eliminar copias de seguridad     *\n" +
        	        "*  antiguas. Es recomendable revisar manualmente la        *\n" +
        	        "*  carpeta de backups para evitar acumulación excesiva.    *\n" +
        	        "*                                                          *\n" +
        	        "************************************************************\n"
        	    );
        	
            logger.warning("Error " + e.getMessage());
        }
    }

    public String createManualBackup() {
        try {
            createWeeklyBackup();
            
            System.out.println(
                    "\n" +
                    "************************************************************\n" +
                    "*                                                          *\n" +
                    "*          ✅ BACKUP MANUAL INICIADO EXITOSAMENTE          *\n" +
                    "*                                                          *\n" +
                    "************************************************************\n" +
                    "*                                                          *\n" +
                    "*  Se ha iniciado correctamente la creación manual de la   *\n" +
                    "*  copia de seguridad. Esto asegura que tus datos estén    *\n" +
                    "*  respaldados y protegidos.                               *\n" +
                    "*                                                          *\n" +
                    "*  Por favor espera a que el proceso finalice y verifica   *\n" +
                    "*  la carpeta de backups para confirmar.                   *\n" +
                    "*                                                          *\n" +
                    "************************************************************\n"
                );
            
            return "Backup creado  ";
        } catch (Exception e) {
        	
        	 System.out.println(
        		        "\n" +
        		        "************************************************************\n" +
        		        "*                                                          *\n" +
        		        "*             ❌ ERROR AL CREAR BACKUP MANUAL              *\n" +
        		        "*                                                          *\n" +
        		        "************************************************************\n" +
        		        "*                                                          *\n" +
        		        "*  Ha ocurrido un problema al intentar crear el backup     *\n" +
        		        "*  manualmente. Por favor, verifica la configuración y     *\n" +
        		        "*  vuelve a intentarlo.                                    *\n" +
        		        "*                                                          *\n" +
        		        "************************************************************\n"
        		    );
        	 
            return "Error " + e.getMessage();
        }
    }
}