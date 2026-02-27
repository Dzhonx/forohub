# 🚀 ForoHub API

![Java](https://img.shields.io/badge/Java-17-blue) ![Spring
Boot](https://img.shields.io/badge/Spring%20Boot-3.5.11-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)
![JWT](https://img.shields.io/badge/JWT-JSON%20Web%20Token-purple)
![License](https://img.shields.io/badge/License-MIT-yellow)

------------------------------------------------------------------------

## 📋 Descripción

**ForoHub** es una API REST desarrollada con **Java 17** y **Spring Boot
3** que simula el backend de un foro de preguntas y respuestas.

Permite a los usuarios:

-   🔐 Autenticarse mediante JWT\
-   📝 Crear tópicos\
-   📖 Listarlos con paginación\
-   🔎 Consultar detalles\
-   ✏️ Actualizarlos\
-   ❌ Eliminarlos

La API sigue buenas prácticas de arquitectura REST, seguridad con Spring
Security y control de versiones de base de datos con Flyway.

------------------------------------------------------------------------

# 🛠️ Tecnologías Utilizadas

  Tecnología        Versión   Descripción
  ----------------- --------- -----------------------------------
  Java              17        Lenguaje principal
  Spring Boot       3.5.11    Framework backend
  Spring Security   6.x       Autenticación y autorización
  Spring Data JPA   3.x       Persistencia de datos
  JWT (JJWT)        0.11.5    Generación y validación de tokens
  MySQL             8.0       Base de datos relacional
  Flyway            11.x      Migraciones de base de datos
  Lombok            1.18.42   Reducción de boilerplate
  Maven             4.x       Gestión de dependencias

------------------------------------------------------------------------

# ✨ Funcionalidades

## ✅ Implementadas

### 🔐 Autenticación

-   Login con usuario y contraseña
-   Generación de token JWT
-   Protección de endpoints

### 📝 CRUD de Tópicos

-   Crear nuevo tópico
-   Listar tópicos con paginación
-   Ver detalle por ID
-   Actualizar tópico
-   Eliminar tópico

### 🔎 Búsqueda avanzada

-   Filtrar por curso
-   Filtrar por año de creación
-   Paginación y ordenamiento dinámico

### 🛡️ Validaciones

-   Campos obligatorios
-   Prevención de tópicos duplicados (mismo título y mensaje)
-   Autenticación requerida en endpoints protegidos

------------------------------------------------------------------------

# 📌 Endpoints

  Método   Endpoint            Descripción                Requiere Token
  -------- ------------------- -------------------------- ----------------
  POST     `/auth`             Login de usuario           ❌
  GET      `/topicos`          Listar tópicos             ✅
  GET      `/topicos/{id}`     Detalle de tópico          ✅
  POST     `/topicos`          Crear tópico               ✅
  PUT      `/topicos/{id}`     Actualizar tópico          ✅
  DELETE   `/topicos/{id}`     Eliminar tópico            ✅
  GET      `/topicos/buscar`   Buscar por curso y/o año   ✅
  
  ------------------------------------------------------------------------
<img width="886" height="166" alt="image" src="https://github.com/user-attachments/assets/3d4a2f5d-54ce-4539-b70a-da4cd326057b" />
<img width="886" height="253" alt="image" src="https://github.com/user-attachments/assets/dddaf9e2-b2a1-4142-9263-65bee48c95ea" />
<img width="886" height="537" alt="image" src="https://github.com/user-attachments/assets/eb9dff88-9878-483e-84d1-8f52fe77484f" />

------------------------------------------------------------------------

# 🗄️ Modelo de Datos

## 👤 Usuario

-   id (Long, PK)
-   login (String, único)
-   password (String, encriptado con BCrypt)

## 📌 Tópico

-   id (Long, PK)
-   titulo (String, obligatorio)
-   mensaje (String, obligatorio)
-   fecha_creacion (LocalDateTime)
-   status (String) \[ABIERTO / CERRADO\]
-   autor (String, obligatorio)
-   curso (String, obligatorio)

------------------------------------------------------------------------

# ⚙️ Configuración y Ejecución

## 🔹 Prerrequisitos

-   Java JDK 17+
-   Maven 4+
-   MySQL 8+
-   Git

------------------------------------------------------------------------

## 🔹 Instalación

``` bash
git clone https://github.com/Dzhonx/forohub.git
cd forohub

mysql -u root -p
CREATE DATABASE forohub;
exit;

mvn spring-boot:run
```

------------------------------------------------------------------------

# 👨‍💻 Autor

**Dzhonx**\
GitHub: https://github.com/Dzhonx

------------------------------------------------------------------------

# 📄 Licencia

Este proyecto está bajo la licencia **MIT**.

------------------------------------------------------------------------

⭐ Si te gustó el proyecto, ¡no olvides darle una estrella en GitHub!
