package com.petproject.petclinic.repositories;

import com.petproject.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository  extends CrudRepository<Visit, Long> {
}
