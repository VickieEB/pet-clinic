package com.petproject.petclinic.controller;

import com.petproject.petclinic.model.Owner;
import com.petproject.petclinic.model.Pet;
import com.petproject.petclinic.model.PetType;
import com.petproject.petclinic.model.Visit;
import com.petproject.petclinic.services.PetService;
import com.petproject.petclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    String VIEWS_VISITS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdateVisitForm";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        when(petService.findById(anyLong()))
                .thenReturn(Pet.builder().id(1L)
                        //.birthDate(LocalDate.of(2018,11,13))
                        .name("Rico")
                        .visits(new HashSet<>())
                        .owner(Owner.builder().id(1L).lastName("Etim Bassey").firstName("Vickie").build())
                        .petType(PetType.builder().name("Cat").build())
                        .build()
                );
    }

    @Test
    void initVisitFormCreation() throws Exception {
        mockMvc.perform(get("/owners/1/pets/2/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEWS_VISITS_CREATE_OR_UPDATE_FORM));
    }

    @Test
    void processNewVisitForm() throws Exception {
        mockMvc.perform(post("/owners/1/pets/2/visits/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("date", "2020-08-27")
                .param("description", "Leg Injury")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("visit"))
                //.andExpect(view().name(VIEWS_VISITS_CREATE_OR_UPDATE_FORM));
                .andExpect(view().name("redirect:/owners/1"));
    }
}