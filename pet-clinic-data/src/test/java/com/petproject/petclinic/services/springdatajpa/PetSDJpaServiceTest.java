package com.petproject.petclinic.services.springdatajpa;

import com.petproject.petclinic.model.Pet;
import com.petproject.petclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {

    public static final String NAME = "Coco";
    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDJpaService service;

    Pet returnedPet;

    @BeforeEach
    void setUp() {
        returnedPet = Pet.builder().id(1L).name(NAME).build();
    }

    @Test
    void findAll() {
        Set<Pet> pets = new HashSet<>();
        pets.add(Pet.builder().id(1L).build());
        pets.add(Pet.builder().id(2L).build());

        when(petRepository.findAll()).thenReturn(pets);
        final Set<Pet> allPets = service.findAll();
        assertNotNull(allPets);

        verify(petRepository).findAll();

    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnedPet));

        Pet pet = service.findById(1L);
        assertNotNull(pet);
        assertEquals(1, pet.getId());
        verify(petRepository).findById(anyLong());
    }

    @Test
    void save() {
        when(petRepository.save(any())).thenReturn(returnedPet);
        Pet savePet = service.save(Pet.builder().id(1L).build());
        assertEquals(1, savePet.getId());
        assertNotNull(savePet);
        verify(petRepository).save(any());
    }

    @Test
    void delete() {

        service.delete(returnedPet);
        verify(petRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(petRepository).deleteById(anyLong());
    }
}