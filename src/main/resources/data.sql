/* --- 2. ROLES (Requisito: Admin, Vendedor y Cliente) --- */
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_VENDEDOR');
INSERT INTO roles (id, name) VALUES (3, 'ROLE_USER'); -- 'ROLE_USER' suele ser el estándar para clientes

/* --- 3. USUARIOS INICIALES (Requisito para probar el sistema) --- */
/* Password para ambos: '1234' (Hasheado con BCrypt) */
INSERT INTO users (id, username, email, password, rut, enabled) VALUES
                                                                    (1, 'admin', 'admin@duoc.cl', '$2a$10$R/l.Jz.sV4.sV4.sV4.sV4.sV4.sV4.sV4.sV4', '11111111-1', true),
                                                                    (2, 'vendedor', 'vendedor@profesor.duoc.cl', '$2a$10$R/l.Jz.sV4.sV4.sV4.sV4.sV4.sV4.sV4.sV4', '22222222-2', true);

/* Asignar Roles a Usuarios */
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);

INSERT INTO products (id, name, price, description, image_url, stock) VALUES
                                                                          (1, 'Fortnite', 0, 'El Battle Royale más famoso del mundo. Construye y sobrevive.', '/IMG/fortnite.jpg', 100),
                                                                          (2, 'Minecraft', 2695, 'Explora mundos infinitos y construye todo lo que puedas imaginar.', '/IMG/minecraft.jpg', 50),
                                                                          (3, 'Red Dead Redemption 2', 5999, 'Una aventura épica en el corazón de América.', '/IMG/rdr2.jpg', 20),
                                                                          (4, 'Among Us', 499, 'Un juego de trabajo en equipo y traición en el espacio.', '/IMG/amongus.jpg', 200),
                                                                          (5, 'The Witcher 3', 3999, 'Conviértete en un cazador de monstruos profesional.', '/IMG/witcher3.jpg', 30),
                                                                          (6, 'Hollow Knight', 1499, 'Forja tu propio camino en Hallownest.', '/IMG/hollow.jpg', 15),
                                                                          (7, 'Animal Crossing', 5999, 'Crea tu propia isla paradisíaca y haz amigos.', '/IMG/animal.jpg', 60);