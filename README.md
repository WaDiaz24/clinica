# API REST para Gestión de Clínica
Esta es una API REST desarrollada con Java Spring Boot, MySQL como base de datos y JWT (JSON Web Tokens) con Auth0 para la autenticación y autorización. 
El sistema permite gestionar citas de pacientes, así como realizar operaciones CRUD para médicos y pacientes.

<h2>Requisitos</h2>
<ui>
  <li>Java JDK 17</li>
  <li>Gradle</li>
  <li>MySQL</li>
  <li>Postman o cualquier cliente REST para probar los endpoints.</li>
</ui>
<br/>
<h2>Configuración</h2>
<spam>Clonar el repositorio</spam>
<spam>Configurar la Base de Datos</spam>
<h3>Configurar la Base de Datos</h3>
<ul>
  <li>Crear una base de datos en MySQL llamada clinica_db o el nombre de tu preferencia</li>
  <li>En la raíz del proyecto, crea un archivo llamado .env y agrega las siguientes variables de entorno con tus credenciales:</li>
  DB_URL=jdbc:mysql://localhost:3306/clinica_db o el nombre de tu base de datos
DB_USERNAME=tu_usuario
DB_PASSWORD=tu_password
```env
# Configuración de la base de datos
DB_URL=jdbc:mysql://localhost:3306/clinica_db
DB_USERNAME=tu_usuario
DB_PASSWORD=tu_contraseña
</ul>


### **1. Crear el Archivo `.env`**
En la raíz del proyecto, crea un archivo llamado `.env` y agrega las siguientes variables de entorno con tus credenciales:

```env
# Configuración de la base de datos
DB_URL=jdbc:mysql://localhost:3306/clinica_db
DB_USERNAME=tu_usuario
DB_PASSWORD=tu_contraseña
