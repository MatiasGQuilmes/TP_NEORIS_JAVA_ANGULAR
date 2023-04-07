package com.neoris.turnosrotativos.service;

import com.neoris.turnosrotativos.DTOS.EmpleadoDTO;
import com.neoris.turnosrotativos.DTOS.JornadaEmpleadoDTO;
import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.entities.Jornada;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface JornadaService {

    List<JornadaEmpleadoDTO> obtenerJornadas();

    List<Jornada> getJornadas();

    List<Jornada> obtenerJornadasPornroDocumento(List<Jornada> jornadas, Integer dniEmpleado);
    List<Jornada> obtenerJornadasPorFecha(List<Jornada> jornadas, String fecha);

    Jornada agregarJornada(Jornada jornada);


    Empleado empleadoPorId(Long id);


    Concepto getConcepto(Integer id);

    List<Jornada> getJornadasDeLaSemana(LocalDate fecha, Long idEmpleado);

    Integer getHorasSemanales(List<Jornada> jornadas);

    Integer getCantidadTurnos(List<Jornada> jornadas, Integer idConcepto);


}
