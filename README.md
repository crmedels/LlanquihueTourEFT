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
- Listado general de entidades.
- Listado general de servicios turísticos.
- Listado general de reservas.
- Búsqueda de entidades por identificador.
- Búsqueda de personas por RUT.
- Búsqueda de servicios por código.
- Búsqueda de reservas por código.
- Búsqueda de reservas por RUT del cliente.
- Búsqueda de reservas por fecha.
- Filtrado de entidades por tipo.
- Filtrado de servicios por tipo.
- Lectura de datos iniciales desde archivos TXT.
- Cálculo polimórfico del precio de los servicios.
- Cálculo de ingresos acumulados.
- Control de cupos disponibles por servicio y fecha.
- Control de disponibilidad de guías y vehículos.
- Prevención de códigos, RUT y patentes duplicadas.
- Validación inmediata de los datos ingresados.
- Formato monetario con separadores de miles.
- Interfaz gráfica desarrollada con Java Swing.

## Principios de Programación Orientada a Objetos

### Encapsulamiento

Los atributos de las clases se encuentran declarados como privados y son administrados mediante constructores, métodos de acceso y métodos de modificación.

Esto permite proteger el estado interno de los objetos y validar los datos antes de almacenarlos.

### Composición

La clase `Persona` contiene un objeto de tipo `Direccion`, representando la relación entre una persona y su dirección.

La clase `Reserva` relaciona objetos de tipo `Cliente`, `ServicioTuristico`, `GuiaTuristico` y `Vehiculo`.

### Herencia

Las clases `Cliente`, `GuiaTuristico` y `ColaboradorExterno` heredan los atributos y comportamientos comunes de la clase `Persona`.

Las clases `RutaGastronomica` y `PaseoLacustre` heredan de la clase abstracta `ServicioTuristico`.

### Polimorfismo

Los servicios turísticos implementan diferentes formas de calcular su precio mediante la sobrescritura del método `calcularPrecio`.

Las entidades registrables son almacenadas y procesadas mediante referencias de tipo `Registrable`, permitiendo trabajar con diferentes clases mediante una interfaz común.

### Interfaces

La interfaz `Registrable` establece los métodos necesarios para obtener el identificador y mostrar un resumen de cada entidad.

Esta interfaz es implementada por clientes, guías turísticos, colaboradores externos, vehículos, servicios turísticos y reservas.

### Colecciones

El sistema utiliza colecciones `ArrayList` para almacenar y administrar entidades, servicios turísticos y reservas.

Estas colecciones permiten registrar, recorrer, buscar y filtrar objetos durante la ejecución del programa.

### Excepciones personalizadas

El sistema utiliza excepciones propias para controlar situaciones específicas:

- `DatoInvalidoException`: datos vacíos, formatos incorrectos o valores no permitidos.
- `RegistroDuplicadoException`: códigos, RUT o patentes que ya se encuentran registrados.
- `CupoInsuficienteException`: solicitudes que superan la capacidad disponible de un servicio.

## Clases principales

- `Main`: punto de entrada de la aplicación e inicio de la interfaz gráfica.
- `MenuPrincipal`: administra los menús y formularios de entidades, servicios y reservas.
- `Persona`: clase base que contiene los datos comunes de las personas.
- `Direccion`: representa la dirección asociada a una persona.
- `Cliente`: representa a los clientes que contratan servicios turísticos.
- `GuiaTuristico`: representa a los guías disponibles para las actividades.
- `ColaboradorExterno`: representa a las personas externas que prestan servicios.
- `Vehiculo`: representa los vehículos utilizados para transportar pasajeros.
- `ServicioTuristico`: clase abstracta que contiene los datos comunes de los servicios.
- `RutaGastronomica`: representa un recorrido turístico enfocado en experiencias gastronómicas.
- `PaseoLacustre`: representa un servicio turístico realizado mediante una embarcación.
- `Reserva`: relaciona un cliente, un servicio, un guía y un vehículo en una fecha determinada.
- `GestorEntidades`: registra, busca, filtra y recorre entidades mediante polimorfismo.
- `GestorServicios`: registra, busca, filtra y administra servicios turísticos.
- `GestorReservas`: administra reservas, cupos, conflictos de recursos e ingresos.
- `CargadorDatosIniciales`: coordina la lectura inicial de los archivos TXT.
- `LectorClientes`: lee los clientes almacenados en el archivo externo.
- `LectorGuias`: lee los guías turísticos almacenados en el archivo externo.
- `LectorVehiculos`: lee los vehículos almacenados en el archivo externo.
- `LectorServicios`: identifica y crea distintos tipos de servicios desde el archivo externo.
- `Validador`: centraliza las validaciones, normalizaciones y conversiones de datos.
- `FormateadorMoneda`: presenta los montos con separadores de miles.

