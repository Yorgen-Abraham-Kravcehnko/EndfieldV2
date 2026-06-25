# EndfieldV2 — Sistema de Gestión de Parques Naturales

## Integrantes
- Luis Carvallo Parada

## Descripción
API REST desarrollada con Spring Boot para la gestión de parques naturales,
visitantes y reservaciones. Implementa arquitectura en capas CSR
(Controlador-Servicio-Repositorio) con persistencia real en MySQL.

## Tecnologías utilizadas
- Java 21
- Spring Boot 4.1.0 RC1
- Spring Data JPA / Hibernate
- MySQL (MariaDB 10.4 via XAMPP)
- Flyway (migraciones de BD)
- Lombok
- Jakarta Validation

## Funcionalidades implementadas
- CRUD completo para 19 entidades
- 3 migraciones Flyway (20 tablas + seed data)
- 7 reglas de negocio en capa Servicio
- DTOs de entrada y respuesta para entidades críticas
- Manejo centralizado de errores con @ControllerAdvice
- Logs estructurados con SLF4J
- Validaciones con Bean Validation (Jakarta)

## Reglas de negocio
1. Visitante no puede tener más de 2 reservas activas simultáneas
2. Cabaña no puede estar ocupada en las mismas fechas
3. Fecha de reserva no puede superar 60 días desde hoy
4. No se puede reservar en un parque cerrado por temporada
5. Capacidad máxima por cabaña: 8 personas
6. Máximo 2 cabañas por reservación
7. Servicio debe ser compatible con el tipo de parque

## Pasos para ejecutar
1. Instalar XAMPP y iniciar MySQL en puerto 3306
2. Crear base de datos: `CREATE DATABASE db_endfield_v2;`
3. Configurar credenciales en `application.properties`:

spring.datasource.url=jdbc:mysql://localhost:3306/db_endfield_v2

spring.datasource.username=root

spring.datasource.password=


4. Ejecutar el proyecto con Maven: `./mvnw spring-boot:run`
5. Flyway ejecutará las migraciones automáticamente
6. API disponible en: `http://localhost:8080`

## Endpoints principales
- `GET/POST /api/visitantes`
- `GET/POST /api/parques`
- `GET/POST /api/reservas`
- `GET/POST /api/reservas-servicio`
- `GET/POST /api/pagos`
- `GET/POST /api/usuarios`




