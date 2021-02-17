-- use prueba_meli;
--
-- CREATE SEQUENCE instance_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
-- CREATE TABLE instance
-- (
--     id                  BIGINT NOT NULL DEFAULT nextval('instance_seq'::regclass),
--     date                datetime NOT NULL,
--     init_vulcanos       int NOT NULL,
--     init_ferengis       int NOT NULL,
--     init_betasoides     int NOT NULL,
--     years               int NOT NULL,
--     avance_vulcanos     int NOT NULL,
--     avance_ferengis     int NOT NULL,
--     avance_betasoides   int NOT NULL,
--     distance_vulcanos   int NOT NULL,
--     distance_ferengis   int NOT NULL,
--     distance_betasoides int NOT NULL,
--     PRIMARY KEY (id)
-- );
-- CREATE SEQUENCE day_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
-- CREATE TABLE day
-- (
--     id            BIGINT NOT NULL DEFAULT nextval('day_seq'::regclass),
--     day           bigint NOT NULL,
--     determinant   double DEFAULT NULL,
--     max_perimeter bit(1) DEFAULT NULL,
--     perimeter     double DEFAULT NULL,
--     weather       int    NOT NULL,
--     instance_id    bigint NOT NULL,
--     PRIMARY KEY (id),
--     CONSTRAINT FK_day_instance FOREIGN KEY (instance_id) REFERENCES instance(id)
-- );
--
-- CREATE SEQUENCE planet_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
-- CREATE TABLE planet
-- (
--     id              BIGINT NOT NULL DEFAULT nextval('planet_seq'::regclass),
--     code            varchar(3) NOT NULL,
--     name            varchar(50) NOT NULL,
--     PRIMARY KEY (id)
-- );
--
-- CREATE SEQUENCE day_planet_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
-- CREATE TABLE day_planet
-- (
--     id              BIGINT NOT NULL DEFAULT nextval('day_planet_seq'::regclass),
--     angle        int    NOT NULL,
--     x_pos        double NOT NULL,
--     y_pos        double NOT NULL,
--     day_id       bigint NOT NULL,
--     planet_id    bigint NOT NULL,
--     PRIMARY KEY (id),
--     CONSTRAINT FK_day_planet_day FOREIGN KEY (day_id) REFERENCES day(id),
--     CONSTRAINT FK_day_planet_planet FOREIGN KEY (planet_id) REFERENCES planet(id)
-- );
--
--
--
-- insert into planet (code,name) values ('VUL','Vulcanos,');
-- insert into planet (code,name) values ('FER','Ferengis');
-- insert into planet (code,name) values ('BET','Betasoides');

CREATE SEQUENCE instance_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE instance
(
    id                  BIGINT NOT NULL DEFAULT nextval('instance_seq'::regclass),
    date                TIMESTAMP NOT NULL,
    init_vulcanos       int NOT NULL,
    init_ferengis       int NOT NULL,
    init_betasoides     int NOT NULL,
    years               int NOT NULL,
    avance_vulcanos     int NOT NULL,
    avance_ferengis     int NOT NULL,
    avance_betasoides   int NOT NULL,
    distance_vulcanos   int NOT NULL,
    distance_ferengis   int NOT NULL,
    distance_betasoides int NOT NULL,
    PRIMARY KEY (id)
);
CREATE SEQUENCE day_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE day
(
    id            BIGINT NOT NULL DEFAULT nextval('day_seq'::regclass),
    day           bigint NOT NULL,
    determinant   decimal DEFAULT NULL,
    perimeter     decimal DEFAULT NULL,
    weather       int    NOT NULL,
    instance_id    bigint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_day_instance FOREIGN KEY (instance_id) REFERENCES instance(id)
);

CREATE SEQUENCE planet_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE planet
(
    id              BIGINT NOT NULL DEFAULT nextval('planet_seq'::regclass),
    code            varchar(3) NOT NULL,
    name            varchar(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE day_planet_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE day_planet
(
    id              BIGINT NOT NULL DEFAULT nextval('day_planet_seq'::regclass),
    angle        int    NOT NULL,
    x_pos        decimal NOT NULL,
    y_pos        decimal NOT NULL,
    day_id       bigint NOT NULL,
    planet_id    bigint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_day_planet_day FOREIGN KEY (day_id) REFERENCES day(id),
    CONSTRAINT FK_day_planet_planet FOREIGN KEY (planet_id) REFERENCES planet(id)
);



insert into planet (code,name) values ('VUL','Vulcanos,');
insert into planet (code,name) values ('FER','Ferengis');
insert into planet (code,name) values ('BET','Betasoides');



