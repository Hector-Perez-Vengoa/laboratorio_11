package com.tecsup.petclinic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsup.petclinic.entities.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    // Busca propietarios cuyo firstName contenga el texto, sin importar mayúsculas/minúsculas
    List<Owner> findByFirstNameContainingIgnoreCase(String firstName);

    // También podrías agregar otros métodos si quieres, por ejemplo:
    // List<Owner> findByLastNameContainingIgnoreCase(String lastName);
}
