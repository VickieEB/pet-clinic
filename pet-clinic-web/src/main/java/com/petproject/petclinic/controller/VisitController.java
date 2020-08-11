package com.petproject.petclinic.controller;

import com.petproject.petclinic.model.Pet;
import com.petproject.petclinic.model.Visit;
import com.petproject.petclinic.services.PetService;
import com.petproject.petclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
//@RequestMapping("/owners/{ownerId}/pets/{petId}")
public class VisitController {

    private VisitService visitService;
    private PetService petService;
    private final static String VIEWS_VISITS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdateVisitForm";

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void initPetBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId, Model model){
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);

        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    //SpringMVC will call loadPetWithVisits() before this method
    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initVisitCreationForm(@PathVariable Long petId, Model model){
        return VIEWS_VISITS_CREATE_OR_UPDATE_FORM;
    }

    //SpringMVC will call loadPetWithVisits() before this method
    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult result){
        if(result.hasErrors()){
            return VIEWS_VISITS_CREATE_OR_UPDATE_FORM;
        }else{
            visitService.save(visit);
        }
        return VIEWS_VISITS_CREATE_OR_UPDATE_FORM;
    }


}
