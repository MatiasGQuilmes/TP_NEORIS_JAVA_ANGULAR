package com.neoris.turnosrotativos.service.implement;

import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.repositories.EmpleadoRepository;
import com.neoris.turnosrotativos.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImplement implements EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;


    @Override
    public Empleado agregarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public List<Empleado> getEmpleados() {

        return empleadoRepository.findAll();
    }

    @Override
    public Empleado getEmpleado(Long id) {


        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Empleado findByDni(Integer dni) {
        return empleadoRepository.findByDni(dni);
    }

    @Override
    public Empleado findByEmail(String email) {
        return empleadoRepository.findByEmail(email);
    }

    @Override
    public String eliminarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
        return "Empleado eliminado con exito";
    }


}
