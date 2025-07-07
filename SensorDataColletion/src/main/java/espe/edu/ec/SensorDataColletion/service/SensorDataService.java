package espe.edu.ec.SensorDataColletion.service;

import espe.edu.ec.SensorDataColletion.exception.InvalidSensorDataException;
import espe.edu.ec.SensorDataColletion.model.SensorReading;
import espe.edu.ec.SensorDataColletion.repository.SensorReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SensorDataService {

    @Autowired
    private SensorReadingRepository sensorReadingRepository;

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    public void processSensorReading(SensorReading sensorReading) throws InvalidSensorDataException {
        validateSensorReading(sensorReading);
        sensorReading.setTimestamp(LocalDateTime.now());
        sensorReadingRepository.save(sensorReading);

        // Emitir evento global
        rabbitMQProducer.sendNewSensorReadingEvent(sensorReading);
    }

    public List<SensorReading> getSensorReadingsBySensorId(String sensorId) {
        return sensorReadingRepository.findBySensorId(sensorId);
    }

    private void validateSensorReading(SensorReading sensorReading) throws InvalidSensorDataException {
        if (sensorReading.getValue() > 60 || sensorReading.getValue() < -50) {
            throw new InvalidSensorDataException("Valor fuera de rango: " + sensorReading.getValue());
        }
    }
}