Aquí tienes un README combinado que incluye todos los elementos mencionados, con instrucciones claras sobre cómo configurar, compilar y ejecutar el proyecto tanto desde una IDE como desde la línea de comandos. También he añadido detalles sobre el uso de Eclipse para aquellos que prefieran trabajar en una IDE.

---

# Consulta DNI Java

![](screenshoot.gif)  <!-- Cambia el path por la ubicación de tu GIF -->

Este es un pequeño programa hecho en Java que consulta el DNI y obtiene los nombres de las personas. La API utilizada es proporcionada por un tercero: [apiperu.dev](https://apiperu.dev/).

## Características

- Consulta información de una persona por su DNI.
- Muestra los nombres completos, apellido paterno y materno.
- Uso de una API externa para obtener los datos.

## Requisitos

- Java 8 o superior
- Maven
- Una IDE como **Eclipse** o **IntelliJ** (opcional, pero recomendado)

## Instalación

### 1. Clona este repositorio:

```sh
git clone https://github.com/tu-usuario/tu-repositorio.git
cd tu-repositorio
```

### 2. Configura las variables de entorno:

Crea un archivo `.env` en el directorio raíz del proyecto con el siguiente contenido:

```plaintext
# Token de la API
API_TOKEN=your_bearer_token_here
```

### 3. Construye el proyecto con Maven:

```sh
mvn clean install
```

## Ejecución

### Opción 1: Ejecutar desde la línea de comandos

1. **Compila el proyecto** usando Maven:
   ```sh
   mvn clean package
   ```
   Esto generará un archivo JAR en el directorio `target/`, como `target/buscadorDNI.jar`.

2. **Asegúrate de que el archivo `.env` esté en la raíz del proyecto** (junto al directorio `target`).

3. **Ejecuta el JAR con el siguiente comando**:
   ```sh
   java -jar target/buscadorDNI.jar
   ```

### Opción 2: Ejecutar en Eclipse

1. **Importa el proyecto** en Eclipse:
   - Abre Eclipse.
   - Selecciona `File > Import > Existing Maven Projects`.
   - Navega a la ubicación del proyecto clonado y selecciona la carpeta del proyecto.
   - Haz clic en `Finish` para importar el proyecto.

2. **Configura las variables de entorno**:
   - Asegúrate de que el archivo `.env` esté en la raíz del proyecto.
   - Si Eclipse no reconoce automáticamente las variables de entorno del archivo `.env`, puedes configurar el `Run Configuration` para que use el archivo `.env` o manualmente agregar las variables en la configuración.

3. **Ejecuta el proyecto**:
   - Haz clic derecho en el archivo `Launcher.java`.
   - Selecciona `Run As > Java Application`.

### Opción 3: Ejecutar con doble clic (solo Windows)

1. **Crea un archivo `start-app.bat` en la raíz del proyecto** con el siguiente contenido:
   ```bat
   @echo off
   javaw -jar target/buscadorDNI.jar
   ```

2. **Haz doble clic en `start-app.bat` para iniciar la aplicación**. Esto lanzará la aplicación sin abrir la consola de comandos.

### Opción 4: Crear un ejecutable `.exe`

Si prefieres un ejecutable nativo de Windows:

1. **Usa Launch4j o JSmooth** para crear un archivo `.exe` a partir del JAR.
2. **Configura la herramienta para usar `javaw`** para que la consola no se muestre.

## Uso

1. Ingresa un DNI de 8 dígitos en el campo correspondiente.
2. Haz clic en el botón "Consultar".
3. La información de la persona será mostrada en el área de resultados.

## Contribuciones

Las contribuciones son bienvenidas. Siéntete libre de abrir un `issue` o enviar un `pull request`.

## Licencia

Este proyecto está bajo la Licencia MIT. Para más detalles, consulta el archivo [LICENSE](./LICENSE).

---

Este README ahora cubre cómo clonar el proyecto, configurarlo, y ejecutarlo utilizando diferentes métodos, incluyendo el uso de Eclipse, la línea de comandos y la creación de un ejecutable `.exe` para una experiencia de usuario más sencilla.