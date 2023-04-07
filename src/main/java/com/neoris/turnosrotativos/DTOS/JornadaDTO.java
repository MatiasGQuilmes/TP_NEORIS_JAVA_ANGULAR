package com.neoris.turnosrotativos.DTOS;

import com.neoris.turnosrotativos.entities.Jornada;

import javax.persistence.Column;
import java.time.LocalDate;

public class JornadaDTO {

    private Long id;

    private Long empleado;

    private Integer idConcepto;


    private LocalDate fecha;

    private Integer horasTrabajadas = 0;


    public JornadaDTO(Jornada jornada) {
        this.id = jornada.getId();
        this.empleado = jornada.getEmpleado();
        this.idConcepto = jornada.getIdConcepto();
        this.fecha = jornada.getFecha();
        this.horasTrabajadas = jornada.getHorasTrabajadas();
    }


    public Long getId() {
        return id;
    }

    public Long getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Long empleado) {
        this.empleado = empleado;
    }

    public Integer getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(Integer idConcepto) {
        this.idConcepto = idConcepto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(Integer horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
}
