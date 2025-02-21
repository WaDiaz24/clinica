# API REST para Gestión de Clínica
Esta es una API REST desarrollada con Java Spring Boot, MySQL como base de datos y JWT (JSON Web Tokens) con Auth0 para la autenticación y autorización. 
El sistema permite gestionar citas de pacientes, así como realizar operaciones CRUD para médicos y pacientes.

<h2>Requisitos</h2>
<ul>
  <li>Java JDK 17</li>
  <li>Gradle</li>
  <li>MySQL</li>
  <li>Postman o cualquier cliente REST para probar los endpoints.</li>
</ul>
<br/>
<h2>Configuración</h2>
<h3>1. Clonar el repositorio</h3>

```env
git clone https://github.com/WaDiaz24/clinica.git
```
<h3>2. Configurar la Base de Datos</h3>
<ul>
  <li>Crear una base de datos en MySQL llamada clinica_db o el nombre de tu preferencia</li>
</ul>


### **3. Crear el Archivo `.env`**
En la raíz del proyecto, crea un archivo llamado `.env` y agrega las siguientes variables de entorno con tus credenciales:

```env
# Configuración de la base de datos
DB_URL=jdbc:mysql://localhost:3306/clinica_db
DB_USERNAME=tu_usuario
DB_PASSWORD=tu_contraseña
```

### **4. Clave secreta para generar tokens JWT**

```
# Esta llave debe ir igual en el archivo .env
SECRET_KEY=tu_clave_secreta_segura
```
<h3>⚠️ IMPORTANTE:</h3>
<ul>
  <li>Esta clave debe ser segura y única en cada entorno (desarrollo, pruebas, producción).</li>
  <li>No compartas ni subas este archivo a Git. Está en .gitignore por seguridad.</li>
</ul>
<p>Si todo está configurado correctamente, la aplicación correrá en:</p>

```
http://localhost:8080
```

<h3>📖 Documentación de la API (Swagger - OpenAPI)</h3>
<p>Este proyecto usa Springdoc OpenAPI para generar la documentación interactiva.</p>
<p>📌 Accede a la documentación en Swagger UI:</p>

```
http://localhost:8080/swagger-ui/index.html
```
<p>📌 Ver el JSON de OpenAPI:</p>

```
http://localhost:8080/v3/api-docs
```
<h3>🛠 Tecnologías Utilizadas</h3>
<ul>
  <li>Spring Boot</li>
  <li>Spring Security</li>
  <li>JWT para autenticación - Auth-0</li>
  <li>Spring Data JPA</li>
  <li>MySQL</li>
  <li>Springdoc OpenAPI (Swagger)</li>
</ul>
