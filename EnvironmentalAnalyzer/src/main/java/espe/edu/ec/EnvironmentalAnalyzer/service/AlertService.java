package espe.edu.ec.EnvironmentalAnalyzer.service;

import java.util.List;

import espe.edu.ec.EnvironmentalAnalyzer.model.Alert;
import espe.edu.ec.EnvironmentalAnalyzer.repository.AlertRepository;
import espe.edu.ec.EnvironmentalAnalyzer.dto.NewSensorReadingEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    public void analyzeAndGenerateAlert(NewSensorReadingEventDTO event) {
        // Lógica de análisis (ejemplo básico)
        if (event instanceof NewSensorReadingEventDTO) {
            NewSensorReadingEventDTO reading = (NewSensorReadingEventDTO) event;

            if (reading.getType().equals("temperature") && reading.getValue() > 40.0) {
                generateHighTemperatureAlert(reading);
            } else if (reading.getType().equals("humidity") && reading.getValue() < 20.0) {
                generateLowHumidityAlert(reading);
            }
        }
    }

    private void generateHighTemperatureAlert(NewSensorReadingEventDTO reading) {
        Alert alert = new Alert();
        alert.setAlertId(UUID.randomUUID().toString());
        alert.setType("HighTemperatureAlert");
        alert.setSensorId(reading.getSensorId());
        alert.setValue(reading.getValue());
        alert.setThreshold(40.0);
        alert.setTimestamp(LocalDateTime.now());

        alertRepository.save(alert);
    }

    // Método para obtener todas las alertas
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }
    
    private void generateLowHumidityAlert(NewSensorReadingEventDTO reading) {
        Alert alert = new Alert();
        alert.setAlertId(UUID.randomUUID().toString());
        alert.setType("LowHumidityWarning");
        alert.setSensorId(reading.getSensorId());
        alert.setValue(reading.getValue());
        alert.setThreshold(20.0);
        alert.setTimestamp(LocalDateTime.now());
        alertRepository.save(alert);
    }
}
