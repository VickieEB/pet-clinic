package com.petproject.petclinic.services;

import java.util.Set;

public interface CrudService<T, ID> {
    //Iterable<T> findAll();
    Set<T> findAll();
    T findById(ID id);
    T save(T object);
    void delete(T object);
    void deleteById(ID id);
}
