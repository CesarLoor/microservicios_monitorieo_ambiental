package espe.edu.ec.SensorDataColletion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "sensor_readings")
public class SensorReading {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(updatable = false, nullable = false)
    private UUID id;

    @NotNull(message = "El ID del sensor es obligatorio")
    @Column(nullable = false)
    private String sensorId;

    @NotNull(message = "El tipo de sensor es obligatorio")
    @Column(nullable = false)
    private String type;

    @NotNull(message = "El valor del sensor es obligatorio")
    @Column(nullable = false)
    private Double value;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime timestamp;

    @Version
    private Long version;
    
    public SensorReading() {
        this.timestamp = LocalDateTime.now();
    }
    
    public SensorReading(String sensorId, String type, Double value) {
        this.sensorId = sensorId;
        this.type = type;
        this.value = value;
        this.timestamp = LocalDateTime.now();
    }
}