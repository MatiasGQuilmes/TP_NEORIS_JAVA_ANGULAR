import { HttpClient , HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Empleado } from '../models/empleado';
import { catchError } from 'rxjs/operators'
import { throwError as observableThrowError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmpleadosService {


  private endpoint = '/empleados';
  url = environment.api + this.endpoint;


  constructor(private http: HttpClient) { }


  listarEmpleados(): Observable<Empleado[]>{ 
    return this.http
                  .get<Empleado[]>(this.url)
                  .pipe(
                    catchError(this.errorHandler)
                  )             
  }

  crearEmpleado(empleadoRequest: Empleado): Observable<any>{
    return this.http
                  .post(this.url, empleadoRequest)
                  .pipe(
                    catchError(this.errorHandler)
                  )
  }

  obtenerEmpleadoPorId(id: number):Observable<Empleado>{
    return this.http
                  .get<Empleado>(`${this.url}/${id}`)
                  .pipe(
                    catchError(this.errorHandler)
                  )
  }

  actualizarEmpleado(id: number, empleado:Empleado): Observable<any>{
    return this.http
                .patch(`${this.url}/${id}`, empleado)
                .pipe(
                  catchError(this.errorHandler)
                )
  }

  eliminarEmpleado(id: number): Observable<Object>{
    return this.http
                  .delete(`${this.url}/${id}`)
                  .pipe(
                    catchError(this.errorHandler)
                  )
  }

  errorHandler(error: HttpErrorResponse){
    return observableThrowError(error.message)
  }

}
