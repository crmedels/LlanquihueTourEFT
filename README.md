# Llanquihue Tour EFT

Proyecto desarrollado para la Evaluacion Final Transversal de la asignatura Desarrollo Orientado a Objetos I.

## Descripcion

Llanquihue Tour EFT es un prototipo de sistema desarrollado en Java para gestionar las principales operaciones de una agencia de turismo.

La aplicacion permite administrar clientes, guias turisticos, colaboradores externos, vehiculos, servicios turisticos y reservas. El proyecto utiliza una estructura modular y aplica los principales fundamentos de la Programacion Orientada a Objetos.

## Funcionalidades implementadas

- Registro de clientes.
- Registro de guias turisticos.
- Registro de colaboradores externos.
- Registro de vehiculos.
- Registro de rutas gastronomicas.
- Registro de paseos lacustres.
- Registro y gestion de reservas.
- Busqueda de entidades por identificador.
- Busqueda de personas por RUT.
- Busqueda de servicios y reservas por codigo.
- Busqueda de reservas por RUT del cliente.
- Busqueda de reservas por fecha.
- Filtrado de entidades y servicios por tipo.
- Lectura de datos iniciales desde archivos TXT.
- Calculo polimorfico del precio de los servicios.
- Calculo de ingresos acumulados.
- Control de cupos disponibles.
- Control de disponibilidad de guias y vehiculos.
- Prevencion de codigos, RUT y patentes duplicadas.
- Validacion inmediata de datos durante el registro.
- Presentacion de montos con separadores de miles.
- Interfaz grafica desarrollada con Java Swing.

## Programacion Orientada a Objetos

### Encapsulamiento

Los atributos se encuentran declarados como privados y son administrados mediante constructores, metodos de acceso y metodos de modificacion.

### Composicion

La clase `Persona` contiene un objeto de tipo `Direccion`, representando la relacion entre una persona y su direccion.

### Herencia

Las clases `Cliente`, `GuiaTuristico` y `ColaboradorExterno` heredan los datos y comportamientos comunes de `Persona`.

Las clases `RutaGastronomica` y `PaseoLacustre` heredan de la clase abstracta `ServicioTuristico`.

### Polimorfismo

Los servicios turisticos sobrescriben el metodo `calcularPrecio(int cantidadPersonas)` para aplicar su propio calculo.

Las entidades se almacenan y procesan mediante referencias de tipo `Registrable`.

### Interfaces

La interfaz `Registrable` establece los metodos necesarios para obtener el identificador y mostrar un resumen de cada entidad.

### Excepciones personalizadas

El sistema utiliza excepciones propias para controlar datos invalidos, registros duplicados y falta de cupos disponibles.

## Estructura del proyecto

```text
src/main/java
├── app
├── data
├── exception
├── interfaces
├── model
├── service
├── ui
└── util
```

- `app`: punto de entrada de la aplicacion.
- `data`: lectura y carga de archivos externos.
- `exception`: excepciones personalizadas.
- `interfaces`: contratos de comportamiento.
- `model`: entidades y servicios del dominio.
- `service`: gestores y logica de negocio.
- `ui`: menus y formularios graficos.
- `util`: validaciones y funciones auxiliares.

## Archivos de datos

Los datos iniciales se cargan desde `src/main/resources` mediante los siguientes archivos:

- `clientes.txt`
- `guias.txt`
- `vehiculos.txt`
- `servicios.txt`

Los campos se encuentran separados por punto y coma.

## Tecnologias utilizadas

- Java 26.
- Maven.
- Java Swing.
- IntelliJ IDEA.
- Git y GitHub.

## Ejecucion desde IntelliJ IDEA

1. Abrir el proyecto.
2. Esperar que Maven cargue la estructura.
3. Abrir `src/main/java/app/Main.java`.
4. Ejecutar el metodo `main`.
5. Utilizar los menus graficos para acceder a los modulos.

## Compilacion con Maven

Desde la ventana Maven de IntelliJ IDEA:

```text
Lifecycle -> clean
Lifecycle -> compile
Lifecycle -> package
```

El archivo generado se almacena en la carpeta `target`.

## Autor

Cristofer Medel  
Estudiante de Analista Programador Computacional  
Duoc UC
