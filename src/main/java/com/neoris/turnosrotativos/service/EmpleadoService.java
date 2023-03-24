package com.neoris.turnosrotativos.service;

import com.neoris.turnosrotativos.entities.Empleado;

import java.util.List;

public interface EmpleadoService {


    Empleado agregarEmpleado(Empleado empleado);

    List<Empleado> getEmpleados();

    Empleado getEmpleado(Long id);

    Empleado findByDni(Integer dni);

    Empleado findByEmail(String email);

    String eliminarEmpleado(Long id);


}
