package espe.edu.ec.SensorDataColletion.repository;

import espe.edu.ec.SensorDataColletion.model.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {
    List<SensorReading> findBySensorId(String sensorId);
}

