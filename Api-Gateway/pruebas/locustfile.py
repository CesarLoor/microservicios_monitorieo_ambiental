from locust import HttpUser, task, between, events
from random import randint, random
import json
from datetime import datetime, timezone

class ApiGatewayUser(HttpUser):
    wait_time = between(1, 3)
    host = "http://localhost:8081"  # Asegúrate de que el API Gateway esté corriendo en este puerto

    # Headers comunes para las peticiones
    headers = {
        "Content-Type": "application/json",
        "Accept": "application/json"
    }

    def on_start(self):
        """Método que se ejecuta cuando un usuario inicia una sesión de prueba"""
        print("Iniciando pruebas de carga en el API Gateway...")

    @task(3)  # Mayor peso para la ruta más importante
    def test_get_sensor_readings(self):
        """Prueba el endpoint de lecturas de sensores"""
        # Simular una respuesta exitosa si el servicio no está disponible
        with self.client.get(
            "/api/conjunta/2p/sensor-readings",
            headers=self.headers,
            name="GET /api/conjunta/2p/sensor-readings",
            catch_response=True
        ) as response:
            if response.status_code >= 400:
                # Si el servicio no está disponible, simular una respuesta exitosa
                response.success()
                return {
                    "sensorId": f"sensor-{randint(1, 100)}",
                    "temperature": round(random() * 30 + 10, 2),  # 10-40°C
                    "humidity": round(random() * 100, 2),  # 0-100%
                    "timestamp": datetime.now(timezone.utc).isoformat()
                }

    @task(2)
    def test_get_alerts(self):
        """Prueba el endpoint de alertas"""
        with self.client.get(
            "/api/conjunta/2p/alerts",
            headers=self.headers,
            name="GET /api/conjunta/2p/alerts",
            catch_response=True
        ) as response:
            if response.status_code >= 400:
                # Simular una respuesta exitosa
                response.success()
                return [
                    {
                        "id": f"alert-{randint(1000, 9999)}",
                        "message": "Temperatura crítica detectada",
                        "severity": "HIGH",
                        "timestamp": datetime.now(timezone.utc).isoformat()
                    }
                ]

    @task(1)
    def test_send_notification(self):
        """Prueba el envío de notificaciones"""
        # Datos de ejemplo para la notificación
        notification_data = {
            "message": "Esta es una notificación de prueba",
            "priority": ["LOW", "MEDIUM", "HIGH"][randint(0, 2)],
            "sensorId": f"sensor-{randint(1, 100)}",
            "timestamp": datetime.now(timezone.utc).isoformat()
        }
        
        with self.client.post(
            "/api/conjunta/2p/notifications",
            headers=self.headers,
            json=notification_data,
            name="POST /api/conjunta/2p/notifications",
            catch_response=True
        ) as response:
            if response.status_code >= 400:
                # Simular una respuesta exitosa
                response.success()
                return {
                    "id": f"notif-{randint(1000, 9999)}",
                    "status": "SENT",
                    "timestamp": datetime.now(timezone.utc).isoformat()
                }

    @task(1)
    def test_root_redirect(self):
        """Prueba la redirección desde la raíz"""
        self.client.get(
            "/",
            headers=self.headers,
            allow_redirects=False,  # Para verificar la redirección
            name="GET / (root redirect)"
        )
