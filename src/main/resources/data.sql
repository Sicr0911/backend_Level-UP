-- data.sql: Inicializa roles para Spring Security

-- Insertar ROLE_ADMIN
INSERT INTO roles (id, name, created_at, updated_at) VALUES (1, 'ROLE_ADMIN', NOW(), NOW())
    ON DUPLICATE KEY UPDATE name=name;

-- Insertar ROLE_USER
INSERT INTO roles (id, name, created_at, updated_at) VALUES (2, 'ROLE_USER', NOW(), NOW())
    ON DUPLICATE KEY UPDATE name=name;