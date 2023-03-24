package com.neoris.turnosrotativos.service;

import com.neoris.turnosrotativos.DTOS.JornadaEmpleadoDTO;
import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.entities.Jornada;

import java.time.LocalDate;
import java.util.List;

public interface JornadaService {

    List<JornadaEmpleadoDTO> obtenerJornadas();

    Jornada jornadaByDni(Integer dni);

    List<Jornada> jornadaByFecha(LocalDate fecha);

    Jornada agregarJornada(Jornada jornada);

    Empleado getEmpleado(Long id);

    Concepto getConcepto(Integer id);

}
