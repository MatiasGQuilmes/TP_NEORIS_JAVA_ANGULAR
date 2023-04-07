import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListarComponent } from './components/listar/listar.component';
import { HeaderComponent } from './components/header/header.component';
import { HttpClientModule } from '@angular/common/http';
import { CrearEmpleadoComponent } from './components/crear-empleado/crear-empleado.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ActualizarEmpleadoComponent } from './components/actualizar-empleado/actualizar-empleado.component';
import { DetallesEmpleadoComponent } from './components/detalles-empleado/detalles-empleado.component';
import { ErrorComponent } from './components/error/error.component';



@NgModule({
  declarations: [
    ListarComponent,
    HeaderComponent,
    CrearEmpleadoComponent,
    ActualizarEmpleadoComponent,
    DetallesEmpleadoComponent,
    ErrorComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class EmpleadoModule { }
