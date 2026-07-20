# Llanquihue Tour EFT

Proyecto desarrollado para la Evaluación Final Transversal de la asignatura Desarrollo Orientado a Objetos I.

## Descripción

Llanquihue Tour EFT es un prototipo de sistema desarrollado en Java para gestionar las principales operaciones de una agencia de turismo.

La aplicación permite administrar clientes, guías turísticos, colaboradores externos, vehículos, servicios turísticos y reservas. El proyecto utiliza una estructura modular y aplica los principales fundamentos de la Programación Orientada a Objetos.

## Funcionalidades implementadas

- Registro de clientes.
- Registro de guías turísticos.
- Registro de colaboradores externos.
- Registro de vehículos.
- Registro de rutas gastronómicas.
- Registro de paseos lacustres.
- Registro y gestión de reservas.
- Búsqueda de entidades por identificador.
- Búsqueda de personas por RUT.
- Búsqueda de servicios y reservas por código.
- Búsqueda de reservas por RUT del cliente.
- Búsqueda de reservas por fecha.
- Filtrado de entidades y servicios por tipo.
- Lectura de datos iniciales desde archivos TXT.
- Cálculo polimórfico del precio de los servicios.
- Cálculo de ingresos acumulados.
- Control de cupos disponibles.
- Control de disponibilidad de guías y vehículos.
- Prevención de códigos, RUT y patentes duplicadas.
- Validación inmediata de los datos ingresados.
- Interfaz gráfica desarrollada con Swing.

## Programación Orientada a Objetos

El proyecto aplica los siguientes principios:

### Encapsulamiento

Los atributos de las clases se encuentran declarados como privados y son administrados mediante constructores, métodos de acceso y métodos de modificación.

### Composición

La clase `Persona` contiene un objeto de tipo `Direccion`, representando la relación entre una persona y su dirección.

### Herencia

Las clases `Cliente`, `GuiaTuristico` y `ColaboradorExterno` heredan los atributos y comportamientos comunes de la clase `Persona`.

Las clases `RutaGastronomica` y `PaseoLacustre` heredan de la clase abstracta `ServicioTuristico`.

### Polimorfismo

Los servicios turísticos implementan diferentes formas de calcular su precio mediante la sobrescritura del método `calcularPrecio`.

Las entidades registrables son almacenadas y procesadas mediante referencias de tipo `Registrable`.

### Interfaces

La interfaz `Registrable` establece los métodos necesarios para obtener el identificador y mostrar el resumen de cada entidad.

### Excepciones personalizadas

El sistema utiliza excepciones propias para controlar datos inválidos, registros duplicados y falta de cupos disponibles.

## Estructura del proyecto

```text
src/main/java
├── app
│   └── Punto de entrada de la aplicación
├── data
│   └── Lectura y carga de archivos externos
├── exception
│   └── Excepciones personalizadas
├── interfaces
│   └── Contratos de comportamiento
├── model
│   └── Entidades y servicios del dominio
├── service
│   └── Gestores y lógica de negocio
├── ui
│   └── Menús y formularios gráficos
└── util
    └── Validaciones y funciones auxiliares