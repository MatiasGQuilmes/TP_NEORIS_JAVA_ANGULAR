import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarComponent } from './empleado/components/listar/listar.component';
import { CrearEmpleadoComponent } from './empleado/components/crear-empleado/crear-empleado.component';
import { DetallesEmpleadoComponent } from './empleado/components/detalles-empleado/detalles-empleado.component';
import { ActualizarEmpleadoComponent } from './empleado/components/actualizar-empleado/actualizar-empleado.component';
import { ErrorComponent } from './empleado/components/error/error.component';



const routes: Routes = [
  {
    path:'empleados',
    component: ListarComponent
  },
  {
    path: '',
    redirectTo: 'empleados',
    pathMatch:'full'
  },
  {
    path: 'crear-empleado',
    component: CrearEmpleadoComponent
  },
  {
    path: 'empleado-detalles/:id',
    component: DetallesEmpleadoComponent
  }
  ,
  {
    path: 'actualizar-empleado/:id',
    component: ActualizarEmpleadoComponent
  }
  ,
  {
    path: 'error',
    component: ErrorComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
