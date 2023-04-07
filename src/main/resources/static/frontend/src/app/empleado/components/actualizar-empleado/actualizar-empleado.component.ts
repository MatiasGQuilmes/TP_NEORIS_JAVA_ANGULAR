import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { EmpleadosService } from '../../services/empleados.service';
import { Empleado } from '../../models/empleado';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { validadoresEspeciales } from '../../validators/validadores.component';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-actualizar-empleado',
  templateUrl: './actualizar-empleado.component.html',
  styleUrls: ['./actualizar-empleado.component.css']
})
export class ActualizarEmpleadoComponent implements OnInit {


  // formulario: FormGroup;

  idEmpleado: number;

  empleado: Empleado = new Empleado();

  respuestaError: String;
  
  fechaIngresoMin: Date;
  fechaIngresoMax: Date;

  fechaIngresoStrMinima: any;
  fechaIngresoStrMaxima: any;

  fechaNacimientoMin: Date;
  fechaNacimientoMax: Date;

  fechaNacimientoStrMinima: any;
  fechaNacimientoStrMaxima: any;


  formulario: FormGroup = this.fb.group({
    nroDocumento: ['',[Validators.required, Validators.minLength(6), Validators.maxLength(10), Validators.pattern('^[0-9]*$')]],
    nombre: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50), Validators.pattern('^[a-zA-Z]+$')]],
    apellido: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50),Validators.pattern('^[a-zA-Z]+$')]],
    email: ['', [Validators.email, Validators.required, Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$')]],
    fechaNacimiento: ['',[Validators.required, validadoresEspeciales.validacionFechaNacimiento]],
    fechaDeIngreso: ['',[Validators.required, validadoresEspeciales.validacionFechaIngreso]]
  })

  constructor(private fb: FormBuilder, private  empleadoService: EmpleadosService, private route:ActivatedRoute,private router:Router, private pd:DatePipe) { }

  ngOnInit(): void {
    this.idEmpleado = this.route.snapshot.params['id'];
    
    this.empleadoService.obtenerEmpleadoPorId(this.idEmpleado).subscribe((res:any) =>

      this.empleado = res,
      error => this.router.navigate(['/error']) 
    )
    
    this.fechaIngresoMin = new Date(new Date().getFullYear(), new Date().getMonth()-5, new Date().getDay())

    this.fechaIngresoStrMinima = this.pd.transform(this.fechaIngresoMin, "yyyy-MM-dd");

    this.fechaIngresoMax = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDay()+2)

    this.fechaIngresoStrMaxima = this.pd.transform(this.fechaIngresoMax, "yyyy-MM-dd");


    this.fechaNacimientoMin = new Date(new Date().getFullYear()-110, new Date().getMonth(), new Date().getDay())

    this.fechaNacimientoStrMinima = this.pd.transform(this.fechaNacimientoMin, "yyyy-MM-dd");
  

    this.fechaNacimientoMax = new Date(new Date().getFullYear()-18, new Date().getMonth(), new Date().getDay()+2)

    this.fechaNacimientoStrMaxima = this.pd.transform(this.fechaNacimientoMax, "yyyy-MM-dd");

  }


  actualizarEmpleado(){
    this.empleadoService.actualizarEmpleado(this.idEmpleado, this.empleado).subscribe(res=>{

        if(res.statusCodeValue != 200){
          this.respuestaError = res.body;
          console.log(res.statusCodeValue);
          
        }else{
          Swal.fire('Empleado actualizado', 'El empleado ha sido actualizado existosamente','success')
        .then(
            (e) => {
              this.router.navigate(['empleados'])
            }
          )
        } 
    },
    (error) => {
      Swal.fire('Error ! ', 'No se ha podido actualizar el empleado','error');
    })
    
  }


 cancelarFormulario() {
    this.router.navigate(['empleados'])
  }

}
