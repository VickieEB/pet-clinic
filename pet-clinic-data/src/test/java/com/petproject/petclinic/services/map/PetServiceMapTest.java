package com.petproject.petclinic.services.map;

import com.petproject.petclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetServiceMapTest {

    private PetServiceMap petServiceMap;
    private Long petId = 1L;


    @BeforeEach
    void setUp() {
        petServiceMap = new PetServiceMap();
        petServiceMap.save(Pet.builder().id(petId).build());
    }

    @Test
    void findAll() {
        Set<Pet> pets = petServiceMap.findAll();
        assertEquals(1, pets.size());
    }

    @Test
    void findById() {
        Pet pet = petServiceMap.findById(petId);
        assertNotNull(pet);
        assertEquals(1, pet.getId());
    }

    @Test
    void findByIdNotExistingId() {
        Pet pet = petServiceMap.findById(5L);
        assertNull(pet);
    }

    @Test
    void findByIdNullId() {
        Pet pet = petServiceMap.findById(null);
        assertNull(pet);
    }

    @Test
    void save() {
        Pet sasha = Pet.builder().name("Sasha").build();
        Pet savedPet = petServiceMap.save(sasha);
        assertEquals("Sasha", savedPet.getName());
    }

    @Test
    void saveDuplicate() {
        Long id = 1L;
        Pet pet = Pet.builder().id(id).build();
        Pet savedPet  = petServiceMap.save(pet);
        assertEquals(id, savedPet.getId());
    }

    @Test
    void saveNoId() {
        Pet savedPet = petServiceMap.save(Pet.builder().build());
        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(2, savedPet.getId());
    }

    @Test
    void deleteById() {
        petServiceMap.deleteById(petId);
        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void delete() {
        petServiceMap.delete(petServiceMap.findById(petId));
        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void deleteWithWrongId() {
        petServiceMap.deleteById(5L);
        assertEquals(1, petServiceMap.findAll().size());
    }
}