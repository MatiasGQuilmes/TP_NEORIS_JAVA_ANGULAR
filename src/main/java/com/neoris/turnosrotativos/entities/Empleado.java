package com.neoris.turnosrotativos.entities;

import com.neoris.turnosrotativos.DTOS.JornadaEmpleadoDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name="native", strategy = "native")
    private Long id;

    @Column(name="nro_documento")
    private Integer nroDocumento;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;


    @Column(name="email")
    private String email;

    @Column(name="fecha_nacimiento")
    private LocalDate fechaNacimiento;


    @Column(name="fecha_ingreso")
    private LocalDate fechaDeIngreso;

    private LocalDate fechaDeCreacion;


    @OneToMany(cascade = CascadeType.ALL, mappedBy="empleado", fetch = FetchType.LAZY)
    List<Jornada> jornadas = new ArrayList<>();


    public Empleado() {
        this.fechaDeCreacion = LocalDate.now();
    }

    public Empleado(Integer nroDocumento, String nombre, String apellido, String email, LocalDate fechaNacimiento, LocalDate fechaDeIngreso) {
        this.nroDocumento = nroDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaDeIngreso = fechaDeIngreso;
        this.fechaDeCreacion = LocalDate.now();
    }

    public Long getId() {
        return id;
    }


    public Integer getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(Integer NroDocumento) {
        this.nroDocumento = NroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public void setFechaDeIngreso(LocalDate fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public List<Jornada> getJornadas() {
        return jornadas;
    }

    public void setJornadas(List<Jornada> jornadas) {
        this.jornadas = jornadas;
    }
}
