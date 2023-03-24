package com.neoris.turnosrotativos;

import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.repositories.EmpleadoRepository;
import com.neoris.turnosrotativos.repositories.JornadaRepository;
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
	public CommandLineRunner initData(EmpleadoRepository empleadoRepository, JornadaRepository jornadaRepository){

		return args -> {

			Empleado empleado = new Empleado(41242521, "Matias", "Gonzalez", "mati@gmail.com", LocalDate.of(1998, 8, 21), LocalDate.of(2023, 3, 20));
			empleadoRepository.save(empleado);



		};
	}
}
