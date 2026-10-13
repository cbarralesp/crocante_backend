-- Se ejecuta UNA sola vez, conectado como el usuario "postgres" (pgAdmin o psql).
-- Las tablas no se crean a mano: las crea Flyway cuando arranca la aplicación.
CREATE ROLE crocante WITH LOGIN PASSWORD 'crocante';
CREATE DATABASE crocante OWNER crocante ENCODING 'UTF8';
