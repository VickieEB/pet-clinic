package com.petproject.petclinic.services.map;

import com.petproject.petclinic.model.Owner;
import com.petproject.petclinic.model.Pet;
import com.petproject.petclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VisitServiceMapTest {

    VisitServiceMap  visitServiceMap;
    Long visitId = 1L;
    Pet pet;
    Owner owner;

    @BeforeEach
    void setUp() {
        visitServiceMap = new VisitServiceMap();
        owner = Owner.builder().id(1L).build();
        pet = Pet.builder().id(1L).owner(owner).build();
        visitServiceMap.save(Visit.builder().id(visitId).pet(pet).build());
    }

    @Test
    void findAll() {
        Set<Visit> visits = visitServiceMap.findAll();
        assertEquals(1, visits.size());
    }

    @Test
    void save() {
        Visit visit = Visit.builder().id(visitId).pet(pet).build();
        visitServiceMap.save(visit);
        assertEquals(1L, visit.getId());
    }
}