# Q&A APP:

![Despliegue](https://res.cloudinary.com/df8qzqymf/image/upload/v1641861231/homepage_swplkn.png)

## Descripcion:

Se realizo una aplicacion de preguntas y respuestas, basandonos en dos tipos de usuarios:

- El perfil no registrado:
  Tiene acceso a la seccion de preguntas, no puede votar, ni responder, solo visualizarlas.
  
- El perfil usuario registrado:
  Tiene acceso la seccion de preguntas en ella puede votar y replicar, a la vez que puede crear
  nuevas preguntas en otra seccion, tambien puede visualizar en la seccion list sus preguntas, ademas
  puede agregar y borrar preguntas de favoritos, como usuario registrado a traves de un e-mail y password
  puede modificar su nombre, si se logueo con Google esta opcion esta desahibiltada por las politicas
  que google maneja con la autenticacion de firebase.


### Perspectiva Back-end

El backend se centro en realizar una API-Rest reactiva usando Spring-boot webflux, ademas usando como Base de 
datos NoSQL MongoDB, desde el back se realizan las validaciones, reglas de negocio para el sistema de votacion
y el envio de correos cuando una pregunta es contestada, se desplego esta API en Heroku.

### Perspectiva Front-end

El frontend se centro en el consumo de esta API, se crearon las UI y UX usando React y Redux para el
manejo de los estados globales y consumo de la API-Rest, tambien se validaron rutas privadas a traves de react-router-dom el
despliegue de la aplicacion se hizo a traves de firebase y la autenticacion a traves de firebase Auth.

## URL Despliegues:

* [Despliegue Frontend Firebase](https://questions-98a5f.web.app/)
* [Despliegue Backend Heroku](https://questionsapisofka.herokuapp.com/getAll)

