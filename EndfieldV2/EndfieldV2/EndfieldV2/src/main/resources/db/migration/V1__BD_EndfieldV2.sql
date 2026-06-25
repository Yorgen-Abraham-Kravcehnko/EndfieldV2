-- ============================================================
--  ENDFIELD V2 - Script de migración Flyway
--  Archivo : V1__BD_EndfieldV2.sql
--  Motor   : MySQL
-- ============================================================

-- ------------------------------------------------------------
--  GEOGRAFIA
-- ------------------------------------------------------------
CREATE TABLE region (
    id_region      INT          NOT NULL AUTO_INCREMENT,
    numero_region  VARCHAR(10)  NOT NULL,
    nombre_region  VARCHAR(150) NOT NULL,
    CONSTRAINT pk_region PRIMARY KEY (id_region)
);

CREATE TABLE comuna (
    id_comuna  INT          NOT NULL AUTO_INCREMENT,
    id_region  INT          NOT NULL,
    nombre     VARCHAR(150) NOT NULL,
    CONSTRAINT pk_comuna     PRIMARY KEY (id_comuna),
    CONSTRAINT fk_comuna_reg FOREIGN KEY (id_region) REFERENCES region (id_region)
);

-- ------------------------------------------------------------
--  PARQUE
-- ------------------------------------------------------------
CREATE TABLE parque (
    id_parque            INT           NOT NULL AUTO_INCREMENT,
    nombre               VARCHAR(150)  NOT NULL,
    tipo_parque          VARCHAR(100)  NOT NULL,
    km2                  DECIMAL(10,2),
    max_visitantes       INT,
    cap_estacionamiento  INT,
    abierto_temporada    BOOLEAN       NOT NULL DEFAULT FALSE,
    temporadas           VARCHAR(255),
    CONSTRAINT pk_parque PRIMARY KEY (id_parque)
);

CREATE TABLE direccion_parque (
    id_direccion_parque  INT          NOT NULL AUTO_INCREMENT,
    id_parque            INT          NOT NULL,
    id_comuna            INT          NOT NULL,
    calle                VARCHAR(255) NOT NULL,
    numero               VARCHAR(20),
    referencia           VARCHAR(255),
    CONSTRAINT pk_dir_parque      PRIMARY KEY (id_direccion_parque),
    CONSTRAINT fk_dir_parque_par  FOREIGN KEY (id_parque) REFERENCES parque (id_parque),
    CONSTRAINT fk_dir_parque_com  FOREIGN KEY (id_comuna) REFERENCES comuna (id_comuna)
);

-- ------------------------------------------------------------
--  VISITANTE
-- ------------------------------------------------------------
CREATE TABLE visitante (
    id_visitante      INT          NOT NULL AUTO_INCREMENT,
    nombre            VARCHAR(100) NOT NULL,
    primer_apellido   VARCHAR(100) NOT NULL,
    segundo_apellido  VARCHAR(100),
    rut               VARCHAR(12)  NOT NULL,
    dv                VARCHAR(1)   NOT NULL,
    edad              INT,
    sexo              VARCHAR(20),
    correo            VARCHAR(150) NOT NULL,
    CONSTRAINT pk_visitante  PRIMARY KEY (id_visitante),
    CONSTRAINT uq_visitante_rut UNIQUE (rut)
);

CREATE TABLE direccion_visitante (
    id_direccion_visitante  INT          NOT NULL AUTO_INCREMENT,
    id_visitante            INT          NOT NULL,
    id_comuna               INT          NOT NULL,
    calle                   VARCHAR(255) NOT NULL,
    numero                  VARCHAR(20),
    referencia              VARCHAR(255),
    CONSTRAINT pk_dir_visitante     PRIMARY KEY (id_direccion_visitante),
    CONSTRAINT fk_dir_vis_vis       FOREIGN KEY (id_visitante) REFERENCES visitante (id_visitante),
    CONSTRAINT fk_dir_vis_com       FOREIGN KEY (id_comuna)    REFERENCES comuna (id_comuna)
);

CREATE TABLE perfil_visitante (
    id_perfil_visitante  INT          NOT NULL AUTO_INCREMENT,
    id_visitante         INT          NOT NULL,
    tipo_perfil          VARCHAR(100) NOT NULL,
    descripcion          VARCHAR(255),
    CONSTRAINT pk_perfil_visitante      PRIMARY KEY (id_perfil_visitante),
    CONSTRAINT fk_perfil_vis_vis        FOREIGN KEY (id_visitante) REFERENCES visitante (id_visitante)
);

CREATE TABLE vehiculo (
    id_vehiculo   INT          NOT NULL AUTO_INCREMENT,
    id_visitante  INT          NOT NULL,
    patente       VARCHAR(10)  NOT NULL,
    marca         VARCHAR(100),
    modelo        VARCHAR(100),
    color         VARCHAR(50),
    tipo_vehiculo VARCHAR(50),
    CONSTRAINT pk_vehiculo      PRIMARY KEY (id_vehiculo),
    CONSTRAINT fk_vehiculo_vis  FOREIGN KEY (id_visitante) REFERENCES visitante (id_visitante)
);

