# Pruebas de Carga para el API Gateway

Este directorio contiene scripts para realizar pruebas de carga al API Gateway utilizando Locust.

## Requisitos Previos

- Python 3.8 o superior
- pip (gestor de paquetes de Python)
- El API Gateway debe estar en ejecución (por defecto en http://localhost:8081)

## Instalación

1. Crea un entorno virtual (opcional pero recomendado):
   ```
   python -m venv venv
   source venv/bin/activate  # En Windows: .\venv\Scripts\activate
   ```

2. Instala las dependencias:
   ```
   pip install -r requirements.txt
   ```

## Ejecución de las Pruebas

1. Navega al directorio de pruebas:
   ```
   cd pruebas
   ```

2. Inicia Locust con la interfaz web:
   ```
   locust -f locustfile.py
   ```

3. Abre tu navegador web y ve a:
   ```
   http://localhost:8089
   ```

4. Configura la prueba:
   - Número de usuarios (users): Número de usuarios simultáneos
   - Spawn rate: Usuarios que se crearán por segundo
   - Host: URL base del API Gateway (por defecto: http://localhost:8081)

5. Haz clic en "Start swarming" para comenzar la prueba

## Configuración Avanzada

### Ejecución sin Interfaz Web

Para ejecutar las pruebas sin la interfaz web (útil para CI/CD):

```bash
locust -f locustfile.py --headless -u 100 -r 10 -t 5m --host=http://localhost:8081
```

- `-u 100`: 100 usuarios
- `-r 10`: 10 usuarios por segundo
- `-t 5m`: Duración de 5 minutos
- `--host`: URL base del API Gateway

### Personalización de las Pruebas

Puedes modificar el archivo `locustfile.py` para:
- Añadir más endpoints
- Cambiar los datos de prueba
- Ajustar los pesos de las tareas (el número entre paréntesis después de `@task`)
- Añadir más lógica de negocio a las pruebas

## Análisis de Resultados

La interfaz web de Locust proporciona varias métricas útiles:

- **RPS**: Peticiones por segundo
- **Tiempo de respuesta**: Promedio, mediana, percentil 95, etc.
- **Número de fallos**: Peticiones fallidas
- **Gráficos en tiempo real**

## Notas Importantes

- Asegúrate de que el API Gateway esté en ejecución antes de iniciar las pruebas
- Las pruebas están configuradas para el entorno local por defecto
- Ajusta los parámetros de carga según la capacidad de tu entorno de pruebas
