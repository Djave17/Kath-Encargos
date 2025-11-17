# KathEncargo

Sistema de gestión de pedidos para un restaurante tipo buffet con modalidad **solo pickup**.  
Permite administrar el menú diario, definir platillos combinando estación principal, guarnición, bebida y postre, registrar pedidos de clientes, controlar su flujo de estados y generar la facturación correspondiente.

Este proyecto se desarrolla como parte de la asignatura **Programación Orientada a Objetos (MPOO1)** y está orientado a aplicar buenas prácticas de modelado de datos, diseño orientado a objetos y uso de OpenXava con JPA sobre PostgreSQL.

---

## Objetivos del proyecto

- Estandarizar la **toma de pedidos** del buffet, evitando errores manuales.
- Gestionar de forma centralizada:
  - Usuarios y roles (operador / cliente).
  - Catálogos de tipos y estados de pedido.
  - Menú diario (bebidas, postres, guarniciones, estaciones principales).
  - Platillos como combinación de los elementos del menú.
- Llevar un **historial de estados** por pedido.
- Registrar la **factura** asociada a cada pedido, manteniendo trazabilidad entre pedido y cobro.
- Diseñar una base de datos **normalizada y escalable**, lista para crecimiento futuro (más sucursales, más tipos de pedido, más roles de usuario, etc.).

---

## Modelo de datos

El sistema se apoya en un modelo entidad–relación organizado en áreas de sujeto:

- **Seguridad y usuarios**: `Usuario`, `Rol`.
- **Catálogos**: `TipoPedido`, `EstadoPedido`.
- **Menú**: `CategoriaGuarnicion`, `CategoriaFuerte`, `Guarnicion`, `EstacionPrincipal`, `Bebida`, `Postre`.
- **Platillo**: `Platillo` (combina estación principal, guarnición, bebida y postre).
- **Operación de pedidos**: `Pedido`, `DetallePedido`, `HistorialEstadoPedido`, `Factura`.

### Diagrama lógico

<img width="1634" height="1140" alt="Image" src="https://github.com/user-attachments/assets/2aefeb6a-799b-4bca-bd49-e32a7ba0610a" />

### Diagrama físico

<img width="1634" height="1182" alt="Image" src="https://github.com/user-attachments/assets/ed7b1fc5-4d43-4b72-9727-ed5c553f91ec" />

## Arquitectura de la aplicación

La solución sigue una arquitectura por capas sencilla, favoreciendo la separación de responsabilidades:

- `domain/`
  - `catalogos/`: entidades de catálogo (`TipoPedido`, `EstadoPedido`, categorías).
  - `seguridad/`: `Usuario`, `Rol`.
  - `menu/`: `Bebida`, `Postre`, `Guarnicion`, `EstacionPrincipal`.
  - `platillo/`: `Platillo`.
  - `pedidos/`: `Pedido`, `DetallePedido`, `HistorialEstadoPedido`.
  - `facturacion/`: `Factura`.
- `infrastructure/`
  - Configuración de persistencia JPA (PostgreSQL) y recursos comunes.
- `application/`
  - Arranque de la aplicación y configuración de módulos OpenXava.

Cada entidad de dominio se mapea con **JPA** y se expone como módulo de **OpenXava** para permitir operaciones CRUD directas desde la UI generada por el framework.

---

## Tecnologías utilizadas

- **Java** (versión acorde a OpenXava del proyecto).
- **OpenXava** como framework para desarrollo rápido de aplicaciones empresariales.
- **JPA/Hibernate** para mapeo objeto–relacional.
- **PostgreSQL 18** como motor de base de datos (adaptable a 17 si se usa imagen Docker distinta).
- **Maven/Gradle** (según configuración del proyecto) para gestión de dependencias.

---

## Requisitos previos

- JDK instalado.
- PostgreSQL en ejecución (local o contenedor Docker) con una base de datos creada para el proyecto.
- Herramientas de build (Maven o Gradle).
- IDE recomendado: IntelliJ IDEA, Eclipse o similar con soporte para proyectos web Java.

---

## Puesta en marcha rápida

1. **Clonar el repositorio**

   ```bash
   git clone https://github.com/Djave17/KathEncargo.git
   cd KathEncargo
````

2. **Configurar la conexión a PostgreSQL**

   * Actualizar los parámetros de conexión (URL, usuario, contraseña) en el archivo de configuración de persistencia JPA (`persistence.xml` o equivalente según la estructura del proyecto).

3. **Crear la base de datos**

   * Crear la base vacía en PostgreSQL (nombre según configuración).
   * Permitir que JPA genere el esquema a partir de las entidades o aplicar el script DDL exportado desde el modelo físico.

4. **Compilar y ejecutar**

   * Ejecutar el comando de build (Maven/Gradle) para compilar el proyecto.
   * Levantar la aplicación y acceder al módulo principal de OpenXava en el navegador.

---

## Estructura de paquetes (dominio)


uam.edu.ni.KathEncargos
 └── domain
     ├── catalogos
     │   ├── TipoPedido
     │   └── EstadoPedido
     ├── seguridad
     │   ├── Rol
     │   └── Usuario
     ├── menu
     │   ├── Bebida
     │   ├── Postre
     │   ├── Guarnicion
     │   └── EstacionPrincipal
     ├── platillo
     │   └── Platillo
     ├── pedidos
     │   ├── Pedido
     │   ├── DetallePedido
     │   └── HistorialEstadoPedido
     └── facturacion
         └── Factura
