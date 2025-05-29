package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VetServiceTest {

    @Autowired
    private VetService vetService;

    /**
     * Test para crear veterinarios
     */
    @Test
    public void testCreateVeterinarios() {

        String FIRST_NAME_1 = "Juan";
        String LAST_NAME_1 = "Perez";

        String FIRST_NAME_2 = "Maria";
        String LAST_NAME_2 = "Lopez";

        String FIRST_NAME_3 = "Pedro";
        String LAST_NAME_3 = "Gomez";

        String FIRST_NAME_4 = "Ana";
        String LAST_NAME_4 = "Torres";

        String FIRST_NAME_5 = "Luis";
        String LAST_NAME_5 = "Sanchez";

        Vet vet1 = new Vet(FIRST_NAME_1, LAST_NAME_1);
        Vet vet2 = new Vet(FIRST_NAME_2, LAST_NAME_2);
        Vet vet3 = new Vet(FIRST_NAME_3, LAST_NAME_3);
        Vet vet4 = new Vet(FIRST_NAME_4, LAST_NAME_4);
        Vet vet5 = new Vet(FIRST_NAME_5, LAST_NAME_5);

        Vet vetCreado1 = this.vetService.create(vet1);
        Vet vetCreado2 = this.vetService.create(vet2);
        Vet vetCreado3 = this.vetService.create(vet3);
        Vet vetCreado4 = this.vetService.create(vet4);
        Vet vetCreado5 = this.vetService.create(vet5);

        log.info("===========================================");
        log.info("===== PRUEBA DE CREACIÓN DE VETERINARIOS ====");
        log.info("===========================================");

        log.info("* VETERINARIO 1:");
        log.info("* ID: " + vetCreado1.getId());
        log.info("* Nombre: " + vetCreado1.getFirstName());
        log.info("* Apellido: " + vetCreado1.getLastName());
        log.info("* --------------------");

        log.info("* VETERINARIO 2:");
        log.info("* ID: " + vetCreado2.getId());
        log.info("* Nombre: " + vetCreado2.getFirstName());
        log.info("* Apellido: " + vetCreado2.getLastName());
        log.info("* --------------------");

        log.info("* VETERINARIO 3:");
        log.info("* ID: " + vetCreado3.getId());
        log.info("* Nombre: " + vetCreado3.getFirstName());
        log.info("* Apellido: " + vetCreado3.getLastName());
        log.info("* --------------------");

        log.info("* VETERINARIO 4:");
        log.info("* ID: " + vetCreado4.getId());
        log.info("* Nombre: " + vetCreado4.getFirstName());
        log.info("* Apellido: " + vetCreado4.getLastName());
        log.info("* --------------------");

        log.info("* VETERINARIO 5:");
        log.info("* ID: " + vetCreado5.getId());
        log.info("* Nombre: " + vetCreado5.getFirstName());
        log.info("* Apellido: " + vetCreado5.getLastName());
        log.info("* --------------------");

        log.info("* RESULTADO: Todos los veterinarios fueron creados correctamente");
        log.info("===========================================");

        assertNotNull(vetCreado1.getId());
        assertEquals(FIRST_NAME_1, vetCreado1.getFirstName());
        assertEquals(LAST_NAME_1, vetCreado1.getLastName());

        assertNotNull(vetCreado2.getId());
        assertEquals(FIRST_NAME_2, vetCreado2.getFirstName());
        assertEquals(LAST_NAME_2, vetCreado2.getLastName());

        assertNotNull(vetCreado3.getId());
        assertEquals(FIRST_NAME_3, vetCreado3.getFirstName());
        assertEquals(LAST_NAME_3, vetCreado3.getLastName());

        assertNotNull(vetCreado4.getId());
        assertEquals(FIRST_NAME_4, vetCreado4.getFirstName());
        assertEquals(LAST_NAME_4, vetCreado4.getLastName());

        assertNotNull(vetCreado5.getId());
        assertEquals(FIRST_NAME_5, vetCreado5.getFirstName());
        assertEquals(LAST_NAME_5, vetCreado5.getLastName());
    }

    @Test
    public void testUpdateVet() {

        String FIRST_NAME = "Sharon";
        String LAST_NAME = "Jenkins";

        String UP_FIRST_NAME = "Sharon1";
        String UP_LAST_NAME = "Jenkins1";

        Vet vet  = new Vet(FIRST_NAME, LAST_NAME);

        log.info(">" + vet);
        Vet vetCreated = this.vetService.create(vet);
        log.info(">>" + vetCreated);

        vetCreated.setFirstName(UP_FIRST_NAME);
        vetCreated.setLastName(UP_LAST_NAME);

        Vet upgradePet = this.vetService.update(vetCreated);
        log.info("Vet updated : {}" , upgradePet);

        assertEquals(UP_FIRST_NAME, upgradePet.getFirstName());
        assertEquals(UP_LAST_NAME, upgradePet.getLastName());
    }
}