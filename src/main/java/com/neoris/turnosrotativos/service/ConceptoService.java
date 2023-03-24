package com.neoris.turnosrotativos.service;



import com.neoris.turnosrotativos.entities.Concepto;

import java.util.List;

public interface ConceptoService {


    List<Concepto> getConceptos();

    Concepto getConcepto(Integer id);

}
