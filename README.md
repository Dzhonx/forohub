🚀 ForoHub API










📋 Descripción

ForoHub es una API REST desarrollada con Java 17 y Spring Boot 3 que simula el backend de un foro de preguntas y respuestas.

Permite a los usuarios:

🔐 Autenticarse mediante JWT

📝 Crear tópicos

📖 Listarlos con paginación

🔎 Consultar detalles

✏️ Actualizarlos

❌ Eliminarlos

La API sigue buenas prácticas de arquitectura REST, seguridad con Spring Security y control de versiones de base de datos con Flyway.

Proyecto desarrollado como parte del desafío Spring Framework Challenge - Foro Hub (Alura Latam).

🛠️ Tecnologías Utilizadas
Tecnología	Versión	Descripción
Java	17	Lenguaje principal
Spring Boot	3.5.11	Framework backend
Spring Security	6.x	Autenticación y autorización
Spring Data JPA	3.x	Persistencia de datos
JWT (JJWT)	0.11.5	Generación y validación de tokens
MySQL	8.0	Base de datos relacional
Flyway	11.x	Migraciones de base de datos
Lombok	1.18.42	Reducción de boilerplate
Maven	4.x	Gestión de dependencias
✨ Funcionalidades
✅ Implementadas
🔐 Autenticación

Login con usuario y contraseña

Generación de token JWT

Protección de endpoints

📝 CRUD de Tópicos

Crear nuevo tópico

Listar tópicos con paginación

Ver detalle por ID

Actualizar tópico

Eliminar tópico

🔎 Búsqueda avanzada

Filtrar por curso

Filtrar por año de creación

Paginación y ordenamiento dinámico

🛡️ Validaciones

Campos obligatorios

Prevención de tópicos duplicados (mismo título y mensaje)

Autenticación requerida en endpoints protegidos

📌 Endpoints
Método	Endpoint	Descripción	Requiere Token
POST	/auth	Login de usuario	❌
GET	/topicos	Listar tópicos	✅
GET	/topicos/{id}	Detalle de tópico	✅
POST	/topicos	Crear tópico	✅
PUT	/topicos/{id}	Actualizar tópico	✅
DELETE	/topicos/{id}	Eliminar tópico	✅
GET	/topicos/buscar	Buscar por curso y/o año	✅
🗄️ Modelo de Datos
👤 Usuario
- id (Long, PK)
- login (String, único)
- password (String, encriptado con BCrypt)
📌 Tópico
- id (Long, PK)
- titulo (String, obligatorio)
- mensaje (String, obligatorio)
- fecha_creacion (LocalDateTime)
- status (String) [ABIERTO / CERRADO]
- autor (String, obligatorio)
- curso (String, obligatorio)
⚙️ Configuración y Ejecución
🔹 Prerrequisitos

Java JDK 17+

Maven 4+

MySQL 8+

Git

🔹 Variables de Entorno (Opcional)
DB_URL=jdbc:mysql://localhost:3306/forohub
DB_USER=root
DB_PASSWORD=tu_contraseña
JWT_SECRET=tu_clave_secreta_jwt
🔹 Instalación
# 1. Clonar repositorio
git clone https://github.com/Dzhonx/forohub.git
cd forohub

# 2. Crear base de datos
mysql -u root -p
CREATE DATABASE forohub;
exit;

# 3. Ejecutar aplicación
mvn spring-boot:run
🔹 application.properties
# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/forohub
spring.datasource.username=root
spring.datasource.password=TU_CONTRASEÑA

# JPA
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

# Flyway
spring.flyway.enabled=true

# JWT
api.security.token.secret=super-secret-key-1234567890123456
🧪 Uso de la API
🔐 1. Autenticación
POST http://localhost:8080/auth
Content-Type: application/json

{
  "login": "admin",
  "password": "123456"
}

Respuesta:

eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTc0MDYxMjM0NX0...
📝 2. Crear un Tópico
POST http://localhost:8080/topicos
Authorization: Bearer TU_TOKEN
Content-Type: application/json

{
  "titulo": "Duda sobre Spring Boot",
  "mensaje": "¿Cómo configurar Spring Security con JWT?",
  "autor": "Juan Pérez",
  "curso": "Spring Framework"
}
📖 3. Listar con Paginación
GET http://localhost:8080/topicos?page=0&size=10&sort=fechaCreacion,desc
Authorization: Bearer TU_TOKEN
🔎 4. Buscar por Curso y Año
GET http://localhost:8080/topicos/buscar?curso=Spring&anio=2026
Authorization: Bearer TU_TOKEN
📁 Estructura del Proyecto
forohub/
├── src/
│   ├── main/
│   │   ├── java/com/forohub/
│   │   │   ├── controller/
│   │   │   ├── domain/
│   │   │   ├── repository/
│   │   │   ├── security/
│   │   │   └── ForohubApplication.java
│   │   └── resources/
│   │       ├── db/migration/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
🧪 Pruebas

Puedes probar la API con:

Insomnia (recomendado)

Postman

cURL

👨‍💻 Autor

Dzhonx
GitHub: https://github.com/Dzhonx

📄 Licencia

Este proyecto está bajo la licencia MIT.
Consulta el archivo LICENSE para más información.

🙌 Agradecimientos

Alura Latam

Oracle Next Education

Spring Boot

⭐ Si te gustó el proyecto, ¡no olvides darle una estrella en GitHub!
