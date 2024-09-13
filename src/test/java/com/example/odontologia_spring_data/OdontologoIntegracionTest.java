package com.example.odontologia_spring_data;


import com.example.odontologia_spring_data.Entity.Odontologo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologoIntegracionTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void cargarUnOdontolgo() throws Exception{
        Odontologo odontologo1= new Odontologo("MP10","Juan","Maldonado");
        Odontologo odontologo2= new Odontologo(2L,"MP10","Juan","Maldonado");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(odontologo1);
        String responseJson = writer.writeValueAsString(odontologo2);

        MvcResult resultado= this.mockMvc.perform(MockMvcRequestBuilders.post("/odontologo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertEquals(responseJson, resultado.getResponse().getContentAsString());

    }
}
