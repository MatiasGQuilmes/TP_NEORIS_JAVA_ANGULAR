package com.neoris.turnosrotativos.service.implement;

import com.neoris.turnosrotativos.DTOS.EmpleadoDTO;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.repositories.EmpleadoRepository;
import com.neoris.turnosrotativos.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImplement implements EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;


    @Override
    public ResponseEntity<Object> agregarEmpleado(Empleado empleado) {

        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(empleado.getFechaNacimiento(), fechaActual);
        int edad = periodo.getYears();


        Empleado empleadoNroDocumento = empleadoRepository.findByNroDocumento(empleado.getNroDocumento());
        Empleado empleadoEmail = empleadoRepository.findByEmail(empleado.getEmail());


        if(empleado.getFechaNacimiento().isAfter(fechaActual)){
            return new ResponseEntity<>("La fecha de nacimiento no puede ser posterior al día de la fecha.",HttpStatus.BAD_REQUEST);
        }
        if(edad < 18){
            return new ResponseEntity<>("La edad del empleado no puede ser menor a 18 años",HttpStatus.BAD_REQUEST);
        }
        if(empleadoNroDocumento != null){
            return new ResponseEntity<>("Ya existe un empleado con el documento ingresado.",HttpStatus.CONFLICT);
        }
        if(empleadoEmail != null){
            return new ResponseEntity<>("Ya existe un empleado con el email ingresado.",HttpStatus.CONFLICT);
        }
        if(empleado.getFechaDeIngreso().isAfter(fechaActual)){ //de la fecha de ingreso que pase por body, le pregunto si es una fecha posterior a la actual, y si lo es, devuelve un bad request con el mensaje
            return new ResponseEntity<>("La fecha de ingreso no puede ser posterior al día de la fecha.",HttpStatus.BAD_REQUEST);
        }
        if (!formatoEmailValido(empleado.getEmail())) { //tengo un metodo abajo de todo que valida al email como corresponde, y si no esta bien validado, responde un bad request con que el email ingresado no es correcto
            return new ResponseEntity<>("El email ingresado no es correcto.",HttpStatus.BAD_REQUEST);
        }
        if (!formatoNombreApellidoValido(empleado.getNombre())) { //lo mismo que el metodo del email, pero con el nombre
            return new ResponseEntity<>("Solo se permiten letras en el campo Nombre",HttpStatus.BAD_REQUEST);
        }

        if (!formatoNombreApellidoValido(empleado.getApellido())) {
            return new ResponseEntity<>("Solo se permiten letras en el campo Apellido",HttpStatus.BAD_REQUEST);
        }



        Empleado empleadoAgregado = empleadoRepository.save(empleado);

        return new ResponseEntity<>(empleadoAgregado, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> getEmpleados() {

        List<EmpleadoDTO> empleados = empleadoRepository.findAll().stream().map(empleado -> new EmpleadoDTO(empleado)).collect(Collectors.toList());

        if(empleados.size() == 0){
            return new ResponseEntity<>("No hay empleados",HttpStatus.OK);
        }

        return new ResponseEntity<>(empleados, HttpStatus.CREATED);
    }

    @Override
    public Optional<EmpleadoDTO> getEmpleado(Long id) {

        Optional<EmpleadoDTO> empleado = empleadoRepository.findById(id).map(empl -> new EmpleadoDTO(empl));

//        if(empleado.isEmpty()){
//            return new ResponseEntity<>("No se encontro el empleado con el id: " + id , HttpStatus.NOT_FOUND);
//        }
//
//         return new ResponseEntity<>(empleado, HttpStatus.CREATED);
        return empleado;
    }

    @Override
    public Empleado empleado(Long id) {

        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Empleado findByDni(Integer nroDocumento) {

        return empleadoRepository.findByNroDocumento(nroDocumento);

    }

    @Override
    public Empleado findByEmail(String email) {
        return empleadoRepository.findByEmail(email);
    }

    @Override
    public ResponseEntity<Object> eliminarEmpleado(Long id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);

        if (empleado.isEmpty()) {
            return new ResponseEntity<>("No se encontro el empleado con el id: " + id, HttpStatus.NOT_FOUND);
        }

        empleadoRepository.deleteById(id);

        return new ResponseEntity<>("El empleado fue eliminado con éxito.", HttpStatus.NO_CONTENT);
    }


    @Override
    public ResponseEntity<Object> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoAct){

        Empleado empleadoEncontrado = empleadoRepository.findById(id).orElse(null);

        if (empleadoEncontrado == null) {
            return new ResponseEntity<>("No se encontro el empleado con el id: " + id, HttpStatus.NOT_FOUND);
        }


        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(empleadoAct.getFechaNacimiento(), fechaActual);
        int edad = periodo.getYears();

        if(edad < 18){
            return new ResponseEntity<>("La edad del empleado no puede ser menor a 18 años",HttpStatus.BAD_REQUEST);
        }

        if(!empleadoEncontrado.getNroDocumento().equals(empleadoAct.getNroDocumento())){
            if(empleadoRepository.existsByNroDocumento(empleadoEncontrado.getNroDocumento())){
                return new ResponseEntity<>("Ya existe un empleado con el documento ingresado.",HttpStatus.CONFLICT);
            }
        }

        if(!empleadoEncontrado.getEmail().equals(empleadoAct.getEmail())){
            if(empleadoRepository.existsByEmail(empleadoEncontrado.getEmail())){
                return new ResponseEntity<>("Ya existe un empleado con el email ingresado.",HttpStatus.CONFLICT);
            }
        }

        if(empleadoAct.getFechaDeIngreso().isAfter(fechaActual)){ //de la fecha de ingreso que pase por body, le pregunto si es una fecha posterior a la actual, y si lo es, devuelve un bad request con el mensaje
            return new ResponseEntity<>("La fecha de ingreso no puede ser posterior al día de la fecha.",HttpStatus.BAD_REQUEST);
        }
        if(empleadoAct.getFechaNacimiento().isAfter(fechaActual)){
            return new ResponseEntity<>("La fecha de nacimiento no puede ser posterior al día de la fecha.",HttpStatus.BAD_REQUEST);
        }
        if (!formatoEmailValido(empleadoAct.getEmail())) { //tengo un metodo abajo de todo que valida al email como corresponde, y si no esta bien validado, responde un bad request con que el email ingresado no es correcto
            return new ResponseEntity<>("El email ingresado no es correcto.",HttpStatus.BAD_REQUEST);
        }
        if (!formatoNombreApellidoValido(empleadoAct.getNombre())) { //lo mismo que el metodo del email, pero con el nombre
            return new ResponseEntity<>("Solo se permiten letras en el campo Nombre",HttpStatus.BAD_REQUEST);
        }

        if (!formatoNombreApellidoValido(empleadoAct.getApellido())) {
            return new ResponseEntity<>("Solo se permiten letras en el campo Apellido",HttpStatus.BAD_REQUEST);
        }




        empleadoEncontrado.setNroDocumento(empleadoAct.getNroDocumento());
        empleadoEncontrado.setNombre(empleadoAct.getNombre());
        empleadoEncontrado.setApellido(empleadoAct.getApellido());
        empleadoEncontrado.setEmail(empleadoAct.getEmail());
        empleadoEncontrado.setFechaNacimiento(empleadoAct.getFechaNacimiento());
        empleadoEncontrado.setFechaDeIngreso(empleadoAct.getFechaDeIngreso());



        empleadoRepository.save(empleadoEncontrado);

        return new ResponseEntity<>(empleadoEncontrado, HttpStatus.OK);
    }




    private boolean formatoEmailValido(String email) { //
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"); //googlee diferentes formas de validar un email, y este me parecio muy bueno, lo decidi hacer asi para poder validarlo en los condicionales y poder hacer la respuesta de entidad
        return pattern.matcher(email).matches(); //el metodo compila la expresion, y con el matcher comparamos el email que le pasamos con el patron,  si el email coincide con el patrón, el método devuelve true, de lo contrario, false, segun como lo entendi.
    }
    private boolean formatoNombreApellidoValido(String name) { //lo mismo que el email :D
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        return pattern.matcher(name).matches();
    }





}
