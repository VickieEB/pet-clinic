package com.petproject.petclinic.bootstrap;


import com.petproject.petclinic.model.*;
import com.petproject.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;


@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
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
        owner1.setAddress("House 5, Palm View Estate, Phase 1");
        owner1.setCity("Iyana Paja, Lagos");
        owner1.setTelephone("08139820880");

        Pet larasPet = new Pet();
        larasPet.setPetType(savedCatPetType);
        larasPet.setOwner(owner1);
        larasPet.setBirthDate(LocalDate.now());
        larasPet.setName("Ruby");
        owner1.getPets().add(larasPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Christiana");
        owner2.setLastName("Etim Faffinatu");
        owner2.setAddress("10 Ajose Adeogun");
        owner2.setCity("Victoria Island, Lagos");
        owner2.setTelephone("08067848221");


        Pet christysPet1 = new Pet();
        christysPet1.setPetType(savedCatPetType);
        christysPet1.setBirthDate(LocalDate.of(2018, Month.JUNE, 12));
        christysPet1.setName("Lucie");
        christysPet1.setOwner(owner2);
        owner2.getPets().add(christysPet1);

        Pet christysPet2 = new Pet();
        christysPet2.setPetType(savedCatPetType);
        christysPet2.setName("Charlie");
        christysPet2.setBirthDate(LocalDate.of(2020, Month.APRIL, 7));
        christysPet2.setOwner(owner2);
        owner2.getPets().add(christysPet2);

        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setFirstName("Vickie");
        owner3.setLastName("Etim Bassey");
        owner3.setAddress("9b Alhaji Lateef Shofowora");
        owner3.setCity("Gbagada, Lagos.");
        owner3.setTelephone("08053936878");

        Pet vickiePet1 = new Pet();
        vickiePet1.setPetType(savedCatPetType);
        vickiePet1.setName("Coco");
        vickiePet1.setBirthDate(LocalDate.of(2020, Month.APRIL, 7));
        vickiePet1.setOwner(owner3);
        owner3.getPets().add(vickiePet1);

        Pet vickiePet2 = new Pet();
        vickiePet2.setPetType(savedCatPetType);
        vickiePet2.setName("Rico");
        vickiePet2.setBirthDate(LocalDate.of(2020, Month.APRIL, 7));
        vickiePet2.setOwner(owner3);
        owner3.getPets().add(vickiePet2);

        ownerService.save(owner3);

        Visit rubysVisit = new Visit();
        rubysVisit.setPet(larasPet);
        rubysVisit.setDate(LocalDate.now());
        rubysVisit.setDescription("Leg Injury");

        visitService.save(rubysVisit);

        Visit cocosVisit1 = new Visit();
        cocosVisit1.setPet(vickiePet1);
        cocosVisit1.setDate(LocalDate.of(2020, Month.JULY, 4));
        cocosVisit1.setDescription("Deworming");

        visitService.save(cocosVisit1);

        Visit ricosVisit1 = new Visit();
        ricosVisit1.setPet(vickiePet2);
        ricosVisit1.setDate(LocalDate.of(2020, Month.JULY, 4));
        ricosVisit1.setDescription("Deworming");


        visitService.save(ricosVisit1);

        Visit cocosVisit2 = new Visit();
        cocosVisit2.setPet(vickiePet1);
        cocosVisit2.setDate(LocalDate.of(2020, Month.JULY, 21));
        cocosVisit2.setDescription("Flea Treatment");

        visitService.save(cocosVisit2);

        Visit ricosVisit2 = new Visit();
        ricosVisit2.setPet(vickiePet2);
        ricosVisit2.setDate(LocalDate.of(2020, Month.JULY, 21));
        ricosVisit2.setDescription("Flea Treatment");


        visitService.save(ricosVisit2);


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
