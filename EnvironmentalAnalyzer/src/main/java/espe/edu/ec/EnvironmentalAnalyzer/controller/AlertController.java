package espe.edu.ec.EnvironmentalAnalyzer.controller;

import espe.edu.ec.EnvironmentalAnalyzer.model.Alert;
import espe.edu.ec.EnvironmentalAnalyzer.service.AlertService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/conjunta/2p/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @GetMapping
    public ResponseEntity<List<Alert>> getAllAlerts() {
        List<Alert> alerts = alertService.getAllAlerts();
        return ResponseEntity.ok(alerts);
    }
}