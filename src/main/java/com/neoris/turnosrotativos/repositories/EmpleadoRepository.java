package com.neoris.turnosrotativos.repositories;


import com.neoris.turnosrotativos.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Empleado findByNroDocumento(Integer nroDocumento);


    boolean existsByNroDocumento(Integer nroDocumento);

    boolean existsByEmail(String email);

    Empleado findByEmail(String email);

}
