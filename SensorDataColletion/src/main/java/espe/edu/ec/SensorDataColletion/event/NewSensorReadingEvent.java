package espe.edu.ec.SensorDataColletion.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewSensorReadingEvent implements Serializable {
    private String eventId;
    private String sensorId;
    private String type;
    private Double value;
    private LocalDateTime timestamp;
}
