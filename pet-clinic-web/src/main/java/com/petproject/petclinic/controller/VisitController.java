package com.petproject.petclinic.controller;

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
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
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

//    @ModelAttribute("visit")
//    public Visit loadPetWithVisit(@PathVariable Long petId, Model model){
//        Pet pet = petService.findById(petId);
//        model.addAttribute("pet", pet);
//
//        Visit visit = new Visit();
//        pet.getVisits().add(visit);
//        visit.setPet(pet);
//        return visit;
//    }

    @GetMapping("/new")
    public String initVisitCreationForm(@PathVariable Long petId, Model model){
        model.addAttribute("visit", new Visit());
        model.addAttribute("pet", petService.findById(petId));
        return VIEWS_VISITS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processNewVisitForm(@Valid Visit visit, @PathVariable Long petId, BindingResult result){
        if(result.hasErrors()){
            return VIEWS_VISITS_CREATE_OR_UPDATE_FORM;
        }else{
            visit.setPet(petService.findById(petId));
            visitService.save(visit);
        }
        return "redirect:/owners/1";
    }


}
