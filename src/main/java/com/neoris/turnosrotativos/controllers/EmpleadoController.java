package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;




@RestController
@RequestMapping("/api")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;



    // HU 1
    @PostMapping("/empleados")
    public ResponseEntity<Object> agregarEmpleado(@Valid @RequestBody Empleado empleado){

        return new ResponseEntity<>(empleadoService.agregarEmpleado(empleado), HttpStatus.CREATED);
    }

    // HU 2
    @GetMapping("/empleados")
    public ResponseEntity<Object> obtenerEmpleados(){

        return new ResponseEntity<>(empleadoService.getEmpleados(),HttpStatus.OK);
    }


    // HU 3
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Object> obtenerEmpleado(@PathVariable Long id){

        return new ResponseEntity<>(empleadoService.getEmpleado(id), HttpStatus.OK);
    }


    // HU 4

    @PatchMapping("/empleados/{id}")
    public ResponseEntity<Object> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoAct){

        return new ResponseEntity<>(empleadoService.actualizarEmpleado(id, empleadoAct), HttpStatus.OK);
    }

    // HU 8
    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Object> eliminarEmpleado(@PathVariable Long id){

        return new ResponseEntity<>(empleadoService.eliminarEmpleado(id), HttpStatus.NO_CONTENT);

    }




}
