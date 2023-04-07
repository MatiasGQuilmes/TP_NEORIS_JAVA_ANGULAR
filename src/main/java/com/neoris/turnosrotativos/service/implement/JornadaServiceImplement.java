package com.neoris.turnosrotativos.service.implement;

import com.neoris.turnosrotativos.DTOS.EmpleadoDTO;
import com.neoris.turnosrotativos.DTOS.JornadaEmpleadoDTO;
import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.repositories.JornadaRepository;
import com.neoris.turnosrotativos.service.ConceptoService;
import com.neoris.turnosrotativos.service.EmpleadoService;
import com.neoris.turnosrotativos.service.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.neoris.turnosrotativos.constantes.C_TURNOS.DIA_LIBRE;

@Service
public class JornadaServiceImplement implements JornadaService {

    @Autowired
    JornadaRepository jornadaRepository;


    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    ConceptoService conceptoService;

//    public ResponseEntity<Object> agregarJornada(Jornada jornada){
//
//        Optional<Jornada> empleado = jornadaRepository.findById(jornada.getEmpleado());//traigo el id del empleado que se ingreso por body
//        Concepto concepto = conceptoService.getConcepto(jornada.getIdConcepto());//traigo el id del concepto que se ingreso por body
//
//        if(jornada.getEmpleado() == null){
//            return new ResponseEntity<>("El campo id_empleado es obligatorio.", HttpStatus.BAD_REQUEST);
//        }
//
//        if(jornada.getIdConcepto() == null){
//            return new ResponseEntity<>("El campo id_concepto es obligatorio.",HttpStatus.BAD_REQUEST);
//        }
//
//        if(jornada.getFecha() == null){
//            return new ResponseEntity<>("El campo fecha es obligatorio.",HttpStatus.BAD_REQUEST);
//        }
//
//        if(this.getConcepto(jornada.getIdConcepto()).getId().equals(DIA_LIBRE)  ){//aca pregunto si el concepto que se paso por body es diferente a dia libre, osea si es turno normal o extra, va a requerir obligatoriamente las horas trabajadas
//            if(jornada.getHorasTrabajadas() == 0){
//                return new ResponseEntity<>("hsTrabajadas es obligatorio para el concepto ingresado.",HttpStatus.BAD_REQUEST);
//            }
//        }
//
//        if(this.getConcepto(jornada.getIdConcepto()).getId().equals(DIA_LIBRE) ){// aca pregunto que si el concepto que se paso por body es dia libre, entonces va a decir que no requiere la propiedad de horas trabajadas.
//            if(jornada.getHorasTrabajadas() != 0){
//                return new ResponseEntity<>("El concepto ingresado no requiere el ingreso de hsTrabajadas.",HttpStatus.BAD_REQUEST);
//            }
//        }
//
//        if(empleadoService.getEmpleado(jornada.getEmpleado()) == null){ //pregunto si el empleado es igual a nulo, osea que no existe, devuelve la respuesta de entidad not found con su mensaje
//            return new ResponseEntity<>("No existe el empleado ingresado.",HttpStatus.NOT_FOUND);
//        }
//
//        if(this.getConcepto(jornada.getIdConcepto()) == null){//aca lo mismo pregunto si el concepto es igual a nulo, osea que no existe, devuelve la respuesta de entidad not found con su mensaje
//            return new ResponseEntity<>("No existe el concepto ingresado.",HttpStatus.NOT_FOUND);
//        }
//
//        /////////////  REGLAS DE NEGOCIO /////////////////
//
//        //punto 1
//        if(!this.getConcepto(jornada.getIdConcepto()).getId().equals(DIA_LIBRE) ){
//            if(jornada.getHorasTrabajadas() < concepto.getHsMinimo() || jornada.getHorasTrabajadas() > concepto.getHsMaximo()){ //aca pregunto si el concepto que le pasamos es diferente a dia libre, y las horas trabajadas que le pasamos por body es menos a las horas minimas o mayor a las maximas, devuelve un bad request porque excede el rango de las horas
//                return new ResponseEntity<>("El rango de horas que se puede cargar para este concepto es de " + concepto.getHsMinimo() + " - "  + concepto.getHsMaximo(),HttpStatus.BAD_REQUEST);
//            }
//        }
//
//        //punto 2
//        if(empleado.getJornadas().stream().map( j -> {return j.getFecha().equals(jornada.getFecha()) && j.getIdConcepto().equals(DIA_LIBRE);}).collect(Collectors.toList()).contains(true)){
//            return new ResponseEntity<>("El empleado ingresado cuenta con un día libre en esa fecha.",HttpStatus.BAD_REQUEST); //aca lo que haces es que si quiero ingresar una jornada normal en un dia libre, que me responda que el empleado cuenta con un dia libre en esa fecha, con el contains digo si es true los valores de la lista, es porq cuenta con un dia libre en esa fecha
//        }
//
//        //punto 3
//        if(empleado.getJornadas().stream().map( j -> {return j.getFecha().equals(jornada.getFecha()) && j.getIdConcepto() == jornada.getIdConcepto(); } ).collect(Collectors.toList()).contains(true)){
//
//            return new ResponseEntity<>("El empleado ya tiene registrado una jornada con este concepto en la fecha ingresada",HttpStatus.BAD_REQUEST); //aca pregunte si la fecha que hay en la lista de jornadas es igual a la que ingreso por body, y ademas el concepto de esa jornada es igual a la q ingreso yo, me responde el mensaje correspondiente
//
//        }
//
//        //punto 5
//        if(empleado.getJornadas().stream().map(j-> {return j.getFecha().equals(jornada.getFecha()) && jornada.getIdConcepto() == DIA_LIBRE && j.getIdConcepto() != 0;}).collect(Collectors.toList()).contains(true) ){
//
//            return new ResponseEntity<>("El empleado no puede cargar Dia Libre si cargo un turno previamente para la fecha ingresada",HttpStatus.NOT_FOUND);
//
//        }
//
//        //punto 4
//        if(empleado.getJornadas().stream().map( j -> {return j.getFecha().equals(jornada.getFecha()) && j.getHorasTrabajadas() + jornada.getHorasTrabajadas() > MAX_HORAS_TRABAJADAS_POR_DIA;}).collect(Collectors.toList()).contains(true)){
//
//            return new ResponseEntity<>("El empleado no puede cargar más de 12 horas trabajadas en un día.",HttpStatus.NOT_FOUND);
//
//        }
//
//        //punto 6
//        if(jornadaService.getHorasSemanales(jornadaService.getJornadasDeLaSemana(jornada.getFecha(), empleado.getId())) > MAX_HORAS_TRABAJADAS_POR_SEMANA){
//
//            return new ResponseEntity<>("El empleado ingresado supera las 48 horas semanales.",HttpStatus.NOT_FOUND);
//
//        }
//
//        //punto 7
//        if(jornadaService.getCantidadTurnos(jornadaService.getJornadasDeLaSemana(jornada.getFecha(), empleado.getId()), jornada.getIdConcepto()) >= MAX_TURNOS_EXTRAS_POR_SEMANA && jornada.getIdConcepto() == TURNO_EXTRA ){
//            return new ResponseEntity<>("El empleado ingresado ya cuenta con 3 turnos extra esta semana.",HttpStatus.NOT_FOUND);
//        }
//
//        //punto 8
//        if(jornadaService.getCantidadTurnos(jornadaService.getJornadasDeLaSemana(jornada.getFecha(), empleado.getId()), jornada.getIdConcepto()) >= MAX_TURNOS_NORMALES_POR_SEMANA && jornada.getIdConcepto() == TURNO_NORMAL){
//            return new ResponseEntity<>("El empleado ingresado ya cuenta con 5 turnos normales esta semana.",HttpStatus.NOT_FOUND);
//        }
//
//
//        //punto 9
//        if(jornadaService.getCantidadTurnos(jornadaService.getJornadasDeLaSemana(jornada.getFecha(), empleado.getId()), jornada.getIdConcepto()) >= MAX_DIAS_LIBRES_POR_SEMANA && jornada.getIdConcepto() == DIA_LIBRE ){
//            return new ResponseEntity<>("El empleado no cuenta con más días libres esta semana.",HttpStatus.NOT_FOUND);
//        }
//
//
//
//
//
//        empleadoService.getEmpleado(jornada.getEmpleado()).getJornadas().add(jornada);//si todo esta correcto y no se detiene en las validaciones, le agregamos la jornada a la lista de jornadas que tiene cada empleado
//        jornadaService.agregarJornada(jornada);//y luego le agregamos la jornada a la bd de jornadas
//
//
//        JornadaEmpleadoDTO jornadaEmpleadoDTO = new JornadaEmpleadoDTO(empleado, concepto, jornada);//cree un patron DTO para poder mostrar los datis de la misma forma que pide el response de stoplight
//        return new ResponseEntity<>(jornadaEmpleadoDTO, HttpStatus.CREATED);
//    }



