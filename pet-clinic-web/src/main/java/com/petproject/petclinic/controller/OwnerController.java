package com.petproject.petclinic.controller;

import com.petproject.petclinic.model.Owner;
import com.petproject.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    public static final String VIEWS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

//    @GetMapping({"", "/", "/index", "/index.html"})
//    public String listOwners(Model model){
//        model.addAttribute("owners", ownerService.findAll());
//        return "owners/index";
//    }

    @GetMapping("/find")
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId){
      ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
      modelAndView.addObject(ownerService.findById(ownerId));
      return modelAndView;
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){
        if(owner.getLastName() == null){
            owner.setLastName("");
        }

        //Todo Allow the search work with Lower Case
        //List<Owner> results = ownerService.findByLastNameLike("%" + owner.getLastName() + "%");
        List<Owner> results = ownerService.findByLastNameLowerCaseLike(owner.getLastName());

        if(results.isEmpty()){
            result.rejectValue("lastName", "notFound", "Not Found");
            return "owners/findOwners";
        }else if(results.size() == 1){
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        }else {
            //more than one owner found
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/new")
    public String initCreationForm(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return VIEWS_CREATE_OR_UPDATE_OWNER_FORM;
        }else{
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }

    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model){
        model.addAttribute("owner", ownerService.findById(ownerId));
        return VIEWS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult bindingResult, @PathVariable Long ownerId){
        if (bindingResult.hasErrors()){
            return VIEWS_CREATE_OR_UPDATE_OWNER_FORM;
        }else{
            owner.setId(ownerId);
            Owner saveOwner = ownerService.save(owner);
            return "redirect:/owners/" + saveOwner.getId();
        }
    }


}
