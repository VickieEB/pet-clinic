package com.petproject.petclinic.services.springdatajpa;

import com.petproject.petclinic.model.PetType;
import com.petproject.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetTypeSDJpaServiceTest {

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeSDJpaService service;

    PetType returnedPetType;

    @BeforeEach
    void setUp() {
        returnedPetType = PetType.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<PetType> petTypes = new HashSet<>();
        petTypes.add(PetType.builder().id(1L).build());
        petTypes.add(PetType.builder().id(2L).build());
        when(petTypeRepository.findAll()).thenReturn(petTypes);

        Set<PetType> petTypeSet = service.findAll();
        assertNotNull(petTypeSet);
        assertEquals(2, service.findAll().size());
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(returnedPetType));
        PetType petType = service.findById(1L);
        assertNotNull(petType);
        verify(petTypeRepository).findById(1L);
    }

    @Test
    void findByIdNotFound() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.empty());
        PetType petType = service.findById(5L);
        assertNull(petType);
        verify(petTypeRepository).findById(anyLong());
    }

    @Test
    void save() {
        PetType petTypeToSave =  PetType.builder().id(2L).build();
        when(petTypeRepository.save(any())).thenReturn(petTypeToSave);
        PetType returned = service.save(petTypeToSave);
        assertNotNull(returned);
        verify(petTypeRepository).save(petTypeToSave);
    }

    @Test
    void delete() {
        service.delete(returnedPetType);
        verify(petTypeRepository).delete(any());

    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(petTypeRepository).deleteById(anyLong());
    }


}