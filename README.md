 Proyecto realizado para Neoris, un sistema de gestion de empleados con la posibilidad de crear, editar, eliminar y listar empleados, el stack utilizado: 
 
 Backend: Java - Springboot 
 Frontend: Angular


La aplicacion funciona de la siguiente forma:   

**** Por si gustan, aca les dejo la api de postman para tener todas las peticiones ya creadas para no tener que hacerlos a mano: ****
https://api.postman.com/collections/15035035-430dbae3-a7d3-47e6-b433-4c10793b5b65?access_key=PMAT-01GWAK8Y6V393MY6FZ231XZT0G




*La informacion le pasamos en el body del postman(en formato json)*



**Y aca por si lo quieren hacer a mano**
// Empleados

Para crear empleado:    HU1    

[POST] localhost:8080/api/empleados



Obtener todos los empleados:     HU2

[GET] localhost:8080/api/empleados



Obtener todos los empleados segun su id:      HU3

[GET] localhost:8080/api/empleados/{id}



Actualizar un empleado:       HU4

[PATCH] localhost:8080/api/empleados/editar/{id}



// Conceptos

Para obtener conceptos:       HU5

[GET] localhost:8080/api/conceptos



// Jornadas

Alta de jornada laboral:       HU6

[POST] localhost:8080/api/jornadas


Obtener todas las jornadas:      HU7

[GET] localhost:8080/api/jornadas


Obtener jornadas por nro de documento por query params:

[GET] localhost:8080/api/jornadas/dni


Obtener jornadas por fecha por query params:

[GET] localhost:8080/api/jornadas/fecha



//EMPLEADO DE NUEVO

Eliminar un empleado:      HU8

[DELETE] localhost:8080/api/empleados/{id}

