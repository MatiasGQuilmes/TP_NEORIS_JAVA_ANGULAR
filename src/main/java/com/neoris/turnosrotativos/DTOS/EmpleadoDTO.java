package com.neoris.turnosrotativos.DTOS;

import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.entities.Jornada;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EmpleadoDTO {

    private Long id;

    @Column(nullable = false )
    private Integer nroDocumento;

    @Column(nullable = false)
    @NotBlank(message = "El nombre no puede ser vacio")
    private String nombre;

    @Column(nullable = false)
    @NotBlank(message = "El apellido no puede ser vacio")
    private String apellido;

    @Column(nullable = false)
    @NotBlank(message = "El email no puede ser vacio")
    private String email;
    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private LocalDate fechaDeIngreso;

    private LocalDate fechaDeCreacion;


    private List<JornadaDTO> jornadas;

    public EmpleadoDTO() {
    }

    public EmpleadoDTO(Empleado empleado) {
        this.id = empleado.getId();
        this.nroDocumento = empleado.getNroDocumento();
        this.nombre = empleado.getNombre();
        this.apellido = empleado.getApellido();
        this.email = empleado.getEmail();
        this.fechaNacimiento = empleado.getFechaNacimiento();
        this.fechaDeIngreso = empleado.getFechaDeIngreso();
        this.fechaDeCreacion = empleado.getFechaDeCreacion();
        this.jornadas = empleado.getJornadas().stream().map(jornada -> new JornadaDTO(jornada)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(Integer nroDocumento) {
        this.nroDocumento = nroDocumento;
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

    public List<JornadaDTO> getJornadas() {
        return jornadas;
    }

    public void setJornadas(List<JornadaDTO> jornadas) {
        this.jornadas = jornadas;
    }
}
