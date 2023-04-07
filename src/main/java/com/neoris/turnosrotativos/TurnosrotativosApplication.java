package com.neoris.turnosrotativos;

import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.repositories.ConceptoRepository;
import com.neoris.turnosrotativos.repositories.EmpleadoRepository;
import com.neoris.turnosrotativos.repositories.JornadaRepository;
import com.neoris.turnosrotativos.service.ConceptoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class TurnosrotativosApplication {

	static Logger logger = LogManager.getLogger(TurnosrotativosApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TurnosrotativosApplication.class, args);
		logger.info("===========================================");
		logger.info("============    APP RUNNING    ============");
		logger.info("===========================================");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public CommandLineRunner initData(EmpleadoRepository empleadoRepository, JornadaRepository jornadaRepository, ConceptoService conceptoService){

		return args -> {


			// PRUEBA
			Empleado empleado = new Empleado(41242521, "Matias", "Gonzalez", "mati@gmail.com", LocalDate.of(1998, 8, 21), LocalDate.of(2023, 3, 20));
			Empleado empleado1 = new Empleado(123213, "Nahu", "Ayala", "nahu@gmail.com", LocalDate.of(1998, 1, 1), LocalDate.of(2023, 3, 22));

			empleadoRepository.save(empleado);
			empleadoRepository.save(empleado1);


			Jornada jornada = new Jornada(empleado.getId(), conceptoService.getConcepto(1).getId(), LocalDate.now(), 7);
			Jornada jornada1 = new Jornada(empleado1.getId(), conceptoService.getConcepto(2).getId(), LocalDate.now(), 6);
			jornadaRepository.save(jornada);
			jornadaRepository.save(jornada1);

		};
	}
}
