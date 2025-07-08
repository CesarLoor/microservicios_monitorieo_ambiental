package espe.edu.ec.api_gateway.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Map<String, Object>> home() {
        return Mono.just(createWelcomeResponse());
    }

    private Map<String, Object> createWelcomeResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Bienvenido al API Gateway del Sistema de Monitoreo Ambiental");
        response.put("endpoints", new String[]{
            "GET /api/conjunta/2p/sensor-readings - Obtener lecturas de sensores",
            "GET /api/conjunta/2p/alerts - Obtener alertas ambientales",
            "POST /api/conjunta/2p/notifications - Enviar notificaciones"
        });
        return response;
    }
}
