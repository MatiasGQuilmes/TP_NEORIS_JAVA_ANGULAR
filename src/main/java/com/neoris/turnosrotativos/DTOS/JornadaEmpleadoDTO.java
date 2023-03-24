package com.neoris.turnosrotativos.DTOS;

import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.entities.Jornada;

import java.time.LocalDate;

public class JornadaEmpleadoDTO {

    private Long id;

    private Integer dniEmpleado;
    private String nombreCompleto;

    private LocalDate fecha;

    private String concepto;

    private Integer hsTrabajadas;

    public JornadaEmpleadoDTO(Empleado empleado, Concepto concepto, Jornada jornada) {
        this.id = jornada.getId();
        this.dniEmpleado = empleado.getDni();
        this.nombreCompleto = empleado.getNombre() + " " + empleado.getApellido();
        this.fecha = jornada.getFecha();
        this.concepto = concepto.getNombre();
        this.hsTrabajadas = jornada.getHorasTrabajadas();
    }

    public Long getId() {
        return id;
    }

    public Integer getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(Integer dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Integer getHsTrabajadas() {
        return hsTrabajadas;
    }

    public void setHsTrabajadas(Integer hsTrabajadas) {
        this.hsTrabajadas = hsTrabajadas;
    }
}
