# 🧠 ForoHub

ForoHub es una API REST desarrollada en Java con Spring Boot que permite gestionar tópicos de discusión, usuarios y respuestas. Es ideal para simular el backend de un foro de programación o aprendizaje.

---

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Security con JWT
- MySQL 8
- Flyway (migraciones)
- Swagger (OpenAPI 3)
- Maven

---

## 🔧 Funcionalidades principales

- 🔐 Autenticación con JWT
- 🧑 Registro de usuarios
- 📝 CRUD de tópicos
- 💬 CRUD de respuestas
- 📄 Documentación interactiva con Swagger UI
- ⚠️ Gestión centralizada de errores

---

## 🌐 Endpoints principales

| Método | Ruta              | Descripción                    | Autenticación |
|--------|-------------------|--------------------------------|---------------|
| POST   | `/login`          | Inicia sesión y entrega token  | ❌ No         |
| POST   | `/usuarios`       | Registra nuevo usuario         | ❌ No         |
| GET    | `/topico`         | Lista todos los tópicos        | ✅ Sí         |
| POST   | `/topico`         | Crea un nuevo tópico           | ✅ Sí         |
| PUT    | `/topico`         | Actualiza un tópico            | ✅ Sí         |
| DELETE | `/topico/{id}`    | Elimina un tópico (lógico)     | ✅ Sí         |
| GET    | `/topico/{id}`    | Consulta un tópico por ID      | ✅ Sí         |
| GET    | `/respuestas`     | Lista todas las respuestas     | ✅ Sí         |
| POST   | `/respuestas`     | Registra una nueva respuesta   | ✅ Sí         |

---

## 📄 Swagger

Accede a la documentación interactiva en:  
👉 [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html)

> ⚠️ Recuerda: haz login en `/login`, copia el token y presiona **"Authorize"** para probar los endpoints protegidos.

---

## ⚙️ Configuración

Asegúrate de tener configuradas tus credenciales en `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost/foroHub_db
spring.datasource.username=root
spring.datasource.password=Gestionbd31$
spring.jpa.hibernate.ddl-auto=validate
spring.flyway.enabled=true
▶️ Cómo ejecutar
Clona el repositorio.

Ejecuta las migraciones con Flyway (se ejecutan automáticamente al iniciar).

Corre la aplicación desde tu IDE o con:

mvn spring-boot:run
Accede a Swagger para probar la API.

🧪 Ejemplos de uso
🔐 Autenticación
POST /login

JSON de entrada:

{
  "login": "Meiby",
  "clave": "123456"
}
Respuesta esperada:

"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
📝 Crear un tópico
POST /topico

{
  "titulo": "¿Qué es un DTO en Java?",
  "mensaje": "No entiendo cómo usarlo en mi proyecto",
  "autor": "Meiby",
  "curso": "Java Backend"
}
💬 Crear una respuesta
POST /respuestas

{
  "mensaje": "Un DTO te ayuda a separar la lógica interna de tu API de los datos que envías.",
  "topicoId": 4,
  "autorId": 1
}
🧪 Comandos curl de ejemplo
📥 Login para obtener token

curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{
    "login": "Meiby",
    "clave": "123456"
  }'
✍ Crear un tópico (requiere token)

curl -X POST http://localhost:8080/topico \
  -H "Authorization: Bearer TU_TOKEN_AQUI" \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "¿Qué es Spring Boot?",
    "mensaje": "No sé cómo iniciar un proyecto",
    "autor": "Meiby",
    "curso": "Spring"
  }'