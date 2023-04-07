package com.neoris.turnosrotativos.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Jornada {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name="native", strategy = "native")
    private Long id;


    @Column(nullable = false)
    private Long empleado;
    @Column(nullable = false)
    private Integer idConcepto;

    @Column(nullable = false)
    private LocalDate fecha;

    private Integer horasTrabajadas = 0; //para evitar null como me corregieron en el tp anterior


    public Jornada() {
    }

    public Jornada(Long idEmpleado, Integer idConcepto, LocalDate fecha, Integer horasTrabajadas) {
        this.empleado = idEmpleado;
        this.idConcepto = idConcepto;
        this.fecha = fecha;
        this.horasTrabajadas = horasTrabajadas;
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


    @Override
    public String toString() {
        return "Jornada{" +
                "idEmpleado=" + empleado +
                ", idConcepto=" + idConcepto +
                ", fecha=" + fecha +
                ", horasTrabajadas=" + horasTrabajadas +
                '}';
    }
}
