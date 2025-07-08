package espe.edu.ec.EnvironmentalAnalyzer.scheluder;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduledTasks {

    @Scheduled(cron = "0 0 0 * * *") // Cada 24 horas
    public void generateDailyReport() {
        // Lógica para generar reporte diario
        System.out.println("Generando reporte diario...");
    }

    @Scheduled(cron = "0 0 */6 * * *") // Cada 6 horas
    public void checkInactiveSensors() {
        // Lógica para verificar sensores inactivos
        System.out.println("Verificando sensores inactivos...");
    }
}
