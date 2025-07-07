package espe.edu.ec.SensorDataColletion.service;

import espe.edu.ec.SensorDataColletion.event.NewSensorReadingEvent;
import espe.edu.ec.SensorDataColletion.model.SensorReading;
import espe.edu.ec.SensorDataColletion.repository.SensorReadingRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SensorReadingService {

    private final SensorReadingRepository sensorReadingRepository;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public SensorReadingService(SensorReadingRepository sensorReadingRepository, RabbitTemplate rabbitTemplate) {
        this.sensorReadingRepository = sensorReadingRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public ResponseEntity<String> addSensorReading(SensorReading reading) {
        try {
            // Validaciones básicas
            if (reading == null) {
                return ResponseEntity.badRequest().body("La lectura del sensor no puede ser nula");
            }
            
            if (reading.getValue() > 60 || reading.getValue() < -50) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Valor fuera de rango. Debe estar entre -50 y 60");
            }

            // Asegurarse de que el timestamp esté establecido
            if (reading.getTimestamp() == null) {
                reading.setTimestamp(LocalDateTime.now());
            }

            // Guardar en la base de datos
            SensorReading savedReading = sensorReadingRepository.save(reading);

            // Emitir evento global
            NewSensorReadingEvent event = new NewSensorReadingEvent(
                    savedReading.getId().toString(),
                    savedReading.getSensorId(),
                    savedReading.getType(),
                    savedReading.getValue(),
                    savedReading.getTimestamp()
            );
            
            rabbitTemplate.convertAndSend(
                "sensor-exchange", 
                "new.reading", 
                event
            );

            return ResponseEntity.ok("Lectura almacenada correctamente");
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la lectura del sensor: " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<SensorReading> getSensorReadings(String sensorId) {
        return sensorReadingRepository.findBySensorId(sensorId);
    }
}