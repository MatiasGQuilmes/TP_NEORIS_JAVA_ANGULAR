package com.neoris.turnosrotativos.controllers;


import com.neoris.turnosrotativos.DTOS.JornadaEmpleadoDTO;
import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.service.ConceptoService;
import com.neoris.turnosrotativos.service.EmpleadoService;
import com.neoris.turnosrotativos.service.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class JornadaController {


    @Autowired
    JornadaService jornadaService;

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    ConceptoService conceptoService;


    @GetMapping("/jornadas")
    public ResponseEntity<List<JornadaEmpleadoDTO>> traerJornadas(){

        return new ResponseEntity<>(jornadaService.obtenerJornadas(), HttpStatus.OK);
    }

    @GetMapping("/jornadas/{dni}")
    public ResponseEntity<Jornada> traerJornadaPorDNI(@PathVariable Integer dni){

        return new ResponseEntity<>(jornadaService.jornadaByDni(dni), HttpStatus.OK);
    }

    @GetMapping("/jornadas/{fecha}")
    public ResponseEntity<Object> traerJornadaPorFecha(@PathVariable LocalDate fecha){

        List<Jornada> jornadas = jornadaService.jornadaByFecha(fecha);

        if(jornadas.isEmpty()){
            return new ResponseEntity<>("No hay jornadas con la fecha indicada.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(jornadas, HttpStatus.OK);
    }

    @PostMapping("/jornadas")
    public ResponseEntity<Object> agregarJornada( @Valid @RequestBody Jornada jornada){

        Empleado empleado = empleadoService.getEmpleado(jornada.getEmpleado());
        Concepto concepto = conceptoService.getConcepto(jornada.getIdConcepto());



        if(jornada.getEmpleado() == null){
            return new ResponseEntity<>("El campo id_empleado es obligatorio.",HttpStatus.BAD_REQUEST);
        }
        if(jornada.getIdConcepto() == null){
            return new ResponseEntity<>("El campo id_concepto es obligatorio.",HttpStatus.BAD_REQUEST);
        }if(jornada.getFecha() == null){
            return new ResponseEntity<>("El campo fecha es obligatorio.",HttpStatus.BAD_REQUEST);
        }
        if(!jornadaService.getConcepto(jornada.getIdConcepto()).getNombre().equals("Dia Libre") ){
            if(jornada.getHorasTrabajadas() == null){
                return new ResponseEntity<>("hsTrabajadas es obligatorio para el concepto ingresado.",HttpStatus.BAD_REQUEST);
            }
        }
        if(jornadaService.getConcepto(jornada.getIdConcepto()).getNombre().equals("Dia Libre")){
            if(jornada.getHorasTrabajadas() != null){
                return new ResponseEntity<>("El concepto ingresado no requiere el ingreso de hsTrabajadas.",HttpStatus.BAD_REQUEST);
            }
        }
        if(jornadaService.getEmpleado(jornada.getEmpleado()) == null){
            return new ResponseEntity<>("No existe el empleado ingresado.",HttpStatus.NOT_FOUND);
        }
        if(jornadaService.getConcepto(jornada.getIdConcepto()) == null){
            return new ResponseEntity<>("No existe el concepto ingresado.",HttpStatus.NOT_FOUND);
        }
        if(!jornadaService.getConcepto(jornada.getIdConcepto()).getNombre().equals("Dia Libre")){
            if(jornada.getHorasTrabajadas() < concepto.getHsMinimo() || jornada.getHorasTrabajadas() > concepto.getHsMaximo()){
                return new ResponseEntity<>("El rango de horas que se puede cargar para este concepto es de " + concepto.getHsMinimo() + " - "  + concepto.getHsMaximo(),HttpStatus.BAD_REQUEST);
            }
        }

        List<Jornada> fechasIdenticas = empleado.getJornadas()
                .stream()
                .filter(f -> f.getFecha().equals(jornada.getFecha()) )
                .collect(Collectors.toList());


        List<Jornada> DiasLibresLoHacenTrabajar = empleado.getJornadas()
                .stream()
                .filter(f -> f.getIdConcepto().equals(3) && f.getFecha().equals(jornada.getFecha()))
                .collect(Collectors.toList());

//        List<Jornada> jornadasSegunHsTrabajadas= empleado.getJornadas().stream()
//                .filter(j ->  j.getHorasTrabajadas() + jornada.getHorasTrabajadas() > 12 && !Objects.equals(j.getIdConcepto(), jornada.getIdConcepto()))
//                .collect(Collectors.toList());


        if(empleado.getJornadas().contains(jornadaService.jornadaByFecha(jornada.getFecha()))){
            return new ResponseEntity<>("No podes tener el mismo concepto",HttpStatus.BAD_REQUEST);
        }

//        if(fechasIdenticas.size() > 0){
//            return new ResponseEntity<>("El empleado ya tiene registrado una jornada con este concepto en la fecha ingresada.",HttpStatus.BAD_REQUEST);
//        }



        if(DiasLibresLoHacenTrabajar.size() > 0){
            return new ResponseEntity<>("El empleado ingresado cuenta con un día libre en esa fecha.",HttpStatus.BAD_REQUEST);
        }


//        if(jornadasSegunHsTrabajadas.size() > 0) {
//            return new ResponseEntity<>("El empleado no puede cargar más de 12 horas trabajadas en un día.",HttpStatus.BAD_REQUEST);
//        }





        Jornada newJornada = new Jornada(jornada.getEmpleado(), jornada.getIdConcepto(), jornada.getFecha(), jornada.getHorasTrabajadas());
        empleadoService.getEmpleado(jornada.getEmpleado()).getJornadas().add(newJornada);
        jornadaService.agregarJornada(newJornada);


        JornadaEmpleadoDTO jornadaEmpleadoDTO = new JornadaEmpleadoDTO(empleado, concepto, newJornada);
        return new ResponseEntity<>(jornadaEmpleadoDTO, HttpStatus.CREATED);
    }


}