    @Override
    public List<JornadaEmpleadoDTO> obtenerJornadas() {
        return jornadaRepository.findAll().stream().map(jornada -> new JornadaEmpleadoDTO(empleadoService.empleado(jornada.getEmpleado()), conceptoService.getConcepto(jornada.getIdConcepto()),jornada)).collect(Collectors.toList());
    }

    @Override
    public List<Jornada> getJornadas() {
        return jornadaRepository.findAll();
    }

    @Override
    public List<Jornada> obtenerJornadasPornroDocumento(List<Jornada> jornadas, Integer nroDocumentoEmpleado) {
        return jornadas.stream()
                .filter(jornada -> Objects.requireNonNull(empleadoService.getEmpleado(jornada.getEmpleado())
                        .orElse(null)).getNroDocumento().equals(nroDocumentoEmpleado) )
                .collect(Collectors.toList());
    }


    @Override
    public List<Jornada> obtenerJornadasPorFecha(List<Jornada> jornadas, String fecha) {
        LocalDate fecha1 = LocalDate.parse(fecha);

        return jornadaRepository.findAll().stream().filter(jornada -> jornada.getFecha().equals(fecha1)).collect(Collectors.toList());
    }


    @Override
    public Jornada agregarJornada(Jornada jornada){
        return jornadaRepository.save(jornada);
    }



