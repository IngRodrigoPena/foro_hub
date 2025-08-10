
# 🗨️ ForoHub - API RESTful para un foro de discusión

![Foro sobre temas de Tecnología, Programación, IA...](docs/assets/58974.jpg)

![Estado](https://img.shields.io/badge/Estado-En%20desarrollo-green)
![Licencia](https://img.shields.io/badge/Licencia-MIT-blue)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-brightgreen)

---

## 📑 Tabla de Contenidos
1. [Descripción](#descripción)
2. [Tecnologías usadas](#tecnologías-usadas)
3. [Instrucciones para correr el proyecto](#instrucciones-para-correr-el-proyecto)
4. [Estructura de la Base de Datos](#estructura-de-la-base-de-datos)
5. [Diagrama Entidad-Relación (DER)](#diagrama-entidad-relación-der)
6. [Script SQL de creación de tablas](#script-sql-para-creación-de-tablas)
7. [Endpoints principales](#endpoints-principales)
8. [Flujo general](#flujo-general)
9. [Autor](#autor)
10. [Licencia](#licencia)

---

## 📌 Descripción

**ForoHub** es una API RESTful que permite gestionar un foro de discusión enfocado en temas de **tecnología, programación e inteligencia artificial**.  
Ofrece endpoints para **crear, listar, actualizar y eliminar** tópicos, cursos, usuarios y respuestas.  
Está desarrollada con **Spring Boot** siguiendo buenas prácticas de arquitectura y seguridad.

---

## 🛠 Tecnologías usadas

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Maven**
- **Insomnia / Postman** (para pruebas de API)

---

## 🚀 Instrucciones para correr el proyecto

### Requisitos previos
- Tener instalado **Java 21** y **Maven**.
- Tener una base de datos **PostgreSQL** en ejecución.
- Configurar credenciales de base de datos en `src/main/resources/application.properties`.

### Pasos
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tuusuario/foro_hub.git
   cd foro_hub
2. Configurar conexión a la base de datos:
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/foro_hub
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```
3. Ejecutar la aplicación:
```
./mvnw spring-boot:run
```
4. Probar los endpoints usando Insomnia o Postman.

## Estructura de la Base de Datos
Tablas y campos principales

| Tabla         | Campos principales                                                                          |
| ------------- | ------------------------------------------------------------------------------------------- |
| **Curso**     | id, nombre, categoria (enum), activo                                                        |
| **Usuario**   | id, nombre, correo, contrasena, perfil (enum), activo                                       |
| **Topico**    | id, titulo, mensaje, fecha\_creacion, status (enum), activo, curso\_id (FK), autor\_id (FK) |
| **Respuesta** | id, mensaje, solucion (bool), fecha, activo, autor\_id (FK), topico\_id (FK)                |

### Relaciones
- Un Curso tiene muchos Tópicos (1:N)

- Un Usuario es autor de muchos Tópicos (1:N)

- Un Tópico tiene muchas Respuestas (1:N)

- Una Respuesta pertenece a un Usuario y a un Tópico


## Diagrama Entidad-Relación (DER)

![Diagrama ER](docs/assets/foro_hub_erd.png)


## Script SQL para creación de tablas

```sql
Copiar
Editar
CREATE TABLE curso (
id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
nombre VARCHAR(255),
categoria VARCHAR(50),
activo BOOLEAN
);

CREATE TABLE usuario (
id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
nombre VARCHAR(255),
correo VARCHAR(255),
contrasena VARCHAR(255),
perfil VARCHAR(50),
activo BOOLEAN
);

CREATE TABLE topico (
id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
titulo VARCHAR(255),
mensaje TEXT,
fecha_creacion TIMESTAMP,
status VARCHAR(50),
activo BOOLEAN,
curso_id BIGINT,
autor_id BIGINT,
FOREIGN KEY (curso_id) REFERENCES curso(id),
FOREIGN KEY (autor_id) REFERENCES usuario(id)
);

CREATE TABLE respuesta (
id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
mensaje TEXT,
solucion BOOLEAN,
fecha TIMESTAMP,
activo BOOLEAN,
autor_id BIGINT,
topico_id BIGINT,
FOREIGN KEY (autor_id) REFERENCES usuario(id),
FOREIGN KEY (topico_id) REFERENCES topico(id)
);
Endpoints principales
Recurso Métodos disponibles
/topicos    GET, POST, PUT, DELETE
/usuarios   (pendiente de implementar)
/cursos (pendiente de implementar)
/respuestas (pendiente de implementar)
```
## 🔗 Endpoints principales
| Recurso       | Métodos disponibles    | Estado         |
| ------------- | ---------------------- | -------------- |
| `/topicos`    | GET, POST, PUT, DELETE | ✅ Implementado |
| `/usuarios`   | GET, POST, PUT, DELETE | 🚧 Pendiente   |
| `/cursos`     | GET, POST, PUT, DELETE | 🚧 Pendiente   |
| `/respuestas` | GET, POST, PUT, DELETE | 🚧 Pendiente   |

## 🔄 Flujo general
1. Los usuarios crean y administran tópicos relacionados con un curso específico.
2. Otros usuarios pueden responder a esos tópicos.
3. Los tópicos tienen un estado (status) para indicar si están abiertos, resueltos, cerrados, etc.

## 👤 Autor
Ing. Rodrigo Peña

## 📜 Licencia
Este proyecto está bajo la licencia MIT.



