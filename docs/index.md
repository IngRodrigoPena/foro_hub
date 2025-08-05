# Documentación de ForoHub

Bienvenido a la documentación técnica de **ForoHub**, una API RESTful para gestionar foros de discusión organizados por cursos y usuarios.

---

## Índice

- [1. Introducción](#1-introducción)
- [2. Entidades del dominio](#2-entidades-del-dominio)
- [3. Diagrama ER](#3-diagrama-er)
- [4. Endpoints principales](#4-endpoints-principales)
- [5. Ejemplos de uso](#5-ejemplos-de-uso)
- [6. Base de datos](#6-base-de-datos)

---

## 1. Introducción

ForoHub permite a los usuarios crear, listar y responder a tópicos dentro de un curso. Cada tópico puede contener respuestas, marcar soluciones y tener un estado.

---

## 2. Entidades del dominio

### Curso
- id
- nombre
- categoría
- activo

### Usuario
- id
- nombre
- correo
- contraseña
- perfil
- activo

### Tópico
- id
- título
- mensaje
- fecha_creación
- status
- curso (FK)
- autor (FK)

### Respuesta
- id
- mensaje
- solución
- fecha
- autor (FK)
- tópico (FK)

---

## 3. Diagrama ER

![Diagrama ER](foro_hub_erd.png)

---

## 4. Endpoints principales

| Método | Endpoint     | Descripción                     |
|--------|--------------|---------------------------------|
| GET    | /topicos     | Listar todos los tópicos        |
| POST   | /topicos     | Crear nuevo tópico              |
| PUT    | /topicos/{id}| Actualizar un tópico existente  |
| DELETE | /topicos/{id}| Eliminar tópico (soft delete)   |

> ⚠️ Endpoints de usuarios, cursos y respuestas están en desarrollo.

---

## 5. Ejemplos de uso

### Crear un tópico

```json
POST /topicos
{
  "titulo": "¿Cómo usar Spring Boot?",
  "mensaje": "Tengo dudas sobre controladores REST.",
  "idCurso": 1
}
```

## 6. Base de datos
Puedes consultar la estructura SQL en README.md
O usar el archivo schema.sql si lo agregas más adelante.

## Autor
Rodrigo Peña

## Licencia
MIT
