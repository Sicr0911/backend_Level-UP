/* --- ROLES (Requisito: Diferenciar Admin, Vendedor y Cliente) --- */
INSERT INTO roles (id, name, created_at, updated_at) VALUES
                                                         (1, 'ROLE_ADMIN', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
                                                         (2, 'ROLE_VENDEDOR', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
                                                         (3, 'ROLE_CLIENTE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

/* --- PRODUCTOS (Requisito: Caso Gamer Level-Up) --- */
/* Nota: Se agrega columna stock_critico exigida en Reglas de Negocio */

INSERT INTO products (id, name, categoria, precio, descripcion, imagen, stock, stock_critico) VALUES
                                                                                                  (1, 'Fortnite', 'Battle Royale', 0, 'El Battle Royale más famoso del mundo. Construye y sobrevive.', '/IMG/fortnite.jpg', 100, 10),
                                                                                                  (2, 'Minecraft', 'Aventura', 2695, 'Explora mundos infinitos y construye todo lo que puedas imaginar.', '/IMG/minecraft.jpg', 50, 5),
                                                                                                  (3, 'Red Dead Redemption 2', 'Acción', 5999, 'Una aventura épica en el corazón de América.', '/IMG/rdr2.jpg', 20, 2),
                                                                                                  (4, 'Among Us', 'Casual', 499, 'Un juego de trabajo en equipo y traición en el espacio.', '/IMG/amongus.jpg', 200, 20),
                                                                                                  (5, 'The Witcher 3', 'RPG', 3999, 'Conviértete en un cazador de monstruos profesional.', '/IMG/witcher3.jpg', 30, 5),
                                                                                                  (6, 'Hollow Knight', 'Metroidvania', 1499, 'Forja tu propio camino en Hallownest.', '/IMG/hollow.jpg', 15, 3),
                                                                                                  (7, 'Animal Crossing', 'Simulación', 5999, 'Crea tu propia isla paradisíaca y haz amigos.', '/IMG/animal.jpg', 60, 10);