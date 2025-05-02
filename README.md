<!-- ETIQUETAS / BADGES -->
![Java](https://img.shields.io/badge/Java-17-blue)
![Swing](https://img.shields.io/badge/Swing-GUI-orange)
![MySQL](https://img.shields.io/badge/MySQL-Database-green)
![CRUD](https://img.shields.io/badge/CRUD-Operations-yellow)
![JFreeChart](https://img.shields.io/badge/JFreeChart-Graphs-red)
![Apache2 license](https://img.shields.io/github/license/Naereen/AppStatus.svg)

# 🧑‍💻 Proyecto CRUD Persona con Gráficos

## 📄 Descripción

Este es un proyecto de escritorio desarrollado en **Java** que permite realizar operaciones **CRUD (Crear, Leer, Actualizar, Eliminar)** sobre una tabla de personas almacenada en una base de datos **MySQL**. Además, ofrece funcionalidades adicionales como:

- Generación de reportes en tablas (`JTable`)
- Visualización de gráficos circulares 3D basados en los pesos de las personas (`JFreeChart`)
- Validación de campos de entrada
- Menú desplegable con opciones organizadas

---

## 🎯 Funcionalidades Principales

- ✅ Insertar nueva persona
- 🔍 Buscar persona por ID
- 🛠️ Modificar datos de una persona
- ❌ Eliminar persona
- 📋 Mostrar reporte completo de personas en tabla
- 📊 Generar gráfico circular de distribución de pesos

---

## 🧰 Tecnologías Usadas

| Tecnología     | Propósito                             |
|----------------|----------------------------------------|
| Java 11+       | Lenguaje principal                     |
| Swing          | Interfaz gráfica                       |
| JFreeChart     | Generación de gráficos                 |
| JDBC           | Conexión y manipulación de BD          |
| MySQL          | Base de datos                          |

---

## 🗂️ Estructura del Proyecto
profile
Qwen3-235B-A22B
8:08 pm
¡Perfecto! Con base en el código que has compartido, veo que se trata de un proyecto Java con interfaz gráfica (GUI) usando Swing , JDBC para conexión a una base de datos MySQL y la biblioteca JFreeChart para gráficos. El proyecto permite realizar operaciones CRUD sobre una tabla Persona y generar reportes gráficos del campo peso.

✅ README.md con Etiquetas (Shields.io)
Aquí tienes un archivo README.md listo para tu proyecto, incluyendo etiquetas visuales (shields) , descripción, estructura, dependencias, capturas (opcionales), etc.

markdown


1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
⌄
⌄
⌄
⌄
⌄
⌄
<!-- ETIQUETAS / BADGES -->
![Java](https://img.shields.io/badge/Java-17-blue)
![Swing](https://img.shields.io/badge/Swing-GUI-orange)
![MySQL](https://img.shields.io/badge/MySQL-Database-green)
![CRUD](https://img.shields.io/badge/CRUD-Operations-yellow)
![JFreeChart](https://img.shields.io/badge/JFreeChart-Graphs-red)
![Apache2 license](https://img.shields.io/github/license/Naereen/AppStatus.svg)

# 🧑‍💻 Proyecto CRUD Persona con Gráficos

## 📄 Descripción

Este es un proyecto de escritorio desarrollado en **Java** que permite realizar operaciones **CRUD (Crear, Leer, Actualizar, Eliminar)** sobre una tabla de personas almacenada en una base de datos **MySQL**. Además, ofrece funcionalidades adicionales como:

- Generación de reportes en tablas (`JTable`)
- Visualización de gráficos circulares 3D basados en los pesos de las personas (`JFreeChart`)
- Validación de campos de entrada
- Menú desplegable con opciones organizadas

---

## 🎯 Funcionalidades Principales

- ✅ Insertar nueva persona
- 🔍 Buscar persona por ID
- 🛠️ Modificar datos de una persona
- ❌ Eliminar persona
- 📋 Mostrar reporte completo de personas en tabla
- 📊 Generar gráfico circular de distribución de pesos

---

## 🧰 Tecnologías Usadas

| Tecnología     | Propósito                             |
|----------------|----------------------------------------|
| Java 11+       | Lenguaje principal                     |
| Swing          | Interfaz gráfica                       |
| JFreeChart     | Generación de gráficos                 |
| JDBC           | Conexión y manipulación de BD          |
| MySQL          | Base de datos                          |

---

## 🗂️ Estructura del Proyecto

/src
├── GUI/
│ ├── GUI.java # Ventana principal con menú y navegación
│
├── CONTROL/
│ ├── Validacion.java # Validaciones de campos (nombre, edad, peso)
│
├── ADO/
│ ├── Persona.java # Modelo de datos de Persona
│ ├── PersonaSQL.java # Acceso a datos (CRUD + consultas)
│
└── Librerías Externas:
- jfreechart-1.0.13.jar
- mysql-connector-java-x.x.x.jar
- 
---

## 🚀 Cómo Ejecutar

### 1. Requisitos Previos

- Java 11 o superior
- MySQL instalado y corriendo
- Bibliotecas externas en el classpath:
  - `mysql-connector-java`
  - `jfreechart`

### 2. Preparar la Base de Datos

Ejecuta este script SQL para crear la base de datos y la tabla necesaria:

```sql
CREATE DATABASE TAP;

USE TAP;

CREATE TABLE persona (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    edad INT,
    peso DECIMAL(5,2)
);
### 3. Configurar la Conexión
En el archivo ADO/PersonaSQL.java, ajusta las credenciales si es necesario:
String url = "jdbc:mysql://localhost/TAP";
String usuario = "root";
String password = "";

### 4. Compilar y Ejecutar
Si usas un IDE como IntelliJ IDEA o Eclipse, solo ejecuta la clase GUI.GUI.

Desde línea de comandos (si estás usando librerías externas):
javac -cp ".;lib/*" GUI/GUI.java
java -cp ".;lib/*" GUI.GUI

🧪 ### Validaciones Incluidas
Nombre: No vacío, solo letras
Edad: Número entero entre 0 y 120
Peso: Número decimal positivo
ID: Solo números enteros positivos

📦 ### Licencia
MIT License – ver el archivo LICENSE para más detalles.
