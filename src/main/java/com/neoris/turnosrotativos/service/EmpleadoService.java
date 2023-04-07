package com.neoris.turnosrotativos.service;

import com.neoris.turnosrotativos.DTOS.EmpleadoDTO;
import com.neoris.turnosrotativos.entities.Empleado;
import org.springframework.http.ResponseEntity;


import java.util.Optional;

public interface EmpleadoService {


    ResponseEntity<Object> agregarEmpleado(Empleado empleado);

    ResponseEntity<Object> getEmpleados();

    Optional<EmpleadoDTO> getEmpleado(Long id);

    Empleado empleado(Long id);

    Empleado findByDni(Integer dni);

    Empleado findByEmail(String email);

    ResponseEntity<Object> eliminarEmpleado(Long id);

    ResponseEntity<Object> actualizarEmpleado(Long id, Empleado empleado);



}
