package espe.edu.ec.SensorDataColletion.controller;


import espe.edu.ec.SensorDataColletion.exception.InvalidSensorDataException;
import espe.edu.ec.SensorDataColletion.model.SensorReading;
import espe.edu.ec.SensorDataColletion.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conjunta/2p/sensor-readings")
public class SensorDataController {

    @Autowired
    private SensorDataService sensorDataService;

    // POST /sensor-readings
    @PostMapping
    public ResponseEntity<String> addSensorReading(@RequestBody SensorReading sensorReading) {
        try {
            sensorDataService.processSensorReading(sensorReading);
            return ResponseEntity.ok("Lectura de sensor recibida y procesada.");
        } catch (InvalidSensorDataException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /sensor-readings/{sensorId}
    @GetMapping("/{sensorId}")
    public ResponseEntity<List<SensorReading>> getSensorReadings(@PathVariable String sensorId) {
        List<SensorReading> readings = sensorDataService.getSensorReadingsBySensorId(sensorId);
        return ResponseEntity.ok(readings);
    }
}