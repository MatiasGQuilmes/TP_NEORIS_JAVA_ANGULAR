import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';
import { Validators, FormBuilder, FormGroup} from '@angular/forms';

import {NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

import { EmpleadosService } from '../../services/empleados.service';

import { validadoresEspeciales } from '../../validators/validadores.component'

import { Empleado } from '../../models/empleado';

import Swal from 'sweetalert2';




@Component({
  selector: 'app-crear-empleado',
  templateUrl: './crear-empleado.component.html',
  styleUrls: ['./crear-empleado.component.css']
})


export class CrearEmpleadoComponent implements OnInit {
  formulario: FormGroup = this.fb.group({
    nroDocumento: ['',[Validators.required, Validators.minLength(6), Validators.maxLength(10), Validators.pattern('^[0-9]*$')]],
    nombre: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50),  Validators.pattern('^[a-zA-Z]+$')]],
    apellido: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50),  Validators.pattern('^[a-zA-Z]+$')]],
    email: ['', [Validators.email, Validators.required, Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$')]],
    fechaNacimiento: ['',[Validators.required, validadoresEspeciales.validacionFechaNacimiento]],
    fechaDeIngreso: ['',[Validators.required, validadoresEspeciales.validacionFechaIngreso]]
  })
  
  model: NgbDateStruct;
  

  fechaIngresoMin: Date;
  fechaIngresoMax: Date;

  fechaIngresoStrMinima: any;
  fechaIngresoStrMaxima: any;

  fechaNacimientoMin: Date;
  fechaNacimientoMax: Date;

  fechaNacimientoStrMinima: any;
  fechaNacimientoStrMaxima: any;

  respuestaError: String;

  constructor(private fb: FormBuilder, private empleadoServicio: EmpleadosService, private router: Router, private pd:DatePipe) { }

  ngOnInit(): void {
    this.fechaIngresoMin = new Date(new Date().getFullYear(), new Date().getMonth()-5, new Date().getDay())

    this.fechaIngresoStrMinima = this.pd.transform(this.fechaIngresoMin, "yyyy-MM-dd");

    this.fechaIngresoMax = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDay()+2)

    this.fechaIngresoStrMaxima = this.pd.transform(this.fechaIngresoMax, "yyyy-MM-dd");


    this.fechaNacimientoMin = new Date(new Date().getFullYear()-110, new Date().getMonth(), new Date().getDay())

    this.fechaNacimientoStrMinima = this.pd.transform(this.fechaNacimientoMin, "yyyy-MM-dd");
  

    this.fechaNacimientoMax = new Date(new Date().getFullYear()-18, new Date().getMonth(), new Date().getDay()+2)

    this.fechaNacimientoStrMaxima = this.pd.transform(this.fechaNacimientoMax, "yyyy-MM-dd");

  }


  guardarEmpleado() {
    var empleado = new Empleado();
    empleado.nroDocumento = this.formulario.get('nroDocumento')?.value;
    empleado.nombre = this.formulario.get('nombre')?.value;
    empleado.apellido = this.formulario.get('apellido')?.value;
    empleado.email = this.formulario.get('email')?.value;
    empleado.fechaNacimiento = this.formulario.get('fechaNacimiento')?.value;
    empleado.fechaDeIngreso = this.formulario.get('fechaDeIngreso')?.value;

   
 

    this.empleadoServicio.crearEmpleado(empleado).subscribe(res => {
      
      if(res.statusCodeValue != 201){
        this.respuestaError = res.body;
        console.log(res.statusCodeValue);
        
      }else{

        Swal.fire('Empleado Creado', 'El empleado ha sido creado existosamente','success')
        .then(
          (e) => {
            this.formulario.reset();
            this.listaEmpleados();
          }
        )  
      }
    },
      (error) => {
        console.log(error);

      })
  }


  cancelarFormulario() {
    this.formulario.reset();
  }


  listaEmpleados() {
    this.router.navigate(['/empleados'])
  }




}
