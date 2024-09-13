package com.example.odontologia_spring_data;

import com.example.odontologia_spring_data.Entity.Domicilio;
import com.example.odontologia_spring_data.Entity.Odontologo;
import com.example.odontologia_spring_data.Entity.Paciente;
import com.example.odontologia_spring_data.Entity.Turno;
import com.example.odontologia_spring_data.Service.OdontologoService;
import com.example.odontologia_spring_data.Service.PacienteService;
import com.example.odontologia_spring_data.Service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) //no necesito login en esta clase
public class TurnoIntegracionTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;


    public void cargarTurnos(){
        Paciente paciente1= pacienteService.guardar(new Paciente("Matias","Santos","111111", LocalDate.of(2024,9,12),"matiassantos@digitalhouse.com",new Domicilio("Calle 1",122,"Uruguay","Montevideo")));
        Paciente paciente2= pacienteService.guardar(new Paciente("Helen","Vasquez","1112221", LocalDate.of(2024,9,12),"helenvasquez@digitalhouse.com",new Domicilio("Calle 2",122,"Lima","Peru")));
        Odontologo odontologo1= odontologoService.guardar(new Odontologo("MP10","Juan","Maldonado"));
        Odontologo odontologo2= odontologoService.guardar(new Odontologo("MP20","Daniela","Paz"));
        Turno turno1= turnoService.guardar(new Turno(LocalDate.of(2024,11,12),odontologo1,paciente1));
        Turno turno2= turnoService.guardar(new Turno(LocalDate.of(2024,12,20),odontologo2,paciente2));
    }

    @Test
    public void listarTodosLosTurnos() throws Exception{
        cargarTurnos();
        MvcResult resultado= mockMvc.perform(MockMvcRequestBuilders.get("/turno").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(resultado.getResponse().getContentAsString().isEmpty());

    }
}
