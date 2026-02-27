ForoHub API
https://img.shields.io/badge/Java-17-blue
https://img.shields.io/badge/Spring%2520Boot-3.5.11-brightgreen
https://img.shields.io/badge/MySQL-8.0-orange
https://img.shields.io/badge/JWT-JSON%2520Web%2520Token-purple
https://img.shields.io/badge/License-MIT-yellow

📋 Descripción
ForoHub es una API REST desarrollada en Java con Spring Boot que simula el backend de un foro de preguntas y respuestas. Los usuarios pueden autenticarse, crear tópicos, listarlos, actualizarlos y eliminarlos. La API implementa autenticación mediante JWT (JSON Web Token) y sigue las mejores prácticas de desarrollo con Spring.

Este proyecto fue desarrollado como parte del desafío "Spring Framework Challenge - Foro Hub" de Alura Latam.

🚀 Tecnologías Utilizadas
Tecnología	Versión	Descripción
Java	17	Lenguaje de programación principal
Spring Boot	3.5.11	Framework para desarrollo de microservicios
Spring Security	6.x	Autenticación y autorización
Spring Data JPA	3.x	Persistencia de datos
JWT (JJWT)	0.11.5	Generación y validación de tokens
MySQL	8.0	Base de datos relacional
Flyway	11.x	Migraciones de base de datos
Lombok	1.18.42	Reducción de código boilerplate
Maven	4.x	Gestión de dependencias
✨ Funcionalidades
✅ Implementadas
Autenticación de usuarios con JWT

CRUD completo de tópicos:

Crear nuevo tópico

Listar todos los tópicos (con paginación)

Ver detalle de un tópico por ID

Actualizar tópico existente

Eliminar tópico

Validaciones:

Campos obligatorios

Tópicos duplicados (mismo título y mensaje)

Autenticación requerida para endpoints protegidos

Búsqueda avanzada:

Filtrar por curso

Filtrar por año de creación

Paginación y ordenamiento

📌 Endpoints disponibles
Método	Endpoint	Descripción	Autenticación
POST	/auth	Login de usuario (obtener token)	❌ No
GET	/topicos	Listar todos los tópicos	✅ Sí
GET	/topicos/{id}	Ver detalle de un tópico	✅ Sí
POST	/topicos	Crear un nuevo tópico	✅ Sí
PUT	/topicos/{id}	Actualizar un tópico	✅ Sí
DELETE	/topicos/{id}	Eliminar un tópico	✅ Sí
GET	/topicos/buscar	Buscar por curso y/o año	✅ Sí
🗄️ Modelo de Datos
Usuario
sql
- id (Long, PK)
- login (String, único)
- password (String, encriptado con BCrypt)
Tópico
sql
- id (Long, PK)
- titulo (String, obligatorio)
- mensaje (String, obligatorio)
- fecha_creacion (LocalDateTime)
- status (String) [ABIERTO/CERRADO]
- autor (String, obligatorio)
- curso (String, obligatorio)
🔧 Configuración y Ejecución
Prerrequisitos
Java JDK 17 o superior

Maven 4+

MySQL 8+

Git

Variables de entorno (opcional)
Crea un archivo .env o configura estas variables en tu sistema:

properties
DB_URL=jdbc:mysql://localhost:3306/forohub
DB_USER=root
DB_PASSWORD=tu_contraseña
JWT_SECRET=tu_clave_secreta_jwt
Instalación
bash
# 1. Clonar el repositorio
git clone https://github.com/Dzhonx/forohub.git
cd forohub

# 2. Configurar la base de datos MySQL
mysql -u root -p
CREATE DATABASE forohub;
exit;

# 3. Configurar application.properties (o usar variables de entorno)
# Edita src/main/resources/application.properties con tus credenciales

# 4. Ejecutar la aplicación
mvn spring-boot:run
Configuración de application.properties
properties
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
📝 Uso de la API
1. Autenticación (obtener token)
http
POST http://localhost:8080/auth
Content-Type: application/json

{
    "login": "admin",
    "password": "123456"
}
Respuesta:

text
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTc0MDYxMjM0NX0...
2. Crear un tópico
http
POST http://localhost:8080/topicos
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...

{
    "titulo": "Duda sobre Spring Boot",
    "mensaje": "¿Cómo configurar Spring Security con JWT?",
    "autor": "Juan Pérez",
    "curso": "Spring Framework"
}
3. Listar tópicos (con paginación)
http
GET http://localhost:8080/topicos?page=0&size=10&sort=fechaCreacion,desc
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
4. Buscar por curso y año
http
GET http://localhost:8080/topicos/buscar?curso=Spring&anio=2026
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
📁 Estructura del Proyecto
text
forohub/
├── src/
│   ├── main/
│   │   ├── java/com/forohub/
│   │   │   ├── controller/          # Controladores REST
│   │   │   │   ├── AuthController.java
│   │   │   │   └── TopicoController.java
│   │   │   ├── domain/               # Entidades
│   │   │   │   ├── topico/
│   │   │   │   │   └── Topico.java
│   │   │   │   └── usuario/
│   │   │   │       └── Usuario.java
│   │   │   ├── repository/           # Repositorios JPA
│   │   │   │   ├── TopicoRepository.java
│   │   │   │   └── UsuarioRepository.java
│   │   │   ├── security/             # Configuración de seguridad
│   │   │   │   ├── AutenticacionService.java
│   │   │   │   ├── SecurityConfigurations.java
│   │   │   │   ├── SecurityFilter.java
│   │   │   │   └── TokenService.java
│   │   │   └── ForohubApplication.java
│   │   └── resources/
│   │       ├── db/migration/          # Migraciones Flyway
│   │       │   └── V1__init.sql
│   │       └── application.properties  # Configuración
│   └── test/                           # Pruebas unitarias
├── .gitignore
├── mvnw, mvnw.cmd
├── pom.xml
└── README.md
🧪 Pruebas
Puedes probar la API con herramientas como:

Insomnia (recomendado)

Postman

cURL

Colección de pruebas para Insomnia/Postman
json
{
  "auth": {
    "login": {
      "method": "POST",
      "url": "http://localhost:8080/auth",
      "body": {
        "login": "admin",
        "password": "123456"
      }
    }
  },
  "topicos": {
    "crear": {
      "method": "POST",
      "url": "http://localhost:8080/topicos",
      "headers": {
        "Authorization": "Bearer {{token}}"
      },
      "body": {
        "titulo": "Mi tópico",
        "mensaje": "Contenido del mensaje",
        "autor": "admin",
        "curso": "Spring"
      }
    },
    "listar": {
      "method": "GET",
      "url": "http://localhost:8080/topicos",
      "headers": {
        "Authorization": "Bearer {{token}}"
      }
    }
  }
}
👨‍💻 Autor
Dzhonx
https://img.shields.io/badge/GitHub-Dzhonx-181717?style=flat&logo=github

📄 Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más información.

🏆 Agradecimientos
Alura Latam por el desafío y el contenido del curso

Spring Boot por el increíble framework

Oracle Next Education por el programa de formación

¡Gracias por visitar mi proyecto! ⭐ Si te gustó, no olvides darle una estrella en GitHub.
