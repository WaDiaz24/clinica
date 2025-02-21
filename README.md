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

### 4. Clave secreta para generar tokens JWT

```
# Esta llave debe ir igual en el archivo .env
SECRET_KEY=tu_clave_secreta_segura
```

