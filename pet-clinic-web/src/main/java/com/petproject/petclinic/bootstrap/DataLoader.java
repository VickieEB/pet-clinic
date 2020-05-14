package com.petproject.petclinic.bootstrap;


import com.petproject.petclinic.model.Owner;
import com.petproject.petclinic.model.PetType;
import com.petproject.petclinic.model.Vet;
import com.petproject.petclinic.services.OwnerService;
import com.petproject.petclinic.services.PetTypeService;
import com.petproject.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Vickie");
        owner1.setLastName("EB");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Christiana");
        owner2.setLastName("Faffinatu");

        ownerService.save(owner2);

        System.out.println("======Owner Data Loaded======");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Jules");
        vet1.setLastName("Kinder");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Jenny");
        vet2.setLastName("Smith");

        vetService.save(vet2);

        System.out.println("======Vet Data Loaded======");

    }
}
