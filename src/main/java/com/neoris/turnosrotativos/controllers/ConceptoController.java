package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.service.ConceptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ConceptoController {


    @Autowired
    ConceptoService conceptoService;

    // HU 5
    @GetMapping("/conceptos")
    public ResponseEntity<List<Concepto>> obtenerConceptos(){


        return new ResponseEntity<>(conceptoService.getConceptos(), HttpStatus.OK);
    }



}

