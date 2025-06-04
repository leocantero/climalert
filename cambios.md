# Entidades de dominio
## Alerta
### Problemas
- El sistema no cuenta con la entidad principal que es la Alerta, que es la que se va a encargar de evaluar las condiciones y disparar las alertas.
- No se está utilizando un patrón de diseño que permita evaluar las condiciones de manera flexible y escalable.

### Soluciones
- Agrego una clase Alerta, que va a ser la principal entidad del sistema. Ésta va a ser una clase que va a usar un patrón strategy para poder alertar en caso de que se cumplan ciertas condiciones.
- Para el uso del Patrón Strategy, hice la Interface Condicion, que va a definir el método cumpleCondicion. Esta interface va a ser implementada por las clases que representen las condiciones que se van a evaluar para disparar una alerta.
- Al usar una interaz Coleccion, estoy aplicando el princpio SOLID de Inversión de Dependencias, ya que la clase Alerta no depende de una implementación concreta de una colección, sino de una interfaz que define el comportamiento esperado.
- También le sumé un id y descripción a las alertas, ya que considero necesario tener persistidas las entidades alerta, para poder hacer un seguimiento de las mismas.

## Email
### Problemas
- Se está planteando un método "enviar" que suena a método botón, por lo cual podría estar en un service

### Soluciones
- Plantear el "enviar" en el servicio de Email

# Services
## EmailService
### Problemas
- Para crear un email se está usando un constructor que recibe un objeto Email, lo cual no es una buena práctica, ya que el servicio debería ser responsable de crear el email.

### Soluciones
- Hice uso de un DTO para que el controller lo reciba y el servicio sea el encargado de mapearlo con el uso de un mapper.

## AlertaService
### Problemas
- Hay métodos que tienen varias responsabilidades como generarAlertasYAvisar o generarYEnviarMail
- Se está haciendo una validación de las condiciones dentro del servicio, lo cual no es una buena práctica, ya que debería ser responsabilidad de la entidad Alerta.

### Soluciones
- Para cumplir el princpio SOLID de Responsabilidad Única, separé las responsabilidades de los métodos. Ahora, cada método tiene una única responsabilidad.
- 