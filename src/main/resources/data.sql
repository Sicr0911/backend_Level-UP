-- data.sql: Inicializa roles
-- H2 soporta CURRENT_TIMESTAMP() o NOW()

INSERT INTO roles (id, name, created_at, updated_at) VALUES (1, 'ROLE_ADMIN', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO roles (id, name, created_at, updated_at) VALUES (2, 'ROLE_USER', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());