package com.petproject.petclinic.bootstrap;


import com.petproject.petclinic.model.*;
import com.petproject.petclinic.services.OwnerService;
import com.petproject.petclinic.services.PetTypeService;
import com.petproject.petclinic.services.SpecialtyService;
import com.petproject.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {
        //Create PetTypes
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        //Create Specialties
        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        //Creat Owners and their Pets
        Owner owner1 = new Owner();
        //owner1.setId(1L);
        owner1.setFirstName("Lara");
        owner1.setLastName("Oshodi");
        owner1.setAddress("19 Adeola Raji Street");
        owner1.setCity("Gbagada, Lagos");
        owner1.setTelephone("08139820880");

        Pet larasPet = new Pet();
        larasPet.setPetType(savedCatPetType);
        larasPet.setOwner(owner1);
        larasPet.setBirthDate(LocalDate.now());
        larasPet.setName("Lulu-Lemon");
        owner1.getPets().add(larasPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Christiana");
        owner2.setLastName("Faffinatu");
        owner2.setAddress("10 Ajose Adeogun");
        owner2.setCity("Victoria Island, Lagos");
        owner2.setTelephone("08067848221");

        Pet christysPet = new Pet();
        christysPet.setPetType(savedDogPetType);
        christysPet.setOwner(owner2);
        christysPet.setBirthDate(LocalDate.now());
        christysPet.setName("Jason");
        owner2.getPets().add(christysPet);

        ownerService.save(owner2);

        //Create Vets
        Vet vet1 = new Vet();
        vet1.setFirstName("Jules");
        vet1.setLastName("Kinder");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jenny");
        vet2.setLastName("Smith");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        Vet vet3 = new Vet();
        vet3.setFirstName("Simisola");
        vet3.setLastName("Ire-Ola");
        vet3.getSpecialities().add(savedDentistry);
        vetService.save(vet3);

        System.out.println("====== Data Loaded======");
    }
}
