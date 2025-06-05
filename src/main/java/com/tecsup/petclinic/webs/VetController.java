package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.mapper.VetMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;
import com.tecsup.petclinic.services.VetService;

import java.util.List;

/**
 *
 * @author  
 *
 */
@RestController
@Slf4j
public class VetController {

    String name = null;

    //@Autowired
    private VetService vetService;

    //@Autowired
    private VetMapper mapper;

    /**
     *  Change
     * @param vetService
     * @param mapper
     */
    public VetController(VetService vetService, VetMapper mapper){
        this.vetService = vetService;
        this.mapper = mapper ;
    }

    /**
     * Get all vets
     *
     * @return
     */
    @GetMapping(value = "/vets")
    public ResponseEntity<List<VetDTO>> findAllVets() {

        List<Vet> vets = vetService.findAll();
        log.info("vets: " + vets);
        vets.forEach(item -> log.info("Vet >>  {} ", item));

        List<VetDTO> vetsTO = this.mapper.toVetDTOList(vets);
        log.info("vetsTO: " + vetsTO);
        vetsTO.forEach(item -> log.info("VetDTO >>  {} ", item));

        return ResponseEntity.ok(vetsTO);

    }


    /**
     * Create vet
     *
     * @param vetTO
     * @return
     */
    @PostMapping(value = "/vets")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<VetDTO> create(@RequestBody VetDTO vetTO) {

        Vet newVet = this.mapper.toVet(vetTO);
        VetDTO newVetTO = this.mapper.toVetDTO(vetService.create(newVet));

        return  ResponseEntity.status(HttpStatus.CREATED).body(newVetTO);

    }


    /**
     * @param id
     * @return
     * @throws VetNotFoundException
     */
    @GetMapping(value = "/vets/{id}")
    ResponseEntity<VetDTO> findById(@PathVariable Integer id) {

        VetDTO vetTO = null;

        try {
            Vet vet = vetService.findById(id);
            vetTO = this.mapper.toVetDTO(vet);

        } catch (VetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vetTO);
    }

    /**
     * @param vetTO
     * @param id
     * @return
     */
    @PutMapping(value = "/vets/{id}")
    ResponseEntity<VetDTO>  update(@RequestBody VetDTO vetTO, @PathVariable Integer id) {

        VetDTO updateVetTO = null;

        try {

            Vet updateVet = vetService.findById(id);

            updateVet.setFirstName(vetTO.getFirstName());
            updateVet.setLastName(vetTO.getLastName());

            vetService.update(updateVet);

            updateVetTO = this.mapper.toVetDTO(updateVet);

        } catch (VetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updateVetTO);
    }

    
    @DeleteMapping(value = "/vets/{id}")
    ResponseEntity<String> delete(@PathVariable Integer id) {

        try {
            vetService.delete(id);
            return ResponseEntity.ok(" Delete ID :" + id);
        } catch (VetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
