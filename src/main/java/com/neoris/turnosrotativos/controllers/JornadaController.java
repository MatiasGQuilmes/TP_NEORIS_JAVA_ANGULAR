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


import java.util.List;



@RestController
@RequestMapping("/api")
public class JornadaController {


    @Autowired
    JornadaService jornadaService;

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    ConceptoService conceptoService;







    // HU 6

//    @PostMapping("/jornadas")
//    public ResponseEntity<Object> agregarJornada( @Valid @RequestBody Jornada jornada){
//
//
//    }


    //HU 7
    @GetMapping("/jornadas")
    public ResponseEntity<Object> traerJornadas(){

        List<JornadaEmpleadoDTO> jornadas = (List<JornadaEmpleadoDTO>) jornadaService.obtenerJornadas();

        if(jornadas.size() == 0){
            return new ResponseEntity<>("No hay jornadas", HttpStatus.OK);
        }
        return new ResponseEntity<>(jornadas, HttpStatus.OK);
    }



    @GetMapping("/jornadas/nroDocumento")
    public ResponseEntity<List<Jornada>> obtenerJornadasPorDNI( @RequestParam Integer nroDocumentoEmpleado){

        return new ResponseEntity<>(jornadaService.obtenerJornadasPornroDocumento(jornadaService.getJornadas(),  nroDocumentoEmpleado), HttpStatus.ACCEPTED);

    }

    @GetMapping("/jornadas/fecha")
    public ResponseEntity<List<Jornada>> obtenerJornadasPornroDocumento( @RequestParam String fecha){

        return new ResponseEntity<>(jornadaService.obtenerJornadasPorFecha(jornadaService.getJornadas(),  fecha), HttpStatus.ACCEPTED);

    }


    @GetMapping("/jornadas/dniyfecha")
    public ResponseEntity<List<Jornada>> ObtenerJornadasPorDNIyFecha(@RequestParam Integer dni, @RequestParam String fecha){

        return new ResponseEntity<>(jornadaService.obtenerJornadasPorFecha(jornadaService.obtenerJornadasPornroDocumento(jornadaService.getJornadas(),dni),fecha), HttpStatus.ACCEPTED);

    }

}
