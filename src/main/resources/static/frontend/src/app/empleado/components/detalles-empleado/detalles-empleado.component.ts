import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EmpleadosService } from '../../services/empleados.service';
import { Empleado } from '../../models/empleado';

@Component({
  selector: 'app-detalles-empleado',
  templateUrl: './detalles-empleado.component.html',
  styleUrls: ['./detalles-empleado.component.css']
})
export class DetallesEmpleadoComponent implements OnInit {


  id: number;
  empleado: Empleado;

  // mensajeDeError: String;

  constructor(private route:ActivatedRoute, private  empleadoService: EmpleadosService, private router:Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.empleado = new Empleado();
    this.empleadoService.obtenerEmpleadoPorId(this.id).subscribe((res) =>
      this.empleado = res,
      error => this.router.navigate(['/error']) 
    )
  }


  eliminarEmpleado(id:number){
    this.empleadoService.eliminarEmpleado(id).subscribe(res =>{
      this.router.navigate(['/empleados'])
    })
  }


  actualizarEmpleado(id:number){
    this.router.navigate(['actualizar-empleado', id])
  }


}