## Estructura del proyecto

```text
LlanquihueTourEFT
├── src
│   └── main
│       ├── java
│       │   ├── app
│       │   │   └── Punto de entrada de la aplicación
│       │   ├── data
│       │   │   └── Lectura y carga de archivos externos
│       │   ├── exception
│       │   │   └── Excepciones personalizadas
│       │   ├── interfaces
│       │   │   └── Contratos de comportamiento
│       │   ├── model
│       │   │   └── Entidades y servicios del dominio
│       │   ├── service
│       │   │   └── Gestores y lógica de negocio
│       │   ├── ui
│       │   │   └── Menús y formularios gráficos
│       │   └── util
│       │       └── Validaciones y funciones auxiliares
│       └── resources
│           └── data
│               ├── clientes.txt
│               ├── guias.txt
│               ├── servicios.txt
│               └── vehiculos.txt
├── .gitignore
├── pom.xml
└── README.md
```

## Archivos de datos

Los registros iniciales se cargan desde archivos TXT ubicados en:

```text
src/main/resources/data
```

Los archivos utilizados son:

```text
clientes.txt
guias.txt
vehiculos.txt
servicios.txt
```

Los datos de cada registro se encuentran separados mediante punto y coma.

Durante el inicio del programa, las clases lectoras procesan estos archivos y crean los objetos correspondientes.

## Validaciones

El sistema valida los principales datos ingresados por el usuario:

- RUT chileno y dígito verificador.
- RUT duplicados.
- Códigos de clientes, guías, colaboradores, servicios y reservas.
- Identificadores duplicados.
- Patentes chilenas.
- Patentes duplicadas.
- Nombres y apellidos sin números.
- Correos electrónicos.
- Números telefónicos.
- Campos obligatorios.
- Valores enteros y decimales.
- Valores positivos y no negativos.
- Capacidad de vehículos.
- Capacidad máxima de servicios.
- Disponibilidad de servicios, guías y vehículos.
- Cupos disponibles para una fecha.
- Formato de fecha `DD-MM-AAAA`.

Cuando un dato es incorrecto, el sistema muestra un mensaje de error y permite volver a ingresarlo sin cerrar la aplicación.

## Tecnologías utilizadas

- Java 26.
- Maven.
- Java Swing.
- IntelliJ IDEA.
- Git.
- GitHub.

## Requisitos

Para abrir y ejecutar el proyecto se requiere:

- Java Development Kit 26.
- IntelliJ IDEA o un IDE con soporte para proyectos Maven.
- Git, en caso de clonar el repositorio desde una terminal.

## Clonar el repositorio

Para descargar el proyecto mediante Git, abre una terminal y ejecuta:

```bash
git clone https://github.com/crmedels/LlanquihueTourEFT.git
```

Luego ingresa a la carpeta del proyecto:

```bash
cd LlanquihueTourEFT
```

También es posible descargarlo directamente desde GitHub mediante:

```text
Code → Download ZIP
```

## Ejecución desde IntelliJ IDEA

1. Abrir IntelliJ IDEA.
2. Seleccionar la opción `Open`.
3. Buscar y seleccionar la carpeta `LlanquihueTourEFT`.
4. Esperar que IntelliJ reconozca y cargue el proyecto Maven.
5. Verificar que el proyecto utilice Java 26.
6. Abrir la clase:

```text
src/main/java/app/Main.java
```

7. Ejecutar el método `main`.
8. Utilizar los menús gráficos para acceder a los módulos del sistema.

## Compilación con Maven

Desde la ventana Maven de IntelliJ IDEA, ejecutar:

```text
Lifecycle → clean
Lifecycle → compile
```

También se puede verificar la construcción completa mediante:

```text
Lifecycle → package
```

Los archivos generados por Maven se almacenan en la carpeta:

```text
target
```

Esta carpeta no se incluye en el repositorio porque se encuentra excluida mediante `.gitignore`.

## Uso general del sistema

Al iniciar la aplicación se muestran las siguientes opciones principales:

- Ver resumen general.
- Gestionar entidades.
- Gestionar servicios.
- Gestionar reservas.
- Salir.

En el módulo de entidades se pueden registrar, listar, buscar y filtrar clientes, guías, colaboradores externos y vehículos.

En el módulo de servicios se pueden registrar, listar, buscar y filtrar rutas gastronómicas y paseos lacustres.

En el módulo de reservas se pueden registrar reservas, consultar los datos disponibles, buscar registros y revisar los ingresos acumulados.

## Repositorio

El código fuente y el historial de desarrollo se encuentran disponibles en:

```text
https://github.com/crmedels/LlanquihueTourEFT
```

## Autor

Cristofer Medel

Estudiante de Analista Programador Computacional  
Duoc UC