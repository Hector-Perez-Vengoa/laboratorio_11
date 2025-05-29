package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;

import java.util.List;

public interface VetService {

    Vet create (Vet vet);

    void delete (Integer id) throws VetNotFoundException;

    List<Vet> findAll();

    List<Vet> findByLastName(String lastName);

    List<Vet> findByFirstName(String firstname);

    Vet update (Vet vet);


}
