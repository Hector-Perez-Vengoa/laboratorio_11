package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VetRepository extends JpaRepository<Vet, Integer> {

    List<Vet> findByLastName(String lastName);

    List<Vet> findByFirstName(String firstName);

    @Override
    List<Vet> findAll();


}