-- ------------------------------------------------------------
--  SERVICIOS
-- ------------------------------------------------------------
CREATE TABLE categoria_servicio (
    id_categoria_servicio  INT          NOT NULL AUTO_INCREMENT,
    nombre                 VARCHAR(100) NOT NULL,
    descripcion            VARCHAR(255),
    CONSTRAINT pk_categoria_servicio PRIMARY KEY (id_categoria_servicio)
);

CREATE TABLE tipo_servicio (
    id_tipo_servicio       INT          NOT NULL AUTO_INCREMENT,
    id_categoria_servicio  INT          NOT NULL,
    nombre                 VARCHAR(100) NOT NULL,
    descripcion            VARCHAR(255),
    CONSTRAINT pk_tipo_servicio      PRIMARY KEY (id_tipo_servicio),
    CONSTRAINT fk_tipo_serv_cat      FOREIGN KEY (id_categoria_servicio) REFERENCES categoria_servicio (id_categoria_servicio)
);

CREATE TABLE servicio (
    id_servicio       INT            NOT NULL AUTO_INCREMENT,
    id_parque         INT            NOT NULL,
    id_tipo_servicio  INT            NOT NULL,
    cantidad_disponible INT          NOT NULL DEFAULT 0,
    monto             DECIMAL(10,2)  NOT NULL,
    CONSTRAINT pk_servicio       PRIMARY KEY (id_servicio),
    CONSTRAINT fk_servicio_par   FOREIGN KEY (id_parque)        REFERENCES parque (id_parque),
    CONSTRAINT fk_servicio_tipo  FOREIGN KEY (id_tipo_servicio) REFERENCES tipo_servicio (id_tipo_servicio)
);

-- ------------------------------------------------------------
--  RESERVAS
-- ------------------------------------------------------------
CREATE TABLE reserva (
    id_reserva             INT          NOT NULL AUTO_INCREMENT,
    id_visitante           INT          NOT NULL,
    id_parque              INT          NOT NULL,
    cantidad_acompanantes  INT          NOT NULL DEFAULT 0,
    fecha_inicio           DATE         NOT NULL,
    fecha_termino          DATE         NOT NULL,
    tipo_visita            VARCHAR(20)  NOT NULL,
    estado_reserva         VARCHAR(30)  NOT NULL DEFAULT 'PENDIENTE',
    CONSTRAINT pk_reserva       PRIMARY KEY (id_reserva),
    CONSTRAINT fk_reserva_vis   FOREIGN KEY (id_visitante) REFERENCES visitante (id_visitante),
    CONSTRAINT fk_reserva_par   FOREIGN KEY (id_parque)    REFERENCES parque (id_parque)
);

CREATE TABLE reserva_servicio (
    id_reserva        INT  NOT NULL,
    id_servicio       INT  NOT NULL,
    cantidad          INT  NOT NULL DEFAULT 1,
    fecha_servicio    DATE,
    CONSTRAINT pk_reserva_servicio      PRIMARY KEY (id_reserva, id_servicio),
    CONSTRAINT fk_res_serv_reserva      FOREIGN KEY (id_reserva)  REFERENCES reserva (id_reserva),
    CONSTRAINT fk_res_serv_servicio     FOREIGN KEY (id_servicio) REFERENCES servicio (id_servicio)
);

-- ------------------------------------------------------------
--  PAGOS
-- ------------------------------------------------------------
CREATE TABLE banco (
    id_banco          INT          NOT NULL AUTO_INCREMENT,
    nombre_banco      VARCHAR(150) NOT NULL,
    tipo_cuenta       VARCHAR(100),
    num_transaccion   VARCHAR(100),
    CONSTRAINT pk_banco PRIMARY KEY (id_banco)
);

CREATE TABLE pago (
    id_pago      INT            NOT NULL AUTO_INCREMENT,
    id_reserva   INT            NOT NULL,
    tipo_pago    VARCHAR(50)    NOT NULL,
    monto_total  DECIMAL(10,2)  NOT NULL,
    fecha_pago   DATE           NOT NULL,
    estado_pago  VARCHAR(30)    NOT NULL DEFAULT 'PENDIENTE',
    CONSTRAINT pk_pago       PRIMARY KEY (id_pago),
    CONSTRAINT fk_pago_res   FOREIGN KEY (id_reserva) REFERENCES reserva (id_reserva)
);

