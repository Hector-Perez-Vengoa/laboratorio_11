package com.tecsup.petclinic.webs;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.dtos.VetDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class VetControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindVetOK() throws Exception {

        int VET_ID = 1;
        String VET_FIRST_NAME = "James";
        String VET_LAST_NAME = "Carter";

        this.mockMvc.perform(get("/vets/1"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(VET_ID))
                .andExpect(jsonPath("$.firstName").value(VET_FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(VET_LAST_NAME));
    }    @Test
    public void testFindVetNotFound() throws Exception {
        mockMvc.perform(get("/vets/999"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /**
     * Prueba crear veterinario
     * @throws Exception
     */
    @Test
    public void testCreateVet() throws Exception {

        String VET_FIRST_NAME = "Dr. House";
        String VET_LAST_NAME = "Wilson";

        VetDTO newVetTO = new VetDTO();
        newVetTO.setFirstName(VET_FIRST_NAME);
        newVetTO.setLastName(VET_LAST_NAME);

        this.mockMvc.perform(post("/vets")
                        .content(om.writeValueAsString(newVetTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(VET_FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(VET_LAST_NAME)));
    }
}