# Prueba MELI

### La solución se encuentra desplegada de Heroku bajo la URL:

http://meli-forecast.herokuapp.com/swagger-ui.html

## Descripción del problema
En una galaxia lejana, existen tres civilizaciones. Vulcanos, Ferengis y Betasoides. Cada
civilización vive en paz en su respectivo planeta.

Dominan la predicción del clima mediante un complejo sistema informático.

A continuación el diagrama del sistema solar

Premisas:
* El planeta Ferengi se desplaza con una velocidad angular de 1 grados/día en sentido
horario. Su distancia con respecto al sol es de 500Km.
* El planeta Betasoide se desplaza con una velocidad angular de 3 grados/día en sentido
horario. Su distancia con respecto al sol es de 2000Km.
* El planeta Vulcano se desplaza con una velocidad angular de 5 grados/día en sentido
antihorario, su distancia con respecto al sol es de 1000Km.
* Todas las órbitas son circulares.
Cuando los tres planetas están alineados entre sí y a su vez alineados con respecto al sol, el
sistema solar experimenta un período de sequía.

Cuando los tres planetas no están alineados, forman entre sí un triángulo. Es sabido que en el
momento en el que el sol se encuentra dentro del triángulo, el sistema solar experimenta un
período de lluvia, teniendo éste, un pico de intensidad cuando el perímetro del triángulo está en
su máximo.

Las condiciones óptimas de presión y temperatura se dan cuando los tres planetas están
alineados entre sí pero no están alineados con el sol.

Realizar un programa informático para poder predecir en los próximos 10 años:
1. ¿Cuántos períodos de sequía habrá?
2. ¿Cuántos períodos de lluvia habrá y qué día será el pico máximo de lluvia?
3. ¿Cuántos períodos de condiciones óptimas de presión y temperatura habrá?

Bonus:
Para poder utilizar el sistema como un servicio a las otras civilizaciones, los Vulcanos requieren
tener una base de datos con las condiciones meteorológicas de todos los días y brindar una API
REST de consulta sobre las condiciones de un día en particular.
1) Generar un modelo de datos con las condiciones de todos los días hasta 10 años en adelante
   utilizando un job para calcularlas.
2) Generar una API REST la cual devuelve en formato JSON la condición climática del día
   consultado.
3) Hostear el modelo de datos y la API REST en un cloud computing libre (como APP Engine o
   Cloudfoudry) y enviar la URL para consulta:
   Ej: GET → http://….../clima?dia=566 → Respuesta: {“dia”:566, “clima”:”lluvia”}

## Descripción de la solución

### Supuestos y restricciones:
* Se asume que un año tiene 360 días.
* Se asume que todos los años tienen la misma cantidad de días.
* Se asume que todas las órbitas se encuentran en el mismo plano. 
* Se asume las 0:00 hs. como el momento del día que se da la predicción.

### Tecnologías:
* Spring Boot (Web, JPA y Quartz)
* Maven
* Swagger
* GIT
* Postgres

### Solución:

La solución cuenta con un componente de tareas programadas para la calendarización de la tarea de predicción junto con una API REST para agendar las tareas y consultar las predicciones.

### Modo de uso:
#### Generación de predicciones:

---
@Post http://meli-forecast.herokuapp.com/job/schedule

Servicio que permite agendar una tarea automática para una fecha y hora determinada. Al momento de la ejecución de la tarea el sistema realizará las predicciones impactando estas en la base de datos de la solución.

Documentación disponible en http://meli-forecast.herokuapp.com/swagger-ui.html#/task-job-scheduler-controller

---
@Post http://meli-forecast.herokuapp.com/job/now

Servicio que permite la ejecución de las predicciones en tiempo real.

Documentación disponible en http://meli-forecast.herokuapp.com/swagger-ui.html#/task-job-scheduler-controller

---
#### Consulta de datos:

---
@GET http://meli-forecast.herokuapp.com/services/days


Servicio que permite consultar los datos obtenidos de las predicciones. La información incluida en la respuesta es la siguiente:

* Total de días de sequía.
* Total de días de lluvia.
* Total de días de condiciones óptimas.
* Total de dias restantes.
* Listá de días de máximos de lluvia.

Documentación disponible en http://meli-forecast.herokuapp.com/swagger-ui.html#/weather-controller

---

@GET http://meli-forecast.herokuapp.com/services/days/{day}

Servicio que permite consultar las predicciones climáticas para un día en particular.

Documentación disponible en http://meli-forecast.herokuapp.com/swagger-ui.html#/weather-controller

---

### Documentación de referencia (Inglés)

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.2/maven-plugin/reference/html/#build-image)
* [Java Mail Sender](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-email)
* [Quartz Scheduler](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-quartz)  
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guias  (Inglés)

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

