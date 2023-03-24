package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.regex.Pattern;


import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;


import java.util.List;



@RestController
@RequestMapping("/api")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;


    @PostMapping("/empleados")
    public ResponseEntity<Object> agregarEmpleado(@Valid @RequestBody Empleado empleado){

        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(empleado.getFechaNacimiento(), fechaActual);
        int edad = periodo.getYears();


       Empleado empleadoDni = empleadoService.findByDni(empleado.getDni());
       Empleado empleadoEmail = empleadoService.findByEmail(empleado.getEmail());


       if(empleado.getDni() == null){
           return new ResponseEntity<>("El campo dni es obligatorio.",HttpStatus.BAD_REQUEST);
       }
       if(empleado.getNombre().equals("")){
           return new ResponseEntity<>("El campo nombre es obligatorio",HttpStatus.BAD_REQUEST);
       }
        if(empleado.getApellido().equals("")){
            return new ResponseEntity<>("El campo apellido es obligatorio",HttpStatus.BAD_REQUEST);
        }
        if(empleado.getEmail().equals("")){
            return new ResponseEntity<>("El campo email es obligatorio",HttpStatus.BAD_REQUEST);
        }
        if(empleado.getFechaNacimiento() == null){
            return new ResponseEntity<>("El campo fecha de nacimiento es obligatorio",HttpStatus.BAD_REQUEST);
        }
        if(empleado.getFechaDeIngreso() == null){
            return new ResponseEntity<>("El campo fecha de ingreso es obligatorio",HttpStatus.BAD_REQUEST);
        }
        if(empleado.getFechaNacimiento().isAfter(fechaActual)){
            return new ResponseEntity<>("La fecha de nacimiento no puede ser posterior al día de la fecha.",HttpStatus.BAD_REQUEST);
        }
        if(edad < 18){
            return new ResponseEntity<>("La edad del empleado no puede ser menor a 18 años",HttpStatus.BAD_REQUEST);
        }
        if(empleadoDni != null){
                return new ResponseEntity<>("Ya existe un empleado con el documento ingresado.",HttpStatus.CONFLICT);
        }
        if(empleadoEmail != null){
            return new ResponseEntity<>("Ya existe un empleado con el email ingresado.",HttpStatus.CONFLICT);
        }
        if(empleado.getFechaDeIngreso().isAfter(fechaActual)){
            return new ResponseEntity<>("La fecha de ingreso no puede ser posterior al día de la fecha.",HttpStatus.BAD_REQUEST);
        }
        if (!formatoEmailValido(empleado.getEmail())) {
            return new ResponseEntity<>("El email ingresado no es correcto.",HttpStatus.BAD_REQUEST);
        }
        if (!formatoNombreApellidoValido(empleado.getNombre())) {
            return new ResponseEntity<>("Solo se permiten letras en el campo Nombre",HttpStatus.BAD_REQUEST);
        }

        if (!formatoNombreApellidoValido(empleado.getApellido())) {
            return new ResponseEntity<>("Solo se permiten letras en el campo Apellido",HttpStatus.BAD_REQUEST);
        }



        Empleado empleadoAgregado = empleadoService.agregarEmpleado(empleado);

        return new ResponseEntity<>(empleadoAgregado, HttpStatus.CREATED);
    }


    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> obtenerEmpleados(){

        return new ResponseEntity<>(empleadoService.getEmpleados(),HttpStatus.OK);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Object> obtenerEmpleado(@PathVariable Long id){

        Empleado empleado = empleadoService.getEmpleado(id);
        if(empleado != null){
            return new ResponseEntity<>(empleado,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No se encontro el empleado con el id: " + id,HttpStatus.NOT_FOUND);
        }


    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<String> eliminarConcepto(@PathVariable Long id){

        Optional<Empleado> empleado = Optional.ofNullable(empleadoService.getEmpleado(id));

        if (empleado.isEmpty()) {
            return new ResponseEntity<>("No se encontro el empleado con el id: " + id, HttpStatus.NOT_FOUND);
        }
        empleadoService.eliminarEmpleado(id);
        return new ResponseEntity<>("El empleado fue eliminado con éxito.", HttpStatus.NO_CONTENT);

    }


    @PatchMapping("/empleados/editar/{id}")
    public ResponseEntity<Object> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoAct){

        Optional<Empleado> empleadoEncontrado = Optional.ofNullable(empleadoService.getEmpleado(id));

        if (empleadoEncontrado.isEmpty()) {
            return new ResponseEntity<>("No se encontro el empleado con el id: " + id, HttpStatus.NOT_FOUND);
        }

        Empleado empleado = empleadoEncontrado.get();
        empleado.setDni(empleadoAct.getDni());
        empleado.setNombre(empleadoAct.getNombre());
        empleado.setApellido(empleadoAct.getApellido());
        empleado.setEmail(empleadoAct.getEmail());
        empleado.setFechaNacimiento(empleadoAct.getFechaNacimiento());
        empleado.setFechaDeIngreso(empleadoAct.getFechaDeIngreso());


        empleadoService.agregarEmpleado(empleado);

        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }


    private boolean formatoEmailValido(String email) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        return pattern.matcher(email).matches();
    }
    private boolean formatoNombreApellidoValido(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        return pattern.matcher(name).matches();
    }

}
