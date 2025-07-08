package espe.edu.ec.EnvironmentalAnalyzer.repository;


import espe.edu.ec.EnvironmentalAnalyzer.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
}