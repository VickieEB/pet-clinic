package com.petproject.petclinic.controller;

import com.petproject.petclinic.model.Pet;
import com.petproject.petclinic.model.Visit;
import com.petproject.petclinic.services.PetService;
import com.petproject.petclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;




@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController controller;

    MockMvc mockMvc;

    Pet pet;

    Visit visit;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


        pet = Pet.builder().id(1L).build();


    }
}