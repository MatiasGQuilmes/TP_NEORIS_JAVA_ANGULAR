package com.neoris.turnosrotativos.service.implement;

import com.neoris.turnosrotativos.DTOS.JornadaEmpleadoDTO;
import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.repositories.JornadaRepository;
import com.neoris.turnosrotativos.service.ConceptoService;
import com.neoris.turnosrotativos.service.EmpleadoService;
import com.neoris.turnosrotativos.service.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JornadaServiceImplement implements JornadaService {

    @Autowired
    JornadaRepository jornadaRepository;

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    ConceptoService conceptoService;

    @Override
    public List<JornadaEmpleadoDTO> obtenerJornadas() {
        return jornadaRepository.findAll().stream().map(jornada -> new JornadaEmpleadoDTO(empleadoService.getEmpleado(jornada.getEmpleado()), conceptoService.getConcepto(jornada.getIdConcepto()),jornada)).collect(Collectors.toList());
    }

    @Override
    public Jornada jornadaByDni(Integer dni) {
        return null;
    }

    @Override
    public List<Jornada> jornadaByFecha(LocalDate fecha) {
        return jornadaRepository.findAll().stream().filter(j -> j.getFecha() == fecha).collect(Collectors.toList());
    }


    @Override
    public Jornada agregarJornada(Jornada jornada){
        return jornadaRepository.save(jornada);
    }

    @Override
    public Empleado getEmpleado(Long id) {
        return empleadoService.getEmpleado(id);
    }

    @Override
    public Concepto getConcepto(Integer id) {
        return conceptoService.getConcepto(id);
    }
}
