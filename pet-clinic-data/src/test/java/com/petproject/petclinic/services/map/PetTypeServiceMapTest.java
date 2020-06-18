package com.petproject.petclinic.services.map;

import com.petproject.petclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeServiceMapTest {

    PetTypeServiceMap petTypeServiceMap;
    Long petTypeId = 1L;

    @BeforeEach
    void setUp() {
        petTypeServiceMap = new PetTypeServiceMap();
        petTypeServiceMap.save(PetType.builder().id(petTypeId).build());
    }

    @Test
    void findAll() {
        Set<PetType> petTypes = petTypeServiceMap.findAll();
        assertEquals(1, petTypes.size());
    }

    @Test
    void deleteById() {
        petTypeServiceMap.deleteById(petTypeId);
        assertEquals(0, petTypeServiceMap.findAll().size());
    }

    @Test
    void delete() {
        petTypeServiceMap.delete(petTypeServiceMap.findById(petTypeId));
        assertNull(petTypeServiceMap.findById(petTypeId));

    }

    @Test
    void deleteWithWrongId() {
        petTypeServiceMap.deleteById(5L);
        assertEquals(1, petTypeServiceMap.findAll().size());
    }

    @Test
    void save() {
        PetType petType = PetType.builder().id(2L).build();
        PetType savedPetType = petTypeServiceMap.save(petType);
        assertNotNull(savedPetType);
        assertNotNull(savedPetType.getId());
        assertEquals(2, petTypeServiceMap.findAll().size());
        assertEquals(2, savedPetType.getId());
    }

    @Test
    void saveDuplicate() {
        PetType savedPet = petTypeServiceMap.save(PetType.builder().id(1L).build());
        assertNotNull(savedPet.getId());
        assertEquals(1, petTypeServiceMap.findAll().size());
    }

    @Test
    void saveNoId() {
        PetType petType = PetType.builder().build();
        petTypeServiceMap.save(petType);
        assertNotNull(petType.getId());
        assertEquals(2, petType.getId());
    }

    @Test
    void findById() {
        PetType petType = petTypeServiceMap.findById(petTypeId);
        assertEquals(1, petType.getId());
    }

    @Test
    void findByWrongId() {
        PetType petType = petTypeServiceMap.findById(4L);
        assertNull(petType);
    }
}