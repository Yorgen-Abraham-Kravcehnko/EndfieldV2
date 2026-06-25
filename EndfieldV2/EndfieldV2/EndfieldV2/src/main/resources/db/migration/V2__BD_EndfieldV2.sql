-- ============================================================
--  ENDFIELD V2 - Script de migración Flyway
--  Archivo : V2__BD_EndfieldV2.sql
--  Motor   : MySQL
-- ============================================================
--
--  Descripción:
--  Segunda migración del esquema. Se realizaron modificaciones
--  estructurales para soportar las reglas de negocio del proyecto:
--
--  Cambios en tablas existentes:
--  - parque     : se agrega FK hacia tipo_parque (reemplaza VARCHAR tipo_parque)
--  - servicio   : se agrega campo capacidad_maxima (INT, default 8)
--
--  Nuevas tablas:
--  - tipo_parque          : catálogo de tipos de parque (NIEVE, PLAYA, etc.)
--  - tipo_servicio_parque : relación entre servicios y tipos de parque
--                           compatibles, para validar coherencia de reservas
--
--  Reglas de negocio que esta estructura soporta (validadas en Servicio):
--  1. Fecha de reserva no puede superar 60 días desde hoy
--  2. No se puede reservar en un parque cerrado por temporada
--  3. Capacidad máxima por cabaña: 8 personas
--  4. Máximo 2 cabañas por reservación
--  5. Solo se pueden reservar servicios compatibles con el tipo de parque
--
-- ============================================================

-- ------------------------------------------------------------
--  PASO 1 - TIPO_PARQUE
-- ------------------------------------------------------------
CREATE TABLE tipo_parque (
    id_tipo_parque  INT          NOT NULL AUTO_INCREMENT,
    nombre          VARCHAR(100) NOT NULL,
    descripcion     VARCHAR(255),
    CONSTRAINT pk_tipo_parque PRIMARY KEY (id_tipo_parque)
);

-- ------------------------------------------------------------
--  PASO 2 - TIPO_SERVICIO_PARQUE
-- ------------------------------------------------------------
CREATE TABLE tipo_servicio_parque (
    id_tipo_servicio  INT NOT NULL,
    id_tipo_parque    INT NOT NULL,
    CONSTRAINT pk_tipo_servicio_parque     PRIMARY KEY (id_tipo_servicio, id_tipo_parque),
    CONSTRAINT fk_tsp_tipo_servicio        FOREIGN KEY (id_tipo_servicio) REFERENCES tipo_servicio (id_tipo_servicio),
    CONSTRAINT fk_tsp_tipo_parque          FOREIGN KEY (id_tipo_parque)   REFERENCES tipo_parque (id_tipo_parque)
);

-- ------------------------------------------------------------
--  PASO 3 - MODIFICAR PARQUE
-- ------------------------------------------------------------
ALTER TABLE parque ADD COLUMN id_tipo_parque INT;
ALTER TABLE parque ADD CONSTRAINT fk_parque_tipo_parque
    FOREIGN KEY (id_tipo_parque) REFERENCES tipo_parque (id_tipo_parque);

-- ------------------------------------------------------------
--  PASO 4 - MODIFICAR SERVICIO
-- ------------------------------------------------------------
ALTER TABLE servicio ADD COLUMN capacidad_maxima INT DEFAULT 8;

-- ------------------------------------------------------------
--  PASO 5 - SEED DATA
-- ------------------------------------------------------------

-- Tipos de parque
INSERT INTO tipo_parque (nombre, descripcion) VALUES
    ('NIEVE',    'Parques con ecosistema de nieve y glaciares'),
    ('PLAYA',    'Parques costeros y de litoral'),
    ('BOSQUE',   'Parques con ecosistema de bosque nativo'),
    ('MONTAÑA',  'Parques con ecosistema de cordillera y montaña'),
    ('DESIERTO', 'Parques con ecosistema desértico');

-- Relaciones tipo_servicio_parque
INSERT INTO tipo_servicio_parque (id_tipo_servicio, id_tipo_parque) VALUES
    (1, 1), (1, 2), (1, 3), (1, 4), (1, 5),  -- Cabaña
    (2, 1), (2, 2), (2, 3), (2, 4), (2, 5),  -- Camping
    (3, 1), (3, 3), (3, 4),                   -- Refugio
    (4, 1), (4, 2), (4, 3), (4, 4), (4, 5),  -- Restaurant
    (5, 1), (5, 2), (5, 3), (5, 4), (5, 5),  -- Cafetería
    (6, 2), (6, 3), (6, 4), (6, 5),           -- Picnic
    (7, 1), (7, 2), (7, 3), (7, 4), (7, 5),  -- Bus Interno
    (8, 2), (8, 3),                            -- Bote
    (9, 2), (9, 3), (9, 4), (9, 5),           -- Bicicleta
    (10, 3), (10, 4), (10, 5),                -- Senderismo
    (11, 1), (11, 4),                          -- Escalada
    (12, 2), (12, 3),                          -- Kayak
    (13, 1), (13, 2), (13, 3), (13, 4), (13, 5), -- Avistamiento
    (14, 1), (14, 2), (14, 3), (14, 4), (14, 5), -- Estacionamiento
    (15, 1), (15, 2), (15, 3), (15, 4), (15, 5); -- Guardaparques