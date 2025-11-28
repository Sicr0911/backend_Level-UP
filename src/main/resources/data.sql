-- data.sql: Inicializa roles
-- H2 soporta CURRENT_TIMESTAMP() o NOW()

INSERT INTO roles (id, name, created_at, updated_at) VALUES (1, 'ROLE_ADMIN', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO roles (id, name, created_at, updated_at) VALUES (2, 'ROLE_USER', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (id, name, categoria, precio, descripcion, imagen, stock)
VALUES
    (1, 'PlayStation 5', 'Consolas', 549990, 'La consola de última generación de Sony, que ofrece gráficos impresionantes y tiempos de carga ultrarrápidos para una experiencia de juego inmersiva.', '/src/IMG/ps5.png', 10),
    (2, 'Auriculares Gamer HyperX Cloud II', 'Audifonos', 79990, 'Proporcionan un sonido envolvente de calidad con un micrófono desmontable y almohadillas de espuma viscoelástica para mayor comodidad.', '/src/IMG/audifonos.png', 25),
    (3, 'Carcassonne', 'Juegos de Mesa', 24990, 'Un juego de colocación de fichas donde los jugadores construyen el paisaje alrededor de la fortaleza medieval de Carcassonne.', '/src/IMG/juegoCarcassonne.png', 50);