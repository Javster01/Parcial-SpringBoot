# Proyecto Electiva

Este es un proyecto para gestionar un sistema de ventas en una plataforma de comercio electrónico. El sistema permite gestionar clientes, productos, ventas y detalles de venta utilizando **Spring Boot** y una base de datos **PostgreSQL**.

## Tecnologías utilizadas

- **Spring Boot**: Framework para construir aplicaciones Java basadas en Spring.
- **Spring Data JPA**: Para la gestión de la base de datos.
- **PostgreSQL**: Base de datos relacional.
- **Swagger (Springdoc OpenAPI)**: Documentación de la API.
- **Jackson**: Para la serialización y deserialización de objetos Java a JSON y viceversa.

## Requisitos

- **JDK 11 o superior**
- **Maven** (para la gestión de dependencias y construcción del proyecto)
- **PostgreSQL** (o acceso a una base de datos PostgreSQL)
- **Git** (para la gestión del código fuente)

## Instalación

Sigue los siguientes pasos para ejecutar el proyecto:

1. **Clona el repositorio**:

    ```bash
    git clone https://github.com/tu-usuario/electiva.git
    ```

2. **Navega al directorio del proyecto**:

    ```bash
    cd electiva
    ```

3. **Abre el proyecto en tu IDE preferido** (como **IntelliJ IDEA** o **Eclipse**).

4. **Configura las credenciales de la base de datos en `application.properties`**:

    Abre el archivo `src/main/resources/application.properties` y asegúrate de que las credenciales de la base de datos estén correctamente configuradas:

    ```properties
    spring.datasource.url=jdbc:postgresql://aws-0-us-east-1.pooler.supabase.com:6543/postgres?user=postgres.hadlwuutueyfnmtsmddw&password=WKuurLVHEcZseaB4
    spring.jpa.hibernate.ddl-auto=update
    ```


5. **Compila el proyecto**:

    Si estás utilizando **Maven**, ejecuta el siguiente comando en la terminal:

    ```bash
    mvn clean install
    ```

6. **Ejecuta la aplicación**:

    Para ejecutar la aplicación desde la terminal:

    ```bash
    mvn spring-boot:run
    ```

    O, si prefieres, ejecuta el proyecto directamente desde tu IDE.

## Uso

### Documentación de la API (Swagger)


En el application properties esta con Update en vez de Create-Drop porque ya hay datos en la base de datos
Una vez que la aplicación esté en ejecución, puedes acceder a la documentación de la API Swagger en:


Desde allí podrás ver todos los endpoints disponibles y probar la API directamente.

### Endpoints principales

#### **Clientes**

- **POST /clientes**: Crear un nuevo cliente.

    **Request body:**
    ```json
    {
      "nombre": "Juan Pérez",
      "email": "juan.perez@example.com"
    }
    ```

- **GET /clientes**: Obtener todos los clientes.

    **Response:**
    ```json
    [
      {
        "id": 1,
        "nombre": "Juan Pérez",
        "email": "juan.perez@example.com"
      },
      {
        "id": 2,
        "nombre": "Javier Ortiz",
        "email": "javier.ortiz@example.com"
      }
    ]
    ```

- **GET /clientes/{id}**: Obtener un cliente por ID.

    **Response:**
    ```json
    {
      "id": 1,
      "nombre": "Juan Pérez",
      "email": "juan.perez@example.com"
    }
    ```

#### **Productos**

- **POST /productos**: Crear un nuevo producto.

    **Request body:**
    ```json
    {
      "nombre": "Producto A",
      "precio": 100.0,
      "stock": 50
    }
    ```

- **GET /productos**: Obtener todos los productos.

    **Response:**
    ```json
    [
      {
        "id": 1,
        "nombre": "Producto A",
        "precio": 100.0,
        "stock": 50
      },
      {
        "id": 2,
        "nombre": "Producto B",
        "precio": 200.0,
        "stock": 30
      }
    ]
    ```

- **GET /productos/{id}**: Obtener un producto por ID.

    **Response:**
    ```json
    {
      "id": 1,
      "nombre": "Producto A",
      "precio": 100.0,
      "stock": 50
    }
    ```

#### **Ventas**

- **POST /ventas**: Crear una nueva venta.

    **Request body:**
    ```json
    {
      "fecha": "2024-11-29",
      "cliente_id": 1
    }
    ```

- **GET /ventas**: Obtener todas las ventas.

    **Response:**
    ```json
    [
      {
        "id": 1,
        "fecha": "2024-11-29",
        "cliente": {
          "id": 1,
          "nombre": "Juan Pérez",
          "email": "juan.perez@example.com"
        }
      }
    ]
    ```

- **GET /ventas/{id}**: Obtener una venta por ID.

    **Response:**
    ```json
    {
      "id": 1,
      "fecha": "2024-11-29",
      "cliente": {
        "id": 1,
        "nombre": "Juan Pérez",
        "email": "juan.perez@example.com"
      }
    }
    ```

#### **Detalles de Venta**

- **POST /detalles**: Crear un detalle de venta.

    **Request body:**
    ```json
    {
      "cantidad": 2,
      "producto_id": 1,
      "venta_id": 1
    }
    ```

- **GET /detalles**: Obtener todos los detalles de venta.

    **Response:**
    ```json
    [
      {
        "id": 1,
        "cantidad": 2,
        "producto": {
          "id": 1,
          "nombre": "Producto A",
          "precio": 100.0,
          "stock": 50
        },
        "venta": {
          "id": 1,
          "fecha": "2024-11-29"
        }
      }
    ]
    ```

## Estructura del Proyecto

La estructura básica del proyecto es la siguiente:


