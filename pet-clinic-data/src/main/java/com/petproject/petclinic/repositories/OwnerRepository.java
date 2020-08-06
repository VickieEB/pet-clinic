package com.petproject.petclinic.repositories;

import com.petproject.petclinic.model.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findByLastNameLike(String lastName);

    @Query("select o from Owner o where lower(o.lastName) like lower(concat('%', :lastname,'%'))")
    List<Owner> findByLastNameLowerCaseLike(@Param("lastname") String lastName);
}
