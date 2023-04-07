import { Component, OnInit } from '@angular/core';
import { EmpleadosService } from '../../services/empleados.service';
import { Empleado } from '../../models/empleado';
import { Router } from '@angular/router';

@Component({
  selector: 'listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {


  
  empleados:Empleado[];

  constructor(private empleadoService: EmpleadosService, private router:Router) { }

  ngOnInit(): void {
      this.obtenerEmpleados()
  }


  obtenerEmpleados(){
    this.empleadoService.listarEmpleados().subscribe((res:any) =>
    
      this.empleados = res.body,
   
      (error) => this.router.navigate(['/error']) )
  }

  
  verDetallesEmpleado(id:number){
    this.router.navigate(['empleado-detalles', id])
  }


   eliminarEmpleado(id:number){
    this.empleadoService.eliminarEmpleado(id).subscribe(res =>
      this.obtenerEmpleados(),
      error => this.router.navigate(['/error']) 
    )
  }

   actualizarEmpleado(id:number){
      this.router.navigate(['actualizar-empleado', id])
  }

}
