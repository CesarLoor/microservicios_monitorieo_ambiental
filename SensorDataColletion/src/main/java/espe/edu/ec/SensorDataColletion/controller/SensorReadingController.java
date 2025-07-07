package espe.edu.ec.SensorDataColletion.controller;

import espe.edu.ec.SensorDataColletion.model.SensorReading;
import espe.edu.ec.SensorDataColletion.service.SensorReadingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/conjunta/2p/sensor-readings")
public class SensorReadingController {

    private final SensorReadingService sensorReadingService;

    @Autowired
    public SensorReadingController(SensorReadingService sensorReadingService) {
        this.sensorReadingService = sensorReadingService;
    }

    @PostMapping
    public ResponseEntity<?> addSensorReading(
            @Valid @RequestBody SensorReading reading,
            BindingResult result) {
        
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        
        return sensorReadingService.addSensorReading(reading);
    }

    @GetMapping("/{sensorId}")
    public ResponseEntity<?> getSensorReadings(@PathVariable String sensorId) {
        List<SensorReading> readings = sensorReadingService.getSensorReadings(sensorId);
        if (readings.isEmpty()) {
            return ResponseEntity.ok("No se encontraron lecturas para el sensor con ID: " + sensorId);
        }
        return ResponseEntity.ok(readings);
    }
}