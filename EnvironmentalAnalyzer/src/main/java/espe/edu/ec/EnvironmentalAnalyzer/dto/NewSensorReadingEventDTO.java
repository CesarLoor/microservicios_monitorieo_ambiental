package espe.edu.ec.EnvironmentalAnalyzer.dto;


import java.time.LocalDateTime;

public class NewSensorReadingEventDTO {

    private String sensorId;
    private String type;
    private Double value;
    private LocalDateTime timestamp;

    // Getters y Setters
    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
