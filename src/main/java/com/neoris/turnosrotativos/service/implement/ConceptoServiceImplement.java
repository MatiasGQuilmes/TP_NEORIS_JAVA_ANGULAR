package com.neoris.turnosrotativos.service.implement;

import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.repositories.ConceptoRepository;
import com.neoris.turnosrotativos.service.ConceptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConceptoServiceImplement implements ConceptoService {


    @Autowired
    ConceptoRepository conceptoRepository;

    @Override
    public List<Concepto> getConceptos() {
        return conceptoRepository.findAll();
    }

    @Override
    public Concepto getConcepto(Integer id) {

        return conceptoRepository.findById(id).orElse(null);
    }


}
