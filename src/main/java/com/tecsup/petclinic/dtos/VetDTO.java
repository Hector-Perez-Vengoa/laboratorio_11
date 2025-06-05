package com.tecsup.petclinic.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VetDTO {

    private int id;

    private String firstName;

    private String lastName;

}