CREATE TABLE pago_banco (
    id_pago_banco  INT            NOT NULL AUTO_INCREMENT,
    id_pago        INT            NOT NULL,
    id_banco       INT            NOT NULL,
    num_boleta     VARCHAR(100),
    monto          DECIMAL(10,2)  NOT NULL,
    CONSTRAINT pk_pago_banco      PRIMARY KEY (id_pago_banco),
    CONSTRAINT fk_pago_banco_pago FOREIGN KEY (id_pago)   REFERENCES pago (id_pago),
    CONSTRAINT fk_pago_banco_ban  FOREIGN KEY (id_banco)  REFERENCES banco (id_banco)
);

CREATE TABLE pago_efectivo (
    id_pago_efectivo  INT            NOT NULL AUTO_INCREMENT,
    id_pago           INT            NOT NULL,
    tipo_moneda       VARCHAR(50),
    num_boleta        VARCHAR(100),
    monto             DECIMAL(10,2)  NOT NULL,
    CONSTRAINT pk_pago_efectivo      PRIMARY KEY (id_pago_efectivo),
    CONSTRAINT fk_pago_efec_pago     FOREIGN KEY (id_pago) REFERENCES pago (id_pago)
);

-- ------------------------------------------------------------
--  SEGURIDAD
-- ------------------------------------------------------------
CREATE TABLE rol (
    id_rol  INT          NOT NULL AUTO_INCREMENT,
    nombre  VARCHAR(50)  NOT NULL,
    CONSTRAINT pk_rol PRIMARY KEY (id_rol)
);

CREATE TABLE usuario (
    id_usuario    INT          NOT NULL AUTO_INCREMENT,
    id_visitante  INT,
    username      VARCHAR(100) NOT NULL,
    password      VARCHAR(255) NOT NULL,
    activo        BOOLEAN      NOT NULL DEFAULT TRUE,
    CONSTRAINT pk_usuario        PRIMARY KEY (id_usuario),
    CONSTRAINT uq_usuario_user   UNIQUE (username),
    CONSTRAINT fk_usuario_vis    FOREIGN KEY (id_visitante) REFERENCES visitante (id_visitante)
);

CREATE TABLE usuario_rol (
    id_usuario  INT NOT NULL,
    id_rol      INT NOT NULL,
    CONSTRAINT pk_usuario_rol       PRIMARY KEY (id_usuario, id_rol),
    CONSTRAINT fk_usr_rol_usuario   FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario),
    CONSTRAINT fk_usr_rol_rol       FOREIGN KEY (id_rol)     REFERENCES rol (id_rol)
);

-- ============================================================
--  DATOS INICIALES (SEED DATA)
-- ============================================================

-- Roles del sistema
INSERT INTO rol (nombre) VALUES ('ADMIN'), ('OPERADOR'), ('RECEPCIONISTA'), ('INSTRUCTOR');

-- Categorías de servicio
INSERT INTO categoria_servicio (nombre, descripcion) VALUES
    ('ALOJAMIENTO',  'Servicios de hospedaje dentro del parque'),
    ('ALIMENTACION', 'Servicios de alimentación y restauración'),
    ('TRANSPORTE',   'Servicios de movilización interna'),
    ('RECREACION',   'Actividades recreativas y de aventura'),
    ('GENERAL',      'Servicios generales del parque');

-- Tipos de servicio
INSERT INTO tipo_servicio (id_categoria_servicio, nombre, descripcion) VALUES
    (1, 'Cabaña',             'Alojamiento en cabaña con capacidad variable'),
    (1, 'Camping',            'Sitio de camping habilitado'),
    (1, 'Refugio',            'Refugio de montaña'),
    (2, 'Restaurant',         'Servicio de restaurant'),
    (2, 'Cafetería',          'Servicio de cafetería y snacks'),
    (2, 'Picnic',             'Área de picnic equipada'),
    (3, 'Bus Interno',        'Servicio de bus dentro del parque'),
    (3, 'Bote',               'Arriendo de bote'),
    (3, 'Bicicleta',          'Arriendo de bicicleta'),
    (4, 'Senderismo Guiado',  'Tour de senderismo con guía'),
    (4, 'Escalada',           'Servicio de escalada con instructor'),
    (4, 'Kayak',              'Servicio de kayak guiado'),
    (4, 'Avistamiento',       'Tour de avistamiento de fauna'),
    (5, 'Estacionamiento',    'Servicio de estacionamiento'),
    (5, 'Guardaparques',      'Asistencia de guardaparques');

-- Bancos chilenos
INSERT INTO banco (nombre_banco, tipo_cuenta) VALUES
    ('Banco de Chile',         'Cuenta Corriente'),
    ('Banco Estado',           'Cuenta Vista'),
    ('Banco Santander',        'Cuenta Corriente'),
    ('Banco BCI',              'Cuenta Corriente'),
    ('Banco Scotiabank',       'Cuenta Corriente'),
    ('Banco Itaú',             'Cuenta Corriente'),
    ('Banco Security',         'Cuenta Corriente'),
    ('Banco Falabella',        'Cuenta Vista'),
    ('Banco Ripley',           'Cuenta Vista'),
    ('Banco Internacional',    'Cuenta Corriente');