    @Override
    public Empleado empleadoPorId(Long id) {
        return empleadoService.empleado(id);
    }


    @Override
    public Concepto getConcepto(Integer id) {
        return conceptoService.getConcepto(id);
    }




    @Override
    public List<Jornada> getJornadasDeLaSemana(LocalDate fecha, Long idEmpleado){

        LocalDate primerDiaSemana = fecha.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        List<Jornada> jornadasDeLaSemana = new ArrayList<>();
        // Recorremos los 7 días de la semana
//        for (int i = 0; i < 7; i++) {
//            // Añadimos el número de días correspondiente para obtener la fecha de cada día de la semana
//            LocalDate fechaDia = primerDiaSemana.plusDays(i);
//
//            // Obtenemos el nombre del día de la semana y lo imprimimos junto con la fecha correspondiente
//            String nombreDia = fechaDia.getDayOfWeek().name();
//
//            empleadoService.getEmpleado(idEmpleado).getJornadas().forEach( jornada -> {
//                if (jornada.getFecha().equals(fechaDia)) {
//                    jornadasDeLaSemana.add(jornada);
//                }});
//
//        }

        return jornadasDeLaSemana;


    }

    @Override
    public Integer getHorasSemanales(List<Jornada> jornadas) {



        Integer horas = 0;

        for (Jornada jornada : jornadas) {
            if(jornada.getIdConcepto() != 3){
                horas += jornada.getHorasTrabajadas();
            }
        }

        return horas;
    }

    @Override
    public Integer getCantidadTurnos(List<Jornada> jornadas, Integer idConcepto) {
        Integer turnos = 0;

        for (Jornada jornada : jornadas) {
            if (jornada.getIdConcepto() == idConcepto) {
                turnos++;
            }
        }

        return turnos;
    }
}
